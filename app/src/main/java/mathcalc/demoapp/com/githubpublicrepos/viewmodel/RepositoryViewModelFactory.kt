package mathcalc.demoapp.com.githubpublicrepos.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class RepositoryViewModelFactory(private val userName: String,private val pageCount:Int,private val perPageCount:Int) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RepositoryViewModel(userName,pageCount,perPageCount) as T
    }

}