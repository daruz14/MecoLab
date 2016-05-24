package com.example.david.entrega2final;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by david on 17/04/16.
 */
public class Paquete implements Parcelable {

    private String mLabel;
    private int mNumber;

    public Paquete(String mLabel, int mNumber) {
        this.mLabel = mLabel;
        this.mNumber = mNumber;
    }

    public int getmNumber() {
        return mNumber;
    }

    public String getmLabel() {
        return mLabel;
    }

    protected Paquete(Parcel in) {
        //deserializa
        //este orden debe ser igual al de la funcion de serializar
        mLabel = in.readString();
        mNumber = in.readInt();

    }

    public static final Creator<Paquete> CREATOR = new Creator<Paquete>() {
        @Override
        public Paquete createFromParcel(Parcel in) {
            return new Paquete(in);
        }

        @Override
        public Paquete[] newArray(int size) {
            return new Paquete[size];
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
