package mathcalc.demoapp.com.githubpublicrepos.service

import kotlinx.coroutines.Deferred
import mathcalc.demoapp.com.githubpublicrepos.model.ApiResponseModel
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET("{userName}/repos")
    fun getRepositories(@Path("userName") userName: String): Deferred<List<ApiResponseModel>>
}