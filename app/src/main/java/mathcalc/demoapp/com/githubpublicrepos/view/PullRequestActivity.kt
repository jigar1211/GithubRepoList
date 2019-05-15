package mathcalc.demoapp.com.githubpublicrepos.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.content_pull_request.*
import kotlinx.android.synthetic.main.pull_request_main.*
import mathcalc.demoapp.com.githubpublicrepos.adapter.PullRequestAdapter
import mathcalc.demoapp.com.githubpublicrepos.model.ApiRepsonseFoePullRequest
import mathcalc.demoapp.com.githubpublicrepos.viewmodel.PullRequestViewModel
import mathcalc.demoapp.com.githubpublicrepos.viewmodel.pullRequestViewModelFactory


class PullRequestActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {
    override fun onRefresh() {
        swipeRefreshPullRequest.isRefreshing = true
        observeViewModel()
    }

    private lateinit var viewModel: PullRequestViewModel
    private lateinit var pullRequestAdapter: PullRequestAdapter
    private lateinit var apiResponseList: MutableList<ApiRepsonseFoePullRequest>
    var isLoading: Boolean = false
    private lateinit var userName: String
    private lateinit var repository: String
    private lateinit var extras: Bundle


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mathcalc.demoapp.com.githubpublicrepos.R.layout.pull_request_main)
        getIntentData()
        mappingData()
    }

    private fun getIntentData() {
        extras = intent.extras
        if (extras == null) {
            userName = ""
            repository = ""
        } else {
            userName = extras.getString("USER_NAME")
            repository = extras.getString("REPOSITORY_NAME")
        }


    }

    private fun mappingData() {

        swipeRefreshPullRequest.setOnRefreshListener(this)
        swipeRefreshPullRequest.setColorSchemeResources(
            mathcalc.demoapp.com.githubpublicrepos.R.color.colorPrimary,
            android.R.color.holo_green_dark,
            android.R.color.holo_orange_dark,
            android.R.color.holo_blue_dark
        )

        swipeRefreshPullRequest.post {
            swipeRefreshPullRequest.isRefreshing = true
            observeViewModel()
        }
        layBack.setOnClickListener {
            onBackPressed()
        }

    }


    private fun observeViewModel() {
        viewModel = ViewModelProviders.of(this, pullRequestViewModelFactory(userName, repository))
            .get(PullRequestViewModel::class.java)

        viewModel.getRepositoryList().observe(this, Observer<List<ApiRepsonseFoePullRequest>> { response ->

            run {
                if (response != null) {
                    isLoading = false
                    apiResponseList = mutableListOf()
                    apiResponseList.addAll(response)
                    pullRequestAdapter = PullRequestAdapter(this, apiResponseList)
                    rvPullRequest.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                    rvPullRequest.adapter = pullRequestAdapter
                    swipeRefreshPullRequest.isRefreshing = false
                }
            }

        })

    }

}