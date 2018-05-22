package itacademy.com.weatherappmvp.ui.bishkek;

import itacademy.com.weatherappmvp.data.entity.GeneralModel;
import itacademy.com.weatherappmvp.ui.IProgressBar;
import itacademy.com.weatherappmvp.ui.Lifecycle;

public interface BishkekContract {

    interface View extends IProgressBar {
        void onSuccess(GeneralModel model);
        void onError(String msg);

        void showInvalidCityMessage(String msg);
    }

    interface Presenter extends Lifecycle<View> {
        void getWeatherByName(String city);

        String getSavedCity();
    }

}
