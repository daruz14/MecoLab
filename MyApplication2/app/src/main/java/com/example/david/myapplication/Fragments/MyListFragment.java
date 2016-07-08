package com.example.david.myapplication.Fragments;

import android.app.Activity;
import android.content.ContentResolver;
import android.support.v4.app.Fragment;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.david.myapplication.Model.Item;
import com.example.david.myapplication.Model.Users;
import com.example.david.myapplication.Persistence.DatabaseContract;
import com.example.david.myapplication.Persistence.UsersDBHelper;
import com.example.david.myapplication.Provider.UsersContract;
import com.example.david.myapplication.R;
import com.example.david.myapplication.UserAdapter;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by david on 1/06/16.
 */
public class MyListFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private ArrayList<Item> mIndexedItemArray = new ArrayList<>();
    private ArrayList<Users> mStudents;
    private UserAdapter mStudentsAdapter;
    private ListView mStudentsView;
    private UsersDBHelper mDbHelper;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list, container, false);
        mStudentsAdapter = new UserAdapter(v.getContext() , R.layout.list_item ,
                mIndexedItemArray);
        mStudents = new ArrayList<>();
        if (!getStudentsDb()){
            mListener.onGetStudentsFromCloud();
        }
        // Inflate the layout for this fragment
        mStudentsView = (ListView) v.findViewById(R.id.list_users);
        mStudentsView.setAdapter(mStudentsAdapter);
        mStudentsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Solo se considera el click cuando se hace sobre una celda que es de del tipo
                // estudiante, no cuando es una letra de la indexación.
                if (mStudentsAdapter.getItem(position).getCellType() == Item.STUDENT_CELL) {

                    mListener.onFragmentInteractionList(mStudentsAdapter.getItem(position));
                }
            }
        });
        return v;
    }

    /**
     * Agrega estudiante a la base de datos. Método usado cuando se obtiene un estudiante del
     * servidor.
     * @param student
     */
    public void addStudent(Users student) {
        if (!studentInDb(student)) return;
        addStudentToDb(student);
        addStudentToIndexedArray(student);

    }

    /**
     * Agrega estudiante a la lista y regenera la lista indexada.
     * @param student
     */
    private void addStudentToIndexedArray(Users student) {
        mStudents.add(student);
        generateIndexedItemArray();
    }

    /**
     * Agrega estudiante a la base de datos local.
     * @param student
     * @return True si se pudo insertar, falso si no.
     */
    private boolean addStudentToDb(Users student) {
        if (mDbHelper == null) {
            mDbHelper = UsersDBHelper.getsInstance(getActivity());
        }
        ContentValues values = new ContentValues();
        values.put(UsersContract.UsersColumns.NAMES, student.getNames());
        values.put(UsersContract.UsersColumns.FIRST_LASTNAME, student.getFirstLastname());
        values.put(UsersContract.UsersColumns.PHONE_NUMBER, student.getPhoneNumber());

        Uri newUri = getActivity().getContentResolver().insert(
                UsersContract.USERS_URIL,   // the user dictionary content URI
                values                          // the values to insert
        );                                  // Valores
        return newUri != null;
    }

    /**
     *
     * @return
     */
    private boolean getStudentsDb() {
        if (mDbHelper == null) {
            mDbHelper = UsersDBHelper.getsInstance(getActivity());
        }
        // Selecciono todas las columnas. Podría dejarse como null.
        String[] projection = {DatabaseContract.Usarios.COLUMN_NAME_STUDENT_NAMES,
                DatabaseContract.Usarios.COLUMN_NAME_FIRST_LASTNAME,
                DatabaseContract.Usarios.COLUMN_NAME_PHONE_NUMBER
        };

        // Se deja como null porque no se requiere filtrar. Un ejemplo podria ser asi:
        // String selection = DatabaseContract.Students.COLUMN_NAME_FIRST_LASTNAME + "= ?"
        String selection = null;

        // Podria ser:
        // String[] selectionArgs = new String[]{ "Apellido" };
        String[] selectionArgs = null;

        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        Cursor c = db.query(DatabaseContract.Usarios.TABLE_NAME,   // Tabla
                projection,                             // Columnas a retornar
                selection,                              // Columnas de WHERE
                selectionArgs,                          // Valores de WHERE
                null,                                   // Group by
                null,                                   // Filtro por columnas de grupos
                DatabaseContract.Usarios.COLUMN_NAME_FIRST_LASTNAME +" ASC"); //Sort order

        ArrayList<Users> studentsInDb = new ArrayList<>();
        c.moveToFirst();
        if (c.getCount()<1) {
            return false;
        }
        while (c.moveToNext()) {
            String names = c.getString(c.getColumnIndexOrThrow(
                    DatabaseContract.Usarios.COLUMN_NAME_STUDENT_NAMES)).toUpperCase();
            String firstLast = c.getString(c.getColumnIndexOrThrow(
                    DatabaseContract.Usarios.COLUMN_NAME_FIRST_LASTNAME)).toUpperCase();
            int number = c.getInt(c.getColumnIndexOrThrow(
                    DatabaseContract.Usarios.COLUMN_NAME_PHONE_NUMBER));
            studentsInDb.add(new Users(names, firstLast, number));
        }
        if (studentsInDb.size()>0) {
            mStudents = studentsInDb;
            generateIndexedItemArray();
            return true;
        }
        return false;
    }

    /**
     * Genera la lista que se mostrará en pantalla. La lista está ordenada por apellido paterno.
     * Contiene además de los estudiantes, celdas con las letras de inicio del apellido del
     * estudiante. Por ejemplo, se mostrará así:
     *
     * A
     * Pedro Aguirre
     * Juan Andrade
     * B
     * ...
     */
    private void generateIndexedItemArray() {
        ArrayList<Item> items = new ArrayList<>();
        Collections.sort(mStudents, new Users());
        char last = mStudents.get(0).getFirstLastname().charAt(0);
        items.add(new Item(last + "","" , 0));

        for (int i=0; i< mStudents.size(); i++) {
            Users s = mStudents.get(i);
            if (last != s.getFirstLastname().charAt(0)) {
                last = s.getFirstLastname().charAt(0);
                Item item = new Item(last+"","", 0);
                items.add(item);
            }
            items.add(new Item(s.getNames() + " " + s.getFirstLastname(),  s.getPhoneNumber().toString() , 1));
        }
        mIndexedItemArray = items;
        mStudentsAdapter.clear();
        mStudentsAdapter.addAll(items);
        mStudentsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    /**
     * Determina si un estudiante ya está en la base de datos.
     * @param student
     * @return True si el estudiante ya está, false si no.
     */
    private boolean studentInDb(Users student) {
        for (int i=0; i< mStudents.size(); i++){
            if (mStudents.get(i).equals(student)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteractionList(Item item);
        void onGetStudentsFromCloud();
    }

}