package com.example.david.myapplication.Provider;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by david on 1/06/16.
 */
public class UsersContract {
    public static final String AUTHORITY = "com.example.david.myapplication.provider";
    public static final Uri BASE_URI = Uri.parse("content://" + AUTHORITY);
    public static final Uri USERS_URI = Uri.withAppendedPath(UsersContract.BASE_URI, "/users");//Revisar esta parte

    /**
     *  MIME Types
     *  Para listas se necesita  'vnd.android.cursor.dir/vnd.com.example.andres.provider.students'
     *  Para items se necesita 'vnd.android.cursor.item/vnd.com.example.andres.provider.students'
     *  La primera parte viene está definida en constantes de ContentResolver
     */
    public static final String URI_TYPE_STUDENT_DIR = ContentResolver.CURSOR_DIR_BASE_TYPE +
            "/vnd.com.example.david.provider.users";

    public static final String URI_TYPE_STUDENT_ITEM = ContentResolver.CURSOR_ITEM_BASE_TYPE +
            "/vnd.com.example.david.provider.users";

    /**
     *  Tabla definida en provider. Acá podría ser una distinta a la de la base de datos,
     *  pero consideramos la misma.
     */
    public static final class UsersColumns implements BaseColumns {

        private UsersColumns() {}

        public static final String NAMES = "names";
        public static final String FIRST_LASTNAME = "firstlastname";
        public static final String PHONE_NUMBER = "phonenumber";


        public static final String DEFAULT_SORT_ORDER = FIRST_LASTNAME + " ASC";
    }
}