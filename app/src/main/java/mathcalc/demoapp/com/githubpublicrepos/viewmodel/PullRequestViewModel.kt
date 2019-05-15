package mathcalc.demoapp.com.githubpublicrepos.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import mathcalc.demoapp.com.githubpublicrepos.model.ApiRepsonseFoePullRequest
import mathcalc.demoapp.com.githubpublicrepos.service.ProjectRepository

class PullRequestViewModel(userName: String,repositoryName:String): ViewModel() {

    private val repositoryListObservable = ProjectRepository.getInstance().getPullRequest(userName,repositoryName)

    fun getRepositoryList(): LiveData<List<ApiRepsonseFoePullRequest>> {
        return repositoryListObservable
    }
}