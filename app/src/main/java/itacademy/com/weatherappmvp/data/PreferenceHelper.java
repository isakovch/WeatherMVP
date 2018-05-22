package itacademy.com.weatherappmvp.data;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceHelper {
    private static final String STORAGE_TITLE = "com.itacademy";
    private static final String CITY_NAME = "com.itacademy.weather.city.name";
    private SharedPreferences mPreferences;

    public PreferenceHelper(Context context) {
        mPreferences = context.getSharedPreferences(STORAGE_TITLE, Context.MODE_PRIVATE);
    }

    public void saveCityName(String name) {
        mPreferences.edit().putString(CITY_NAME, name).apply();
    }

    public String getCityName() {
        return mPreferences.getString(CITY_NAME, "");
    }

}
