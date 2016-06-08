package com.example.david.myapplication.Model;

import java.util.Comparator;

/**
 * Created by david on 1/06/16.
 */
public class Users implements Comparator<Users> {

    public static final String NAME = "name";
    public static final String FIRST_LASTNAME = "first_lastname";
    public static final String PHONE_NUMBER = "phone_number";
    public static final String ID = "id";

    private String mNames;
    private String mFirstLastname;
    private Integer mPhoneNumber;

    public Users() { }

    public Users(String names, String firstLastname, int numero) {
        mNames = names;
        mFirstLastname = firstLastname;
        mPhoneNumber = numero;
    }


    public String getNames() {
        return mNames;
    }

    public String getFirstLastname() {
        return mFirstLastname;
    }

    public Integer getPhoneNumber() {
        return mPhoneNumber;
    }


    @Override
    public int compare(Users lhs, Users rhs) {
        return lhs.getFirstLastname().compareTo(rhs.getFirstLastname());
    }

    @Override
    public boolean equals(Object o) {
        Users student = (Users)o;
        if (student.getFirstLastname().equals(mFirstLastname) &&
                student.getNames().equals(mNames) && student.getPhoneNumber().equals(mPhoneNumber))
            return true;
        return false;
    }
}