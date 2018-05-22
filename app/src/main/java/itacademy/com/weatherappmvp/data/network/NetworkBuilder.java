package itacademy.com.weatherappmvp.data.network;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static itacademy.com.weatherappmvp.config.AppConstants.BASE_URL;

public final class NetworkBuilder {

    private static RetrofitService sService;

    public static RetrofitService initRetrofitClient() {
        if (sService == null) {
            sService = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .client(getClient())
                    .build()
                    .create(RetrofitService.class);
        }

        return sService;
    }
    private static OkHttpClient getClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request.Builder ongoing = chain
                                .request().newBuilder()
                                .addHeader("Accept", "application/json;versions=1");
                        return chain.proceed(ongoing.build());
                    }
                })
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();
    }
}
