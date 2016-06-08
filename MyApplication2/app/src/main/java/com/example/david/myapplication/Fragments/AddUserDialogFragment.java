package com.example.david.myapplication.Fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.david.myapplication.Model.Users;
import com.example.david.myapplication.R;

/**
 * Created by david on 1/06/16.
 */
public class AddUserDialogFragment extends DialogFragment {

    private NoticeDialogListener mListener;

    public interface NoticeDialogListener {
        void onDialogPositiveClick(DialogFragment dialog, Users student);
        void onDialogNegativeClick(DialogFragment dialog);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View v = inflater.inflate(R.layout.dialog_agregar_user, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(v);
        builder.setTitle(R.string.dialog_add_student_title);

        builder.setPositiveButton(R.string.dialog_add_student_positive, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                String name = ((EditText) v.findViewById(R.id.dialog_student_name))
                        .getText().toString().toUpperCase();
                String firstLastname = ((EditText) v.findViewById(R.id.dialog_first_lastname))
                        .getText().toString().toUpperCase();
                String phoneNumber = ((EditText) v.findViewById(R.id.dialog_phone_number)).getText().toString();

                Users student = new Users(name, firstLastname, Integer.parseInt(phoneNumber));
                mListener.onDialogPositiveClick(AddUserDialogFragment.this , student);
            }
        });

        builder.setNegativeButton(R.string.dialog_add_student_negative, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // No pasa nada
            }
        });
        // Create the AlertDialog object and return it
        return builder.create();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (NoticeDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }
}