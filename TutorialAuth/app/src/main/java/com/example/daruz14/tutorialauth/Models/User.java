package com.example.daruz14.tutorialauth.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by daruz14 on 22-08-16.
 */
public class User implements Parcelable {


    public static final String TOKEN = "token";
    public static final String FIRST_NAME = "name";
    public static final String EMAIL = "email";
    public static final String NUMBER = "number";
    public static final String KEY_PAQUETE = "Contacto";


    private String number;
    private String firstName;
    private String email;
    private String uid;

    public User(String nombre, String apellido, String numero, String uid){
        this.number = numero;
        this.firstName = nombre;
        this.email = apellido;
        this.uid = uid;
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

    public String getEmail() {
        return email;
    }

    public String getNumber(){
        return number;
    }

    public String getUid(){
        return uid;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        //esto serializa
        dest.writeString(firstName);
        dest.writeString(email);
        dest.writeString(uid);
        dest.writeString(number);
    }

    protected User(Parcel in) {
        //deserializa
        //este orden debe ser igual al de la funcion de serializar
        firstName = in.readString();
        email = in.readString();
        uid = in.readString();
        number = in.readString();

    }

}
