package com.example.projet_reservation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Main_Activity extends AppCompatActivity {

    DBHandler db = new DBHandler(Main_Activity.this);
    public userlog userlog = new userlog();

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Réservation de voiture de l'ESAIP");
        EditText mEdit = (EditText) findViewById(R.id.editText1);
        EditText mEdit2 = (EditText) findViewById(R.id.editText2);
        TextView acc = findViewById(R.id.textView_newacc);
        acc.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        ImageButton rev;
        rev = findViewById(R.id.button_reveal);
        Button mButton;
        mButton = findViewById(R.id.button_submit);

        mButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String mEdits = mEdit.getText().toString().trim();
                String mEdits2 = mEdit2.getText().toString();
                boolean found = db.check_user(mEdits);
                String mdp = "";
                boolean mdpgood = false;
                if(found){
                    mdp = db.get_mdp(mEdits);
                }
                if(mEdits2.compareTo(mdp) == 0) {
                    mdpgood = true;
                }
                if((found) && (mdpgood)) {
                    userlog.userlogvar = mEdits;
                    startActivity(new Intent(Main_Activity.this, pagemenu.class));
                } else if(!found) {
                    Toast.makeText(getApplicationContext(), "Nom d'utilisateur ou Mot de passe non valide", Toast.LENGTH_LONG).show();
                    mEdit.setText("");
                    mEdit2.setText("");
                } else if(!mdpgood){
                    Toast.makeText(getApplicationContext(), "Nom d'utilisateur ou Mot de passe non valide", Toast.LENGTH_LONG).show();
                    mEdit.setText("");
                    mEdit2.setText("");
                }
            }
        });

        acc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(Main_Activity.this, register.class));
            }
        });

        rev.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch ( event.getAction() ) {
                    case MotionEvent.ACTION_UP:
                        mEdit2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        break;
                    case MotionEvent.ACTION_DOWN:
                        mEdit2.setInputType(InputType.TYPE_CLASS_TEXT);
                        break;
                }
                return true;
            }
        });
    }
}
