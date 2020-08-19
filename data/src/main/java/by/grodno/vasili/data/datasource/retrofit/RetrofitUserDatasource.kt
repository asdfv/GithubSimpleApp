package by.grodno.vasili.data.datasource.retrofit

import by.grodno.vasili.data.datasource.UserDatasource
import by.grodno.vasili.data.response.OrganizationResponse
import by.grodno.vasili.data.response.UserResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * [UserDatasource] implementation for work with Github API via Retrofit.
 */
class RetrofitUserDatasource(
        private val token: String? = null
) : UserDatasource {
    private val userApiService: UserApiService = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(buildOkHttpClient())
            .build()
            .create(UserApiService::class.java)

    private fun buildOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        token?.let { token ->
            val headerInterceptor = Interceptor { chain ->
                val newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", "token $token")
                        .build()
                chain.proceed(newRequest)
            }
            builder.addInterceptor(headerInterceptor)
        }
        val logingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.HEADERS
        }
        builder.addInterceptor(logingInterceptor)
        return builder.build()
    }

    override suspend fun all(since: Int): List<UserResponse> = userApiService.getUsers(since)

    override suspend fun one(username: String): UserResponse = userApiService.getUser(username)

    override suspend fun organizations(username: String): List<OrganizationResponse> = userApiService.getOrganizations(username)
}
