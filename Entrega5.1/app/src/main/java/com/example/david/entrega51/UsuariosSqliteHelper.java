package com.example.david.entrega51;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by david on 11/05/16.
 */
public class UsuariosSqliteHelper extends SQLiteOpenHelper {
    //Sentencia SQL para crear la tabla de Clientes
    String sqlCreate = "CREATE TABLE Usuarios " +
            "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            " nombre TEXT)";
    public static final String DATABASE_NAME = "Usuarios";
    public UsuariosSqliteHelper(Context context)
    {
        super(context, DATABASE_NAME , null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //Se ejecuta la sentencia SQL de creación de la tabla
        db.execSQL(sqlCreate);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {


        //Se elimina la versión anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS Clientes");

        //Se crea la nueva versión de la tabla
        db.execSQL(sqlCreate);
    }

    public void insertContact  (String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nombre", name);
        db.insert("Usuarios", null, contentValues);


    }
    public ArrayList<String> getAllCotacts()
    {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
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
