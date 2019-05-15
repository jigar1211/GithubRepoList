package mathcalc.demoapp.com.githubpublicrepos.service

import kotlinx.coroutines.Deferred
import mathcalc.demoapp.com.githubpublicrepos.model.ApiRepsonseFoePullRequest
import mathcalc.demoapp.com.githubpublicrepos.model.ApiResponseModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("users/{userName}/repos")
    fun getRepositories(@Path("userName") userName: String,@Query("page") pageCount:Int,@Query("per_page") pageParItem:Int): Deferred<List<ApiResponseModel>>

    @GET("repos/{userName}/{repositoryName}/pulls?state=all")
    fun getPullRequests(@Path("userName") userName: String,@Path("repositoryName") repositoryName: String): Deferred<List<ApiRepsonseFoePullRequest>>
}