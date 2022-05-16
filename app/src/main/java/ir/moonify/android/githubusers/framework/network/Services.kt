package ir.moonify.android.githubusers.framework.network

import io.reactivex.Single
import ir.moonify.android.githubusers.domain.UserDetail
import ir.moonify.android.githubusers.domain.UserList
import ir.moonify.android.githubusers.util.Constants
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface Services {

    @GET
    suspend fun searchUser(@Url apiUrl: String?, @Query("q") query: String?): UserList

    @GET
    suspend fun getUser(@Url apiUrl: String?): UserDetail

    companion object {
        var retrofitService: Services? = null
        var okHttpClient: OkHttpClient = OkHttpClient().newBuilder()
            /*.certificatePinner(
                CertificatePinner.Builder()
                    // api.github.com public key
                    .add("api.github.com", "sha256/WoiWRyIOVNa9ihaBciRSC7XHjliYS9VwUGOIud4PB18=")
                    .build()
            )*/
            .build()

        fun getInstance(): Services {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.github.com/")
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(Services::class.java)
            }
            return retrofitService!!
        }
    }

}