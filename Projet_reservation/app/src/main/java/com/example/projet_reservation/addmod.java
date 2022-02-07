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

public class addmod extends FragmentActivity  {

    String reserv; // or other values
    DBHandler db = new DBHandler(addmod.this);
    userlog userlog;
    boolean modif = false;
    TimePickerDialog picker;
    DatePickerDialog dpicker;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addmod);
        TextView mEdittitre = findViewById(R.id.textView_titre);
        EditText mEdit1 = (EditText) findViewById(R.id.editText1);
        EditText mEdit2 = (EditText) findViewById(R.id.editText2);
        EditText mEdit3 = (EditText) findViewById(R.id.editText3);
        EditText mEdit4 = (EditText) findViewById(R.id.editText4);
        EditText mEdit = (EditText) findViewById(R.id.editText);
        EditText mEdit5 = (EditText) findViewById(R.id.editText5);
        Intent t = new Intent();
        t.putExtra(new String("retour"), "nope");
        setResult(Activity.RESULT_OK, t);
        Button mButton;
        mButton = findViewById(R.id.button_submit);
        Bundle b = getIntent().getExtras();
        if(b != null) {
            modif = true;
            reserv = b.getString("key").toLowerCase();
            mEdittitre.setText("Modifier la " + reserv);
            mButton.setText("Modifier");
            ArrayList<String> reservl = db.get_reservation(Integer.parseInt(reserv.replaceAll("[^0-9]", "")));
            mEdit1.setText(reservl.get(0));
            mEdit2.setText(reservl.get(1));
            mEdit3.setText(reservl.get(3).substring(11));
            mEdit4.setText(reservl.get(3).substring(0,10));
            mEdit.setText(reservl.get(4).substring(11));
            mEdit5.setText(reservl.get(4).substring(0,10));
        }

        mEdit3.setInputType(InputType.TYPE_NULL);
        mEdit3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY)+1;
                int minutes = cldr.get(Calendar.MINUTE);
                // time picker dialog
                picker = new TimePickerDialog(addmod.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                String hour = "";
                                String minute = "";
                                if(sHour < 10){hour = "0";}
                                if(sMinute < 10){minute = "0";}
                                mEdit3.setText(hour + sHour + ":" + minute + sMinute);
                            }
                        }, hour, minutes, true);
                picker.show();
            }
        });

        mEdit.setInputType(InputType.TYPE_NULL);
        mEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY)+1;
                int minutes = cldr.get(Calendar.MINUTE);
                // time picker dialog
                picker = new TimePickerDialog(addmod.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                String hour = "";
                                String minute = "";
                                if(sHour < 10){hour = "0";}
                                if(sMinute < 10){minute = "0";}
                                mEdit.setText(hour + sHour + ":" + minute + sMinute);
                            }
                        }, hour, minutes, true);
                picker.show();
            }
        });

        mEdit4.setInputType(InputType.TYPE_NULL);
        mEdit4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                dpicker = new DatePickerDialog(addmod.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                monthOfYear++;
                                String month = "";
                                String day = "";
                                if(monthOfYear < 10){month = "0";}
                                if(dayOfMonth < 10){day = "0";}
                                mEdit4.setText(day + dayOfMonth + "/" + month + monthOfYear + "/" + year);
                            }
                        }, year, month, day);
                dpicker.show();
            }
        });

        mEdit5.setInputType(InputType.TYPE_NULL);
        mEdit5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                dpicker = new DatePickerDialog(addmod.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                monthOfYear++;
                                String month = "";
                                String day = "";
                                if(monthOfYear < 10){month = "0";}
                                if(dayOfMonth < 10){day = "0";}
                                mEdit5.setText(day + dayOfMonth + "/" + month + monthOfYear + "/" + year);
                            }
                        }, year, month, day);
                dpicker.show();
            }
        });

        mButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String mEdits1 = mEdit1.getText().toString();
                String mEdits2 = mEdit2.getText().toString();
                String mEdits3 = mEdit3.getText().toString();
                String mEdits4 = mEdit4.getText().toString();
                String mEdits = mEdit.getText().toString();
                String mEdits5 = mEdit5.getText().toString();
                mEdits1 = mEdits1.replaceAll("[^0-9]", "");
                mEdits2 = mEdits2.replaceAll("[^0-9]", "");
                mEdits3 = mEdits3.replaceAll("[^0-9:]", "");
                mEdits4 = mEdits4.replaceAll("[^0-9/]", "");
                mEdits = mEdits.replaceAll("[^0-9:]", "");
                mEdits5 = mEdits5.replaceAll("[^0-9/]", "");

                if (mEdits2.equals("1") || mEdits2.equals("2")) {
                    if (Integer.parseInt(mEdits1) > 0) {

                        SimpleDateFormat defaultDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

                        String starts1 = mEdits4 + " " + mEdits3 + ":00";
                        String ends1 = mEdits5 + " " + mEdits + ":00";
                        Date locs = new Date();
                        Date loce = new Date();
                        try {
                            locs = defaultDateFormat.parse(starts1);
                            loce = defaultDateFormat.parse(ends1);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        if (locs.compareTo(loce) < 0) {
                            boolean dispo;
                            ArrayList<String> reservl = new ArrayList<String>();
                            String starts = mEdits4 + " " + mEdits3;
                            String ends = mEdits5 + " " + mEdits;
                            Date end = new Date();
                            Date start = new Date();
                            try {
                                start = defaultDateFormat.parse(starts);
                                end = defaultDateFormat.parse(ends);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            if(modif) {
                                reservl.add(mEdits1);
                                reservl.add(mEdits2);
                                reservl.add(starts);
                                reservl.add(ends);
                                reservl.add(reserv.replaceAll("[^0-9]", ""));
                                dispo = db.check_dispo_modif(Integer.parseInt(mEdits2), start, end, Integer.parseInt(reserv.replaceAll("[^0-9]", "")));
                            }else{dispo = db.check_dispo(Integer.parseInt(mEdits2), start, end);}
                            if (dispo) {
                                if(modif){
                                    db.modif_reservation(reservl);
                                    Toast.makeText(getApplicationContext(), "Réservation modifiée", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(addmod.this, reservactivity.class);
                                    Bundle b = new Bundle();
                                    b.putInt("finish", 6); //Your id
                                    intent.putExtras(b); //Put your id to your next Intent
                                    startActivity(intent);
                                }else {
                                    reservation reserv = new reservation();
                                    reserv.setUserid(userlog.userlogvar);
                                    reserv.setDistance(Integer.parseInt(mEdits1));
                                    reserv.setVoiture(Integer.parseInt(mEdits2));
                                    reserv.setLocstart(starts);
                                    reserv.setLocend(ends);
                                    db.add_reservation(reserv);
                                    Toast.makeText(getApplicationContext(), "Réservation ajoutée", Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(addmod.this, reservactivity.class));
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Créneau indisponible", Toast.LENGTH_LONG).show();
                                mEdit3.setText("");
                                mEdit4.setText("");
                                mEdit.setText("");
                                mEdit5.setText("");
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Format ou ordre des dates incorrect", Toast.LENGTH_LONG).show();
                            mEdit3.setText("");
                            mEdit4.setText("");
                            mEdit.setText("");
                            mEdit5.setText("");
                        }

                    } else {
                        Toast.makeText(getApplicationContext(), "Distance invalide", Toast.LENGTH_LONG).show();
                        mEdit1.setText("");
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Veuillez entrez 1 ou 2 pour le numéro de voiture", Toast.LENGTH_LONG).show();
                    mEdit2.setText("");
                }
            }
        });
    }
}