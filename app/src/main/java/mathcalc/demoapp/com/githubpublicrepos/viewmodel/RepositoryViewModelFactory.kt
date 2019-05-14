package mathcalc.demoapp.com.githubpublicrepos.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class RepositoryViewModelFactory(private val userName: String) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RepositoryViewModel(userName) as T
    }

}