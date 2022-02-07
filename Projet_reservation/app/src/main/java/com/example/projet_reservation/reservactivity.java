package com.example.projet_reservation;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class reservactivity extends ListActivity {

    DBHandler db = new DBHandler(reservactivity.this);
    userlog userlog;

    protected ArrayAdapter<String> monAdaptateur;
    private ArrayList<String> listereservations;
    AlertDialog.Builder builder;
    int idbut = 0;
    int idfin = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservactivity);
        TextView titre = (TextView) findViewById(R.id.textView_titre2);
        builder = new AlertDialog.Builder(this);
        Bundle b = getIntent().getExtras();
        ArrayList<String> reservations = db.get_list();
        if(b != null)
            try {
                idbut = b.getInt("key");
                idfin = b.getInt("finish");
            }catch (Exception e){}
        if(idfin == 6) {
            finish();
            Intent intent = new Intent(reservactivity.this, reservactivity.class);
            Bundle b1 = new Bundle();
            b1.putInt("key", 4); //Your id
            intent.putExtras(b1); //Put your id to your next Intent
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        if(idbut == 3)
            titre.setText("Supprimer une réservation");
        else if(idbut == 4)
            titre.setText("Modifier une réservation");

        listereservations = new ArrayList<String>();
        for(int i = 0; i < reservations.size(); i++) {
            listereservations.add("Réservation " + reservations.get(i));
        }
        this.monAdaptateur = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, listereservations);
        super.setListAdapter(this.monAdaptateur);
        ListView lv = getListView();


        // gestion du clic long:
        getListView().setOnItemLongClickListener(
            new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent,
                                               View view, int position, long id) {
                    switch (idbut) {

                        case 3:

                            String res1 = (String) (lv.getItemAtPosition(position));
                            ArrayList<String> login = db.get_reservation(Integer.parseInt(res1.replaceAll("[^0-9]", "")));

                            builder.setMessage("Êtes-vous sûr de vouloir supprimer la réservation " + login.get(5) + "?")
                                    .setCancelable(false)
                                    .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            if (userlog.userlogvar.compareTo("admin") == 0 || userlog.userlogvar.compareTo(login.get(2)) == 0) {
                                                listereservations.remove(position);
                                                monAdaptateur.notifyDataSetChanged();
                                                db.del_res(Integer.parseInt(login.get(5)));
                                                Toast.makeText(getApplicationContext(), "Réservation " + login.get(5) + " supprimée", Toast.LENGTH_LONG).show();
                                                dialog.cancel();
                                            } else {
                                                Toast.makeText(getApplicationContext(), "Vous n'avez pas accès à cette réservation", Toast.LENGTH_LONG).show();
                                            }
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
                            break;

                        case 4:

                            String res2 = (String) (lv.getItemAtPosition(position));
                            ArrayList<String> login2 = db.get_reservation(Integer.parseInt(res2.replaceAll("[^0-9]", "")));
                            if (userlog.userlogvar.compareTo("admin") == 0 || userlog.userlogvar.compareTo(login2.get(2)) == 0) {
                                Intent intent = new Intent(reservactivity.this, addmod.class);
                                Bundle b = new Bundle();
                                b.putString("key", res2); //Your id
                                intent.putExtras(b); //Put your id to your next Intent
                                startActivity(intent);
                            } else {
                                Toast.makeText(getApplicationContext(), "Vous n'avez pas accès à cette réservation", Toast.LENGTH_LONG).show();
                            }
                            break;

                        default:

                            int num = Integer.parseInt(listereservations.get(position).replaceAll("[^0-9]", ""));
                            ArrayList<String> res = db.get_reservation(num);
                            builder.setMessage("Distance: " + res.get(0) + " km\nVoiture: " + res.get(1) + "\nRéservé par: " + res.get(2) + "\nDébut de réservation: " + res.get(3) + "\nFin de réservation: " + res.get(4))
                                    .setCancelable(false)
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    })
                                    .setNegativeButton("Retour", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    });
                            //Creating dialog box
                            AlertDialog alert = builder.create();
                            //Setting the title manually
                            alert.setTitle("Réservation " + res.get(5));
                            alert.show();
                            break;
                    }
                    return true;
                }
            }
        );
    }
    // gestion du simple clic:
    public void onListItemClick(ListView liste, View vue, int position, long id) {

        int num = Integer.parseInt(listereservations.get(position).replaceAll("[^0-9]", ""));
        ArrayList<String> res = db.get_reservation(num);
        builder.setMessage("Distance: "+ res.get(0) +" km\nVoiture: "+ res.get(1) +"\nRéservé par: "+ res.get(2) +"\nDébut de réservation: "+ res.get(3) +"\nFin de réservation: "+ res.get(4))
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                })
                .setNegativeButton("Retour", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        //Creating dialog box
        AlertDialog alert = builder.create();
        //Setting the title manually
        alert.setTitle("Réservation " + res.get(5));
        alert.show();
    }
}

