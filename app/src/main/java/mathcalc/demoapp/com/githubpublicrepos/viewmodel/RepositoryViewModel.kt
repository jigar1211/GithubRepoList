package mathcalc.demoapp.com.githubpublicrepos.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import mathcalc.demoapp.com.githubpublicrepos.model.ApiResponseModel
import mathcalc.demoapp.com.githubpublicrepos.service.ProjectRepository

class RepositoryViewModel : ViewModel() {

    private lateinit var repositoryListObservable: LiveData<List<ApiResponseModel>>

    fun getRepositoryList(): LiveData<List<ApiResponseModel>> {

        return repositoryListObservable
    }

    /**
     * Call API from the P
     */
    fun refreshData(userName: String, pagCount: Int, perPageCount: Int) {
        repositoryListObservable = ProjectRepository.getInstance().getRepository(userName, pagCount, perPageCount)
    }

}


