package com.example.david.tutorialmecolab;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by david on 5/04/16.
 */
public class paquete implements Parcelable{

    public static final String KEY_PAQUETE = "paquete";
    private String mLabel;
    private int mNumber;

    public paquete(String mLabel, int mNumber) {
        this.mLabel = mLabel;
        this.mNumber = mNumber;
    }

    public int getmNumber() {
        return mNumber;
    }

    public String getmLabel() {
        return mLabel;
    }

    protected paquete(Parcel in) {
        //deserializa
        //este orden debe ser igual al de la funcion de serializar
        mLabel = in.readString();
        mNumber = in.readInt();

    }

    public static final Creator<paquete> CREATOR = new Creator<paquete>() {
        @Override
        public paquete createFromParcel(Parcel in) {
            return new paquete(in);
        }

        @Override
        public paquete[] newArray(int size) {
            return new paquete[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        //esto serializa
        dest.writeString(mLabel);
        dest.writeInt(mNumber);
    }
}
