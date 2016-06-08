package com.example.david.syncadapter.Model;


import java.util.Comparator;

/**
 * Created by david on 25/05/16.
 */
public class Student implements Comparator<Student>{

    public static final String NAME = "name";
    public static final String FIRST_LASTNAME = "first_lastname";
    public static final String SECOND_LASTNAME = "second_lastname";
    public static final String PHONE_NUMBER = "phone_number";
    public static final String ID = "id";

    private String mNames;
    private String mFirstLastname;
    private String mSecondLastname;
    private String mPhoneNumber;
    private int mIdCloud;

    public Student() { }

    public Student(String names, String firstLastname, String secondLastname, String numero) {
        mNames = names;
        mFirstLastname = firstLastname;
        mSecondLastname = secondLastname;
        mPhoneNumber = numero;
    }

    public Student(String names, String firstLastname, String secondLastname, String numero, int idCloud) {
        mNames = names;
        mFirstLastname = firstLastname;
        mSecondLastname = secondLastname;
        mPhoneNumber = numero;
        mIdCloud = idCloud;
    }

    public String getNames() {
        return mNames;
    }

    public String getFirstLastname() {
        return mFirstLastname;
    }

    public String getSecondLastname() {
        return mSecondLastname;
    }

    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    public int getIdCloud() {
        return mIdCloud;
    }

    @Override
    public int compare(Student lhs, Student rhs) {
        return lhs.getFirstLastname().compareTo(rhs.getFirstLastname());
    }

    @Override
    public boolean equals(Object o) {
        Student student = (Student)o;
        if (student.getFirstLastname().equals(mFirstLastname) &&
                student.getSecondLastname().equals(mSecondLastname) &&
                student.getNames().equals(mNames) && student.getPhoneNumber().equals(mPhoneNumber))
            return true;
        return false;
    }
}
