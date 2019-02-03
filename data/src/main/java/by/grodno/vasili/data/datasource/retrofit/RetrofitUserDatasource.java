package by.grodno.vasili.data.datasource.retrofit;

import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import by.grodno.vasili.data.datasource.UserDatasource;
import by.grodno.vasili.data.response.OrganizationResponse;
import by.grodno.vasili.data.response.UserResponse;
import io.reactivex.Maybe;
import io.reactivex.Single;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * {@link UserDatasource} implementation for work with Github API via Retrofit
 */
public class RetrofitUserDatasource implements UserDatasource {
    private static final String BASE_URL = "https://api.github.com/";
    private final UserApiService userApiService;

    public RetrofitUserDatasource() {
        this.userApiService = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(buildRxJavaAdapterFactory())
                .addConverterFactory(buildGson())
                .client(buildOkHttpClient())
                .build()
                .create(UserApiService.class);
    }

    @Override
    public Single<List<UserResponse>> all(int since) {
        return userApiService.getUsers(since);
    }

    @Override
    public Maybe<UserResponse> one(String username) {
        return userApiService.getUser(username);
    }

    @Override
    public Single<List<OrganizationResponse>> organizations(String username) {
        return userApiService.getOrganizations(username);
    }

    @NotNull
    private RxJava2CallAdapterFactory buildRxJavaAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @NotNull
    private GsonConverterFactory buildGson() {
        return GsonConverterFactory.create(new GsonBuilder().create());
    }

    @NotNull
    private OkHttpClient buildOkHttpClient() {
        return new OkHttpClient.Builder()
                .build();
    }
}
