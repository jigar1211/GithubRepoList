package mathcalc.demoapp.com.githubpublicrepos

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import mathcalc.demoapp.com.githubpublicrepos.adapter.RepositoryAdapter
import mathcalc.demoapp.com.githubpublicrepos.model.ApiResponseModel
import mathcalc.demoapp.com.githubpublicrepos.viewmodel.RepositoryViewModel
import mathcalc.demoapp.com.githubpublicrepos.viewmodel.RepositoryViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: RepositoryViewModel
    private lateinit var repositoryAdapter: RepositoryAdapter
    private lateinit var apiResponseList: MutableList<ApiResponseModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mappingData()
    }

    private fun mappingData() {

        //viewModel = ViewModelProviders.of(this).get(RepositoryViewModel::class.java)

        viewModel = ViewModelProviders.of(this, RepositoryViewModelFactory("ReactiveX"))
            .get(RepositoryViewModel::class.java)

        observeViewModel(viewModel)
    }

    private fun observeViewModel(viewModel: RepositoryViewModel) {
        viewModel.getRepositoryList().observe(this, Observer<List<ApiResponseModel>> { response ->

            run {
                if (response != null) {

                    apiResponseList = mutableListOf()
                    apiResponseList.addAll(response)
                    repositoryAdapter = RepositoryAdapter(this, apiResponseList)
                    rvPhotos.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
                    rvPhotos.adapter = repositoryAdapter
                }
            }


        })
    }
}
