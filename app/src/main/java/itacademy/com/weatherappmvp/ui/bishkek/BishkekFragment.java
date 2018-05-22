package itacademy.com.weatherappmvp.ui.bishkek;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.Locale;

import itacademy.com.weatherappmvp.R;
import itacademy.com.weatherappmvp.data.entity.GeneralModel;
import itacademy.com.weatherappmvp.ui.BaseFragment;

import static itacademy.com.weatherappmvp.config.AppConstants.WEATHER_ICON;

public class BishkekFragment extends BaseFragment implements View.OnClickListener, BishkekContract.View {

    private EditText mCity;
    private TextView mCityInfo;
    private TextView mUpdate;
    private TextView mTemperature;
    private TextView mDetails;
    private ImageView mImageView;

    private BishkekPresenter mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new BishkekPresenter(getContext());
        mPresenter.bind(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);

        mCity = view.findViewById(R.id.etCity);
        mCityInfo = view.findViewById(R.id.tvCity);
        mUpdate = view.findViewById(R.id.tvUpdate);
        mTemperature = view.findViewById(R.id.tvCurTemp);
        mDetails = view.findViewById(R.id.tvDetails);
        mImageView = view.findViewById(R.id.imageView);

        view.findViewById(R.id.button).setOnClickListener(this);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mCityInfo.setText(mPresenter.getSavedCity());
    }

    @Override
    public void onClick(View v) {
        mPresenter.getWeatherByName(mCity.getText().toString());
    }

    @Override
    public void onSuccess(GeneralModel model) {
        mCity.setText(model.getName());
        mUpdate.setText(String.valueOf(model.getDt()));
        mTemperature.setText(String.valueOf(model.getMain().getTemp()));
        mDetails.setText(String.valueOf(model.getCoord()));

        Picasso.get()
                .load(String.format(
                        Locale.getDefault(),
                        WEATHER_ICON,
                        model.getWeatherList().get(0).getIcon()))
                .into(mImageView);
    }

    @Override
    public void onError(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showInvalidCityMessage(String msg) {
        mCity.setError(msg);
    }

    @Override
    public void showLoadingIndicator() {
        showProgressBar();
    }

    @Override
    public void hideLoadingIndicator() {
        dismissProgressBar();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.unbind();
    }
}
