package com.example.david.syncadapter.Provider;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by david on 25/05/16.
 */
public class StudentsContract {
    public static final String AUTHORITY = "com.example.david.syncadapter.provider";
    public static final Uri BASE_URI = Uri.parse("content://" + AUTHORITY);
    public static final Uri STUDENTS_URI = Uri.withAppendedPath(
            StudentsContract.BASE_URI, "/students");

    /**
     *  MIME Types
     *  Para listas se necesita  'vnd.android.cursor.dir/vnd.com.example.andres.provider.students'
     *  Para items se necesita 'vnd.android.cursor.item/vnd.com.example.andres.provider.students'
     *  La primera parte viene está definida en constantes de ContentResolver
     */
    public static final String URI_TYPE_STUDENT_DIR = ContentResolver.CURSOR_DIR_BASE_TYPE +
            "/vnd.com.example.david.provider.students";

    public static final String URI_TYPE_STUDENT_ITEM = ContentResolver.CURSOR_ITEM_BASE_TYPE +
            "/vnd.com.example.david.provider.students";

    /**
     *  Tabla definida en provider. Acá podría ser una distinta a la de la base de datos,
     *  pero consideramos la misma.
     */
    public static final class StudentsColumns implements BaseColumns {

        private StudentsColumns() {}

        public static final String NAMES = "names";
        public static final String FIRST_LASTNAME = "firstlastname";
        //public static final String SECOND_LASTNAME = "secondlastname";
        public static final String PHONE_NUMBER = "phonenumber";
        public static final String ID_CLOUD = "idcloud";

        public static final String DEFAULT_SORT_ORDER = FIRST_LASTNAME + " ASC";
    }
}


