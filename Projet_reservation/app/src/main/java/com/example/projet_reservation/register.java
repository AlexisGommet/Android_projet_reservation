package com.example.projet_reservation;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDelegate;

import java.nio.charset.StandardCharsets;

public class register extends Activity {

    DBHandler db = new DBHandler(register.this);
    @SuppressLint("ClickableViewAccessibility")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        EditText mEdit = (EditText) findViewById(R.id.editText1);
        EditText mEdit2 = (EditText) findViewById(R.id.editText2);
        EditText mEdit3 = (EditText) findViewById(R.id.editText3);
        Button mButton;
        mButton = findViewById(R.id.button_submit);
        ImageButton rev;
        rev = findViewById(R.id.button_reveal2);
        ImageButton rev2;
        rev2 = findViewById(R.id.button_reveal3);

        mButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String mEdits = mEdit.getText().toString().trim();
                String mEdits2 = mEdit2.getText().toString();
                String mEdits3 = mEdit3.getText().toString();
                boolean found = db.check_user(mEdits);
                if(found) {
                    Toast.makeText(getApplicationContext(), "Nom d'utilisateur indisponible", Toast.LENGTH_LONG).show();
                    mEdit.setText("");
                } else if(mEdits2.compareTo(mEdits3) != 0) {
                    Toast.makeText(getApplicationContext(), "Mots de passe différents", Toast.LENGTH_LONG).show();
                    mEdit2.setText("");
                    mEdit3.setText("");
                }else{
                    db.addUser(mEdits, mEdits2);
                    userlog.userlogvar = mEdits;
                    startActivity(new Intent(register.this, pagemenu.class));
                }
            }
        });

        rev.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch ( event.getAction() ) {
                    case MotionEvent.ACTION_UP:
                        mEdit2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        mEdit3.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        break;
                    case MotionEvent.ACTION_DOWN:
                        mEdit2.setInputType(InputType.TYPE_CLASS_TEXT);
                        mEdit3.setInputType(InputType.TYPE_CLASS_TEXT);
                        break;
                }
                return true;
            }
        });

        rev2.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch ( event.getAction() ) {
                    case MotionEvent.ACTION_UP:
                        mEdit2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        mEdit3.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        break;
                    case MotionEvent.ACTION_DOWN:
                        mEdit2.setInputType(InputType.TYPE_CLASS_TEXT);
                        mEdit3.setInputType(InputType.TYPE_CLASS_TEXT);
                        break;
                }
                return true;
            }
        });
    }
}