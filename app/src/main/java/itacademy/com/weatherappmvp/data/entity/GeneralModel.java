package itacademy.com.weatherappmvp.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Isakov on 04-Oct-17.
 */

public class GeneralModel implements Parcelable {
    private Coord coord;
    @SerializedName("weather")
    private List<Weather> weatherList;
    private String base;
    private Main main;
    private Wind wind;

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public List<Weather> getWeatherList() {
        return weatherList;
    }

    public void setWeatherList(List<Weather> weatherList) {
        this.weatherList = weatherList;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Rain getRain() {
        return rain;
    }

    public void setRain(Rain rain) {
        this.rain = rain;
    }

    public int getDt() {
        return dt;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public GeneralModel(Coord coord, List<Weather> weatherList, String base, Main main, Wind wind, Clouds clouds, Rain rain, int dt, Sys sys, int id, String name, int cod) {

        this.coord = coord;
        this.weatherList = weatherList;
        this.base = base;
        this.main = main;
        this.wind = wind;
        this.clouds = clouds;
        this.rain = rain;
        this.dt = dt;
        this.sys = sys;
        this.id = id;
        this.name = name;
        this.cod = cod;
    }

    private Clouds clouds;
    private Rain rain;
    private int dt;
    private Sys sys;
    private int id;
    private String name;
    private int cod;

    protected GeneralModel(Parcel in) {
        coord = (Coord) in.readValue(Coord.class.getClassLoader());
        if (in.readByte() == 0x01) {
            weatherList = new ArrayList<Weather>();
            in.readList(weatherList, Weather.class.getClassLoader());
        } else {
            weatherList = null;
        }
        base = in.readString();
        main = (Main) in.readValue(Main.class.getClassLoader());
        wind = (Wind) in.readValue(Wind.class.getClassLoader());
        clouds = (Clouds) in.readValue(Clouds.class.getClassLoader());
        rain = (Rain) in.readValue(Rain.class.getClassLoader());
        dt = in.readInt();
        sys = (Sys) in.readValue(Sys.class.getClassLoader());
        id = in.readInt();
        name = in.readString();
        cod = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(coord);
        if (weatherList == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(weatherList);
        }
        dest.writeString(base);
        dest.writeValue(main);
        dest.writeValue(wind);
        dest.writeValue(clouds);
        dest.writeValue(rain);
        dest.writeInt(dt);
        dest.writeValue(sys);
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeInt(cod);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<GeneralModel> CREATOR = new Parcelable.Creator<GeneralModel>() {
        @Override
        public GeneralModel createFromParcel(Parcel in) {
            return new GeneralModel(in);
        }

        @Override
        public GeneralModel[] newArray(int size) {
            return new GeneralModel[size];
        }
    };
}