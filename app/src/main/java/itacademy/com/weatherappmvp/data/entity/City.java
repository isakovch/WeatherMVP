package itacademy.com.weatherappmvp.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Isakov on 04-Oct-17.
 */

public class City implements Parcelable {
    private int id;
    private String name;
    private Coord coord;
    private String country;

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

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public City(int id, String name, Coord coord, String country) {

        this.id = id;
        this.name = name;
        this.coord = coord;
        this.country = country;
    }

    protected City(Parcel in) {
        id = in.readInt();
        name = in.readString();
        coord = (Coord) in.readValue(Coord.class.getClassLoader());
        country = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeValue(coord);
        dest.writeString(country);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<City> CREATOR = new Parcelable.Creator<City>() {
        @Override
        public City createFromParcel(Parcel in) {
            return new City(in);
        }

        @Override
        public City[] newArray(int size) {
            return new City[size];
        }
    };
}