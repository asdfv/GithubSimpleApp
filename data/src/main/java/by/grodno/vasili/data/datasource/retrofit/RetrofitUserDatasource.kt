package by.grodno.vasili.data.datasource.retrofit

import by.grodno.vasili.data.datasource.UserDatasource
import by.grodno.vasili.data.response.OrganizationResponse
import by.grodno.vasili.data.response.UserResponse
import com.google.gson.GsonBuilder
import io.reactivex.Maybe
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * [UserDatasource] implementation for work with Github API via Retrofit.
 */
class RetrofitUserDatasource : UserDatasource {
    private val userApiService: UserApiService = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addCallAdapterFactory(buildRxJavaAdapterFactory())
            .addConverterFactory(buildGson())
            .client(buildOkHttpClient())
            .build()
            .create(UserApiService::class.java)

    override fun all(since: Int): Single<List<UserResponse>> = userApiService.getUsers(since)

    override fun one(username: String): Maybe<UserResponse> = userApiService.getUser(username)

    override fun organizations(username: String): Single<List<OrganizationResponse>> = userApiService.getOrganizations(username)

    private fun buildRxJavaAdapterFactory(): RxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create()

    private fun buildGson(): GsonConverterFactory = GsonConverterFactory.create(GsonBuilder().create())

    private fun buildOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()
    }
}
