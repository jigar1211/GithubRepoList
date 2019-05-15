package mathcalc.demoapp.com.githubpublicrepos.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import mathcalc.demoapp.com.githubpublicrepos.model.ApiRepsonseFoePullRequest
import mathcalc.demoapp.com.githubpublicrepos.service.ProjectRepository

class PullRequestViewModel: ViewModel() {

    private lateinit var repositoryListObservable : LiveData<List<ApiRepsonseFoePullRequest>>

    fun getRepositoryList(): LiveData<List<ApiRepsonseFoePullRequest>> {
        return repositoryListObservable
    }

    /**
     * Call API from the P
     */
    fun refreshData(userName: String, repositoryName:String) {
        repositoryListObservable =ProjectRepository.getInstance().getPullRequest(userName,repositoryName)
    }
}