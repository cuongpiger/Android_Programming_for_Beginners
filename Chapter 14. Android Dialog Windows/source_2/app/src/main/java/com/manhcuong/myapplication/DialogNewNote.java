package com.manhcuong.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class DialogNewNote {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_new_note, null);

        final EditText editTitle = dialogView.findViewById(R.id.editTitle);
        final EditText editDescription = dialogView.findViewById(R.id.editDescription);
        final CheckBox checkBoxIdea = dialogView.findViewById(R.id.checkBoxIdea);
        final CheckBox checkBoxTodo = dialogView.findViewById(R.id.checkBoxTodo);
        final CheckBox checkBoxImportant = dialogView.findViewById(R.id.checkBoxImportant);
        Button btnCancel = dialogView.findViewById(R.id.btnCancel);
        Button btnOK = dialogView.findViewById(R.id.btnOK);
        builder.setView(dialogView).setMessage("Add a new note");


        return builder.create();
    }
}
