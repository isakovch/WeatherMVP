package itacademy.com.weatherappmvp;

import android.app.Application;
import android.content.Context;

import itacademy.com.weatherappmvp.data.network.NetworkBuilder;
import itacademy.com.weatherappmvp.data.network.RetrofitService;

public class WeatherApp extends Application {

    private RetrofitService mService;

    @Override
    public void onCreate() {
        super.onCreate();
        mService = NetworkBuilder.initRetrofitClient();
    }

    public static WeatherApp get(Context context) {
        return (WeatherApp) context.getApplicationContext();
    }

    public RetrofitService getService() {
        return mService;
    }
}
