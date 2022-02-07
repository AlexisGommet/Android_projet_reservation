package com.example.projet_reservation;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class addvoit extends FragmentActivity  {

    String reserv; // or other values
    DBHandler db = new DBHandler(addvoit.this);
    userlog userlog;
    boolean modif = false;
    TimePickerDialog picker;
    DatePickerDialog dpicker;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addvoit);
        EditText mEdit1 = (EditText) findViewById(R.id.editText1);
        EditText mEdit2 = (EditText) findViewById(R.id.editText2);
        EditText mEdit3 = (EditText) findViewById(R.id.editText3);
        Button mButton;
        mButton = findViewById(R.id.button_submit);


        mButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String mEdits1 = mEdit1.getText().toString();
                String mEdits2 = mEdit2.getText().toString();
                String mEdits3 = mEdit3.getText().toString();

                ArrayList<String> reservl = new ArrayList<String>();
                reservl.add(mEdits1);
                reservl.add(mEdits2);
                reservl.add(mEdits3);
                db.add_voit(reservl);
                Toast.makeText(getApplicationContext(), "voiture ajoutée", Toast.LENGTH_LONG).show();
                startActivity(new Intent(addvoit.this, listevoitures.class));
            }

        });
    }
}