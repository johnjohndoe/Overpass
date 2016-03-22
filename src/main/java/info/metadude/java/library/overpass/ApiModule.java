package info.metadude.java.library.overpass;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public final class ApiModule {

    public static OverpassService provideOverpassService() {
        return provideOverpassService(null);
    }

    public static OverpassService provideOverpassService(final OkHttpClient okHttpClient) {
        return createRetrofit(OverpassService.BASE_URL, okHttpClient)
                .create(OverpassService.class);
    }

    private static Retrofit createRetrofit(final String baseUrl, final OkHttpClient okHttpClient) {
        OkHttpClient httpClient = okHttpClient;
        if (httpClient == null) {
            httpClient = new OkHttpClient();
        }

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(MoshiConverterFactory.create())
                .client(httpClient)
                .build();
    }

}

