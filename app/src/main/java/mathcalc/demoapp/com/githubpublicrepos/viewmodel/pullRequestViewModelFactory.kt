package mathcalc.demoapp.com.githubpublicrepos.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class pullRequestViewModelFactory(private val userName: String, private val repositoryName: String) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PullRequestViewModel(userName, repositoryName) as T
    }
}