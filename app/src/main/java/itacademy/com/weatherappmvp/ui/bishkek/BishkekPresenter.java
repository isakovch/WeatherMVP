package itacademy.com.weatherappmvp.ui.bishkek;

import android.content.Context;
import android.support.annotation.NonNull;

import itacademy.com.weatherappmvp.R;
import itacademy.com.weatherappmvp.WeatherApp;
import itacademy.com.weatherappmvp.data.PreferenceHelper;
import itacademy.com.weatherappmvp.data.entity.GeneralModel;
import itacademy.com.weatherappmvp.data.network.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BishkekPresenter implements BishkekContract.Presenter {

    private Context mContext;
    private RetrofitService mService;
    private PreferenceHelper mPrefsHelper;
    private BishkekContract.View mView;

    public BishkekPresenter(Context context) {
        mService = WeatherApp.get(mContext = context).getService();
        mPrefsHelper = new PreferenceHelper(mContext);
    }

    @Override
    public void getWeatherByName(String city) {
        if (isValidData(city)) {
            if (isViewAttached()) mView.showLoadingIndicator();
            saveCity(city);

            mService.getCityWeatherByName(city, mContext.getString(R.string.API_KEY), "metric")
                    .enqueue(new Callback<GeneralModel>() {
                        @Override
                        public void onResponse(@NonNull Call<GeneralModel> call, @NonNull Response<GeneralModel> response) {
                            if (response.isSuccessful() && response.body() != null) {
                                if (isViewAttached()) mView.onSuccess(response.body());
                            } else {
                                if (isViewAttached()) mView.showInvalidCityMessage(response.message());
                            }
                            if (isViewAttached()) mView.hideLoadingIndicator();
                        }

                        @Override
                        public void onFailure(@NonNull Call<GeneralModel> call, @NonNull Throwable t) {
                            if (isViewAttached()) {
                                mView.onError(t.getMessage());
                                mView.hideLoadingIndicator();
                            }
                        }
                    });
        } else {
            if (isViewAttached()) mView.showInvalidCityMessage(mContext.getString(R.string.invalid_city_data));
        }
    }

    @Override
    public String getSavedCity() {
        return mPrefsHelper.getCityName();
    }

    private boolean isValidData(String city) {
        return !city.trim().isEmpty();
    }

    private void saveCity(String city) {
        mPrefsHelper.saveCityName(city);
    }

    @Override
    public void bind(BishkekContract.View view) {
        this.mView = view;
    }

    @Override
    public void unbind() {
        this.mView = null;
    }

    private boolean isViewAttached() {
        return mView != null;
    }
}
