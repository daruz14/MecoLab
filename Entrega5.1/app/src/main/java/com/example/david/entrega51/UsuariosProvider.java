package com.example.david.entrega51;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.annotation.Nullable;

import java.util.ArrayList;

/**
 * Created by david on 11/05/16.
 */
public class UsuariosProvider extends ContentProvider{


    private static final String uri =
            "content://com.example.david.entrega51.UsuariosProvider/Usuarios";

    public static final Uri CONTENT_URI = Uri.parse(uri);
    public static final class Usuarios implements BaseColumns
    {
        private Usuarios() {}

        //Nombres de columnas
        public static final String COL_NOMBRE = "nombre";
    }

    public UsuariosSqliteHelper mydb;
    private static final String TABLA_CLIENTES = "Usuarios";
    private static final int USUARIOS = 1;
    private static final int USUARIOS_ID = 2;
    private static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("com.example.david.entrega51.UsuariosProvider", "clientes", USUARIOS);
        uriMatcher.addURI("com.example.david.entrega51.UsuariosProvider", "clientes/#", USUARIOS_ID);
    }

    @Override
    public boolean onCreate() {
        mydb = new UsuariosSqliteHelper(
                getContext());

        return true;

    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        //Si es una consulta a un ID concreto construimos el WHERE
        String where = selection;
        //if(uriMatcher.match(uri) == USUARIOS_ID){
        //    where = "_id=" + uri.getLastPathSegment();
        //}

        SQLiteDatabase db = mydb.getReadableDatabase();

        Cursor c = db.query(TABLA_CLIENTES, projection, where,
                selectionArgs, null, null, sortOrder);

        return c;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        int match = uriMatcher.match(uri);
        //REVISAR ESTA PARTE!!

        switch (match)
        {
            case USUARIOS:
                return "vnd.android.cursor.dir/vnd.sgoliver.cliente";
            case USUARIOS_ID:
                return "vnd.android.cursor.item/vnd.sgoliver.cliente";
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long regId = 1;

        SQLiteDatabase db = mydb.getWritableDatabase();

        regId = db.insert(TABLA_CLIENTES, null, values);

        Uri newUri = ContentUris.withAppendedId(CONTENT_URI, regId);

        return newUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int cont;

        //Si es una consulta a un ID concreto construimos el WHERE
        String where = selection;
        if(uriMatcher.match(uri) == USUARIOS_ID){
            where = "_id=" + uri.getLastPathSegment();
        }

        SQLiteDatabase db = mydb.getWritableDatabase();

        cont = db.delete(TABLA_CLIENTES, where, selectionArgs);

        return cont;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int cont;

        //Si es una consulta a un ID concreto construimos el WHERE
        String where = selection;
        if(uriMatcher.match(uri) == USUARIOS_ID){
            where = "_id=" + uri.getLastPathSegment();
        }

        SQLiteDatabase db = mydb.getWritableDatabase();

        cont = db.update(TABLA_CLIENTES, values, where, selectionArgs);

        return cont;
    }
    public ArrayList<String> getAllCotacts()
    {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = mydb.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from Usuarios", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            String indice = Character.toString(res.getString(res.getColumnIndex("nombre")).charAt(0));
            array_list.add(res.getString(res.getColumnIndex("nombre")));
            if (!array_list.contains(indice)){array_list.add(indice);}
            res.moveToNext();
        }
        return array_list;
    }
}
