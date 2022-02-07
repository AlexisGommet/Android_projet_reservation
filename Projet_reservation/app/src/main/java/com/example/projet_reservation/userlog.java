package com.example.projet_reservation;

import android.app.Application;

public class userlog extends Application {

    public static String userlogvar;

    public String getuserlogvar() {
        return userlogvar;
    }

    public void setuserlogvar(String userlogvar) {
        this.userlogvar = userlogvar;
    }
}
