package mathcalc.demoapp.com.githubpublicrepos.service

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import mathcalc.demoapp.com.githubpublicrepos.model.ApiResponseModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProjectRepository {

    private val BASE_URL = "https://api.github.com/users/"
    private var apiInterface: ApiInterface? = null

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

        apiInterface = retrofit.create(ApiInterface::class.java)
    }

    companion object {

        private var projectRepository: ProjectRepository? = null

        @Synchronized
        @JvmStatic
        fun getInstance(): ProjectRepository {

            if (projectRepository == null) {
                projectRepository = ProjectRepository()
            }
            return projectRepository!!
        }
    }

    fun getRepository(userName: String): LiveData<List<ApiResponseModel>> {

        val data = MutableLiveData<List<ApiResponseModel>>()

        GlobalScope.launch(Dispatchers.Main) {
            val result = apiInterface?.getRepositories(userName)
            try {
                data.value = result!!.await()
            } catch (e: HttpException) {
                data.value = null
                e.printStackTrace()
            } catch (e: Throwable) {
                data.value = null
                e.printStackTrace()
            }
        }


        return data
    }
}