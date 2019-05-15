package mathcalc.demoapp.com.githubpublicrepos.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import mathcalc.demoapp.com.githubpublicrepos.PaginationScrollListener
import mathcalc.demoapp.com.githubpublicrepos.R
import mathcalc.demoapp.com.githubpublicrepos.UDF
import mathcalc.demoapp.com.githubpublicrepos.adapter.RepositoryAdapter
import mathcalc.demoapp.com.githubpublicrepos.model.ApiResponseModel
import mathcalc.demoapp.com.githubpublicrepos.viewmodel.RepositoryViewModel


class MainActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {
    override fun onRefresh() {
        swipeRefresh.isRefreshing = true
        pageCount = 1
        observeViewModel(pageCount)
    }

    private lateinit var viewModel: RepositoryViewModel
    private lateinit var repositoryAdapter: RepositoryAdapter
    private lateinit var apiResponseList: MutableList<ApiResponseModel>
    var isLastPage: Boolean = false
    var isLoading: Boolean = false
    var pageCount: Int = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mappingData()
    }

    private fun mappingData() {

        swipeRefresh.setOnRefreshListener(this)
        swipeRefresh.setColorSchemeResources(
            R.color.colorPrimary,
            android.R.color.holo_green_dark,
            android.R.color.holo_orange_dark,
            android.R.color.holo_blue_dark
        )

        btnCancel.setOnClickListener {
            etSearch.setText("")
        }

        etSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                btnSearch.performClick()
                true
            } else {
                false
            }
        }

        btnSearch.setOnClickListener {

            pageCount = 1
            swipeRefresh.post {
                swipeRefresh.isRefreshing = true
                btnSearch?.hideKeyboard()
                observeViewModel(pageCount)
            }

        }

        rvPhotos?.addOnScrollListener(object :
            PaginationScrollListener(LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)) {
            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

            override fun loadMoreItems() {
                isLoading = true
                pageCount++
                //you have to call loadmore items to get more data


                observeViewModel(pageCount)
            }
        })
    }


    private fun observeViewModel(pageCount: Int) {
        viewModel = ViewModelProviders.of(this)
            .get(RepositoryViewModel::class.java)
        viewModel.refreshData(etSearch.text.toString(), pageCount, 20)
        viewModel.getRepositoryList().observe(this, Observer<List<ApiResponseModel>> { response ->

            run {
                if (response != null && response.isNotEmpty()) {
                    swipeRefresh.visibility = View.VISIBLE
                    rlError.visibility = View.GONE
                    isLoading = false
                    apiResponseList = mutableListOf()
                    apiResponseList.addAll(response)
                    repositoryAdapter = RepositoryAdapter(this, apiResponseList)
                    rvPhotos.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                    rvPhotos.adapter = repositoryAdapter
                    swipeRefresh.isRefreshing = false
                } else {
                    if (UDF.isOnline(this)) {
                        swipeRefresh.visibility = View.GONE
                        rlError.visibility = View.VISIBLE
                        tvErrorMessage.text = this.resources.getString(R.string.no_record_found)

                    } else {
                        swipeRefresh.visibility = View.GONE
                        rlError.visibility = View.VISIBLE
                        tvErrorMessage.text = this.resources.getString(R.string.check_internet_connection)
                    }
                }
            }

        })
    }

    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
}
