package mathcalc.demoapp.com.githubpublicrepos.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import mathcalc.demoapp.com.githubpublicrepos.model.ApiResponseModel
import mathcalc.demoapp.com.githubpublicrepos.service.ProjectRepository

class RepositoryViewModel(userName: String,pagCount:Int,perPageCount:Int) : ViewModel() {

    private val repositoryListObservable = ProjectRepository.getInstance().getRepository(userName,pagCount,perPageCount)

    fun getRepositoryList(): LiveData<List<ApiResponseModel>> {
        return repositoryListObservable
    }
}