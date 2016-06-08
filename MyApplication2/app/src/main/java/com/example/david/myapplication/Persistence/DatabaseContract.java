package com.example.david.myapplication.Persistence;

import android.provider.BaseColumns;

/**
 * Created by david on 1/06/16.
 */
public class DatabaseContract {

    public DatabaseContract() { }

    public static abstract class Usarios implements BaseColumns {

        public static final String TABLE_NAME = "USERS";
        public static final String COLUMN_NAME_STUDENT_NAMES = "names";
        public static final String COLUMN_NAME_FIRST_LASTNAME = "firstlastname";
        public static final String COLUMN_NAME_PHONE_NUMBER = "phonenumber";


        public static final String TEXT_TYPE = " TEXT";
        public static final String INTEGER_TYPE = " INTEGER";
        public static final String COMMA_SEP = ",";

        public static final String SQL_CREATE_USERS_TABLE =
                "CREATE TABLE " + Usarios.TABLE_NAME + " (" +
                        Usarios._ID + " INTEGER PRIMARY KEY," +
                        Usarios.COLUMN_NAME_STUDENT_NAMES + TEXT_TYPE + COMMA_SEP +
                        Usarios.COLUMN_NAME_FIRST_LASTNAME + TEXT_TYPE + COMMA_SEP +
                        Usarios.COLUMN_NAME_PHONE_NUMBER + INTEGER_TYPE + " )";

        public static final String SQL_DELETE_USERS =
                "DROP TABLE IF EXISTS " + Usarios.TABLE_NAME;
    }
}
