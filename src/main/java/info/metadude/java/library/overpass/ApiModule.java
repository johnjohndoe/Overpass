package info.metadude.java.library.overpass;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import retrofit.MoshiConverterFactory;
import retrofit.Retrofit;

import java.util.List;

public final class ApiModule {

    public static OverpassService provideOverpassService() {
        return provideOverpassService(null);
    }

    public static OverpassService provideOverpassService(final List<Interceptor> httpClientInterceptors) {
        return createRetrofit(OverpassService.BASE_URL, httpClientInterceptors)
                .create(OverpassService.class);
    }

    private static Retrofit createRetrofit(String baseUrl, List<Interceptor> httpClientInterceptors) {
        OkHttpClient httpClient = new OkHttpClient();
        if (httpClientInterceptors != null) {
            httpClient.interceptors().addAll(httpClientInterceptors);
        }

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(MoshiConverterFactory.create())
                .client(httpClient)
                .build();
    }

}

