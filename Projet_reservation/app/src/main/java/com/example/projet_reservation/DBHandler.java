package com.example.projet_reservation;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DBHandler extends SQLiteOpenHelper {

    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "reservationdb";

    // below int is our database version
    private static final int DB_VERSION = 1;

    // below variable is for our table name.
    private static final String TABLE_NAME_USERS = "users";

    // below variable is for our id column.
    private static final String ID_COL_USERS = "id";

    // below variable is for our course name column
    private static final String ID_USER = "username";

    // below variable id for our course duration column.
    private static final String MDP_USER = "passwd";

    // below variable is for our table name.
    private static final String TABLE_NAME_RES = "réservation";

    // below variable is for our id column.
    private static final String ID_COL_RES = "id";

    // below variable is for our course name column
    private static final String ID_RES_USER= "username";

    // below variable id for our course duration column.
    private static final String VOIT_RES = "voiture";

    // below variable id for our course duration column.
    private static final String DIST_RES = "distance";

    // below variable id for our course duration column.
    private static final String LOCS_RES = "date_début";

    // below variable id for our course duration column.
    private static final String LOCE_RES = "date_fin";

    // below variable is for our table name.
    private static final String TABLE_NAME_VOIT = "voit";

    // below variable is for our id column.
    private static final String ID_COL_VOI = "id";

    // below variable is for our course name column
    private static final String ID_TYPE = "type";

    // below variable is for our course name column
    private static final String ID_PLACE = "nbrplaces";

    // below variable id for our course duration column.
    private static final String COUT = "cout";

    // creating a constructor for our database handler.
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {

        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String query = "CREATE TABLE " + TABLE_NAME_RES + " ("
                + ID_COL_RES + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ID_RES_USER + " TEXT,"
                + VOIT_RES + " INTEGER,"
                + DIST_RES + " INTEGER,"
                + LOCS_RES + " TEXT,"
                + LOCE_RES + " TEXT)";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);

        String query2 = "CREATE TABLE " + TABLE_NAME_USERS + " ("
                + ID_COL_USERS + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ID_USER + " TEXT,"
                + MDP_USER + " TEXT)";

        db.execSQL(query2);

        String query3 = "CREATE TABLE " + TABLE_NAME_VOIT + " ("
                + ID_COL_VOI + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ID_TYPE + " TEXT,"
                + ID_PLACE + " INTEGER,"
                + COUT + " INTEGER)";

        db.execSQL(query3);

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(ID_USER, "admin");
        values.put(MDP_USER, "password");

        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME_USERS, null, values);

    }

    // this method is use to add new course to our sqlite database.
    public void addUser(String id_user, String user_pass) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(ID_USER, id_user);
        values.put(MDP_USER, user_pass);

        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME_USERS, null, values);
    }

    public boolean check_user(String id_user) {

        boolean found = false;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        String sql ="SELECT "+ ID_USER +" FROM "+ TABLE_NAME_USERS +" WHERE "+ID_USER+"=\""+ id_user + "\"";
        cursor= db.rawQuery(sql,null);

        if(cursor.getCount() > 0) {
            found = true;
        }
        cursor.close();
        return found;
    }

    public String get_mdp(String id_user) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        String sql ="SELECT * FROM "+ TABLE_NAME_USERS +" WHERE "+ ID_USER +"=\""+ id_user + "\"";
        cursor= db.rawQuery(sql,null);

        cursor.moveToFirst();
        @SuppressLint("Range") String mdp = cursor.getString(cursor.getColumnIndex(MDP_USER));
        cursor.close();
        return mdp;
    }

    public ArrayList<String> get_list() {

        ArrayList<String> arrayList = new ArrayList<>();

        String select_query= "SELECT "+ ID_COL_RES +" FROM " + TABLE_NAME_RES;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(select_query, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                arrayList.add(String.valueOf(cursor.getInt(0)));
            }while (cursor.moveToNext());
        }
        return arrayList;
    }

    public ArrayList<String> get_voit() {

        ArrayList<String> arrayList = new ArrayList<>();

        String select_query= "SELECT * FROM " + TABLE_NAME_VOIT;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(select_query, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                arrayList.add(String.valueOf(cursor.getInt(0)));
                arrayList.add(cursor.getString(1));
                arrayList.add(String.valueOf(cursor.getInt(2)));
                arrayList.add(String.valueOf(cursor.getInt(3)));
            }while (cursor.moveToNext());
        }
        return arrayList;
    }

    public ArrayList<String> get_reservation(int num) {

        ArrayList<String> arrayList = new ArrayList<>();

        String select_query= "SELECT * FROM "+ TABLE_NAME_RES +" WHERE "+ ID_COL_RES +"=\""+ num + "\"";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(select_query, null);

        cursor.moveToFirst();

        arrayList.add(String.valueOf(cursor.getInt(3)));
        arrayList.add(String.valueOf(cursor.getInt(2)));
        arrayList.add(cursor.getString(1));
        arrayList.add(cursor.getString(4));
        arrayList.add(cursor.getString(5));
        arrayList.add(String.valueOf(cursor.getInt(0)));

        return arrayList;
    }

    public ArrayList<String> get_unevoit(int num) {

        ArrayList<String> arrayList = new ArrayList<>();

        String select_query= "SELECT * FROM "+ TABLE_NAME_VOIT +" WHERE "+ ID_COL_VOI +"=\""+ num + "\"";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(select_query, null);

        cursor.moveToFirst();

        arrayList.add(String.valueOf(cursor.getInt(0)));
        arrayList.add(cursor.getString(1));
        arrayList.add(String.valueOf(cursor.getInt(2)));
        arrayList.add(String.valueOf(cursor.getInt(3)));

        return arrayList;
    }

    public void add_reservation(reservation reserv) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(ID_RES_USER, reserv.getUserid());
        values.put(VOIT_RES, reserv.getVoiture());
        values.put(DIST_RES, reserv.getDistance());
        values.put(LOCS_RES, reserv.getLocstart());
        values.put(LOCE_RES, reserv.getLocend());

        db.insert(TABLE_NAME_RES, null, values);

    }


    public void modif_reservation(ArrayList<String> reserv) {

        SQLiteDatabase db = this.getWritableDatabase();

        String query= "UPDATE "+ TABLE_NAME_RES +" SET "+ DIST_RES +"=\""+ reserv.get(0) +"\","+ VOIT_RES +"=\""+ reserv.get(1) +"\","+ LOCS_RES +"=\""+ reserv.get(2) +"\","+ LOCE_RES +"=\""+ reserv.get(3) +"\""+" WHERE "+ ID_COL_RES +"=\""+ reserv.get(4) + "\"";

        db.execSQL(query);
    }

    public void add_voit(ArrayList<String> reserv) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(ID_TYPE, reserv.get(0));
        values.put(ID_PLACE, Integer.parseInt(reserv.get(1)));
        values.put(COUT, Integer.parseInt(reserv.get(2)));

        db.insert(TABLE_NAME_VOIT, null, values);
    }

    public void del_res(int num_res) {

        SQLiteDatabase db = this.getWritableDatabase();

        String query= "DELETE FROM "+ TABLE_NAME_RES +" WHERE "+ ID_COL_RES +"=\""+ num_res + "\"";

        db.execSQL(query);
    }

    public void del_voit(int num_res) {

        SQLiteDatabase db = this.getWritableDatabase();

        String query= "DELETE FROM "+ TABLE_NAME_VOIT +" WHERE "+ ID_COL_VOI +"=\""+ num_res + "\"";

        db.execSQL(query);
    }

    public boolean check_dispo_modif(int voiture, Date locstart, Date locend, int reserv) {

        boolean dispo = true;

        String select_query= "SELECT "+ LOCS_RES +","+ LOCE_RES +","+ ID_COL_RES +" FROM "+ TABLE_NAME_RES +" WHERE "+ VOIT_RES +"=\""+ voiture + "\"";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(select_query, null);

        String startstart = "";
        String endend = "";
        String startend = "";
        String endstart = "";
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                int resmod = Integer.parseInt(cursor.getString(2));
                if(resmod != reserv){
                    SimpleDateFormat defaultDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

                    String start2 = cursor.getString(0);
                    String end2 = cursor.getString(1);
                    Date locstart2 = new Date();
                    Date locend2 = new Date();
                    try {
                        locstart2 = defaultDateFormat.parse(start2);
                        locend2 = defaultDateFormat.parse(end2);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if(locstart.compareTo(locstart2) < 0){startstart = "before";}
                    else if(locstart.compareTo(locstart2) > 0){startstart = "after";}
                    else if(locstart.compareTo(locstart2) == 0){startstart = "same";}
                    if(locend.compareTo(locend2) < 0){endend = "before";}
                    else if(locend.compareTo(locend2) > 0){endend = "after";}
                    else if(locend.compareTo(locend2) == 0){endend = "same";}
                    if(locstart.compareTo(locend2) < 0){startend = "before";}
                    else if(locstart.compareTo(locend2) > 0){startend = "after";}
                    else if(locstart.compareTo(locend2) == 0){startend = "same";}
                    if(locend.compareTo(locstart2) < 0){endstart = "before";}
                    else if(locend.compareTo(locstart2) > 0){endstart = "after";}
                    else if(locend.compareTo(locstart2) == 0){endstart = "same";}
                    if(startstart.equals("same") || endend.equals("same") || (startstart.equals("before") && endstart.equals("after") && endend.equals("before")) || (startstart.equals("after") && startend.equals("before") && endend.equals("after")) || (startstart.equals("after") && startend.equals("before") && endend.equals("before") && endstart.equals("after"))){
                        dispo = false;
                    }
                }
            }while (cursor.moveToNext());
        }
        return dispo;
    }

    public boolean check_dispo(int voiture, Date locstart, Date locend) {

        boolean dispo = true;

        String select_query= "SELECT "+ LOCS_RES +","+ LOCE_RES +","+ ID_COL_RES +" FROM "+ TABLE_NAME_RES +" WHERE "+ VOIT_RES +"=\""+ voiture + "\"";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(select_query, null);

        String startstart = "";
        String endend = "";
        String startend = "";
        String endstart = "";
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                SimpleDateFormat defaultDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

                String start2 = cursor.getString(0);
                String end2 = cursor.getString(1);
                Date locstart2 = new Date();
                Date locend2 = new Date();
                try {
                    locstart2 = defaultDateFormat.parse(start2);
                    locend2 = defaultDateFormat.parse(end2);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if(locstart.compareTo(locstart2) < 0){startstart = "before";}
                else if(locstart.compareTo(locstart2) > 0){startstart = "after";}
                else if(locstart.compareTo(locstart2) == 0){startstart = "same";}
                if(locend.compareTo(locend2) < 0){endend = "before";}
                else if(locend.compareTo(locend2) > 0){endend = "after";}
                else if(locend.compareTo(locend2) == 0){endend = "same";}
                if(locstart.compareTo(locend2) < 0){startend = "before";}
                else if(locstart.compareTo(locend2) > 0){startend = "after";}
                else if(locstart.compareTo(locend2) == 0){startend = "same";}
                if(locend.compareTo(locstart2) < 0){endstart = "before";}
                else if(locend.compareTo(locstart2) > 0){endstart = "after";}
                else if(locend.compareTo(locstart2) == 0){endstart = "same";}
                if(startstart.equals("same") || endend.equals("same") || (startstart.equals("before") && endstart.equals("after") && endend.equals("before")) || (startstart.equals("after") && startend.equals("before") && endend.equals("after")) || (startstart.equals("after") && startend.equals("before") && endend.equals("before") && endstart.equals("after"))){
                    dispo = false;
                }
            }while (cursor.moveToNext());
        }
        return dispo;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_RES);
        onCreate(db);
    }
}
