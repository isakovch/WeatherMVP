package itacademy.com.weatherappmvp.data.network;

import itacademy.com.weatherappmvp.data.entity.ForecastModel;
import itacademy.com.weatherappmvp.data.entity.GeneralModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static itacademy.com.weatherappmvp.config.AppConstants.GET_CITY_FORECAST;
import static itacademy.com.weatherappmvp.config.AppConstants.GET_CITY_WEATHER;

public interface RetrofitService {

    @GET(GET_CITY_WEATHER)
    Call<GeneralModel> getCityWeather(@Query("id") int id, @Query("appid") String appId, @Query("units") String units);

    @GET(GET_CITY_WEATHER)
    Call<GeneralModel> getCityWeatherByName(@Query("q") String name, @Query("appid") String appId, @Query("units") String units);

    @GET(GET_CITY_WEATHER)
    Call<GeneralModel> getCityWeatherByLocation(@Query("lat") double lat, @Query("lon") double lon, @Query("appid") String appId, @Query("units") String units);

    @GET(GET_CITY_FORECAST)
    Call<ForecastModel> getCityForecast(@Query("id") int id, @Query("cnt") int cnt, @Query("appid") String appId, @Query("units") String units);


}
