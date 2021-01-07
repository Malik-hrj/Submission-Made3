package com.example.submissionmade3.movieList;

import android.os.Parcel;
import android.os.Parcelable;

public class WatchModel implements Parcelable {

    private String title;

    private String overview;

    private String poster;

    private WatchModel(Parcel in) {
        this.title = in.readString();
        this.overview = in.readString();
        this.poster = (String) in.readValue(Integer.class.getClassLoader());
    }

    public static final Creator<WatchModel> CREATOR = new Creator<WatchModel>() {
        @Override
        public WatchModel createFromParcel(Parcel parcelMalik) {
            return new WatchModel(parcelMalik);
        }

        @Override
        public WatchModel[] newArray(int size) {
            return new WatchModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.title);
        parcel.writeString(this.overview);
        parcel.writeString(this.poster);
    }
}
