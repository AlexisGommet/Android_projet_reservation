package com.example.projet_reservation;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class listevoitures extends ListActivity {

    DBHandler db = new DBHandler(listevoitures.this);
    userlog userlog;

    protected ArrayAdapter<String> monAdaptateur;
    private ArrayList<String> listereservations;
    AlertDialog.Builder builder;
    int idbut = 0;
    int idfin = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listevoitures);
        TextView titre = (TextView) findViewById(R.id.textView_titre2);
        Button add = findViewById(R.id.button_modify);
        ArrayList<String> reservations = db.get_voit();
        builder = new AlertDialog.Builder(this);


        listereservations = new ArrayList<String>();
        for(int i = 0; i < reservations.size(); i = i+4) {
            listereservations.add("Voiture : " + reservations.get(i) + " " + reservations.get(i+1) + " " + reservations.get(i+2) + " " + reservations.get(i+3));
        }
        this.monAdaptateur = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, listereservations);
        super.setListAdapter(this.monAdaptateur);

        add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(listevoitures.this, addvoit.class);
                startActivity(intent);
            }
        });
        ListView lv = getListView();

        getListView().setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent,
                                                   View view, int position, long id) {

                        String res1 = (String) (lv.getItemAtPosition(position));
                        ArrayList<String> login = db.get_unevoit(Integer.parseInt(res1.substring(res1.indexOf(":")+2,11).replaceAll("[^0-9]", "")));

                        builder.setMessage("Êtes-vous sûr de vouloir supprimer la voiture " + login.get(0) + "?")
                                .setCancelable(false)
                                .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        listereservations.remove(position);
                                        monAdaptateur.notifyDataSetChanged();
                                        db.del_voit(Integer.parseInt(login.get(0)));
                                        Toast.makeText(getApplicationContext(), "voiture " + login.get(0) + " supprimée", Toast.LENGTH_LONG).show();
                                        dialog.cancel();
                                    }
                                })
                                .setNegativeButton("Non", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                        //Creating dialog box
                        AlertDialog alert1 = builder.create();
                        //Setting the title manually
                        alert1.setTitle("Supprimer");
                        alert1.show();
                        return true;
                    }
                }
        );
    }
}


