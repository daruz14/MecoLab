package com.example.david.applicationconnect.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by daruz14 on 22-08-16.
 */
public class User implements Parcelable{


    public static final String TOKEN = "token";
    public static final String FIRST_NAME = "Nombre";
    public static final String LAST_NAME = "Apellido";
    public static final String NUMBER = "Telefono";
    public static final String KEY_PAQUETE = "Contacto";


    private int number;
    private String firstName;
    private String lastName;

    public User(String nombre, String apellido, int numero){
        this.number = numero;
        this.firstName = nombre;
        this.lastName = apellido;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getNumber(){
        return number;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        //esto serializa
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeInt(number);
    }

    protected User(Parcel in) {
        //deserializa
        //este orden debe ser igual al de la funcion de serializar
        firstName = in.readString();
        lastName = in.readString();
        number = in.readInt();

    }

}
