package com.example.projet_reservation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDelegate;

public class pagemenu extends Activity {

    userlog userlog;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pagemenu);
        Button see = findViewById(R.id.button_view);
        Button deco = findViewById(R.id.button_view2);
        Button add = findViewById(R.id.button_add);
        Button del = findViewById(R.id.button_del);
        Button mod = findViewById(R.id.button_modify);
        Button VOIT = findViewById(R.id.button_voit);
        TextView title = findViewById(R.id.textView_userlog);
        TextView title2 = findViewById(R.id.textView_userlog2);
        if(userlog.userlogvar.equals("admin")){title.setText(userlog.userlogvar);title2.setText("Accès privilégié");
        }else{title.setText(userlog.userlogvar);}

        see.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(pagemenu.this, reservactivity.class);
                Bundle b = new Bundle();
                b.putInt("key", 1); //Your id
                intent.putExtras(b); //Put your id to your next Intent
                startActivity(intent);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(pagemenu.this, addmod.class));
            }
        });
        del.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(pagemenu.this, reservactivity.class);
                Bundle b = new Bundle();
                b.putInt("key", 3); //Your id
                intent.putExtras(b); //Put your id to your next Intent
                startActivity(intent);
            }
        });
        mod.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(pagemenu.this, reservactivity.class);
                Bundle b = new Bundle();
                b.putInt("key", 4); //Your id
                intent.putExtras(b); //Put your id to your next Intent
                startActivity(intent);
            }
        });
        deco.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Vous avez été déconnecté", Toast.LENGTH_LONG).show();
                finish();
                startActivity(new Intent(pagemenu.this, Main_Activity.class));
            }
        });

        VOIT.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(pagemenu.this, listevoitures.class));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String s = data.getStringExtra(new String("retour"));
        if(s.compareTo("nope") != 0){Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();}
    }
}