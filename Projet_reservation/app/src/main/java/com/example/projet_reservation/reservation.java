package com.example.projet_reservation;

public class reservation {

    private int distance;
    private int voiture;
    private String userid;
    private String locstart;
    private String locend;

    public int getVoiture() { return voiture; }

    public void setVoiture(int voiture) { this.voiture = voiture; }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getLocstart() {
        return locstart;
    }

    public void setLocstart(String locstart) {
        this.locstart = locstart;
    }

    public String getLocend() {
        return locend;
    }

    public void setLocend(String locend) {
        this.locend = locend;
    }
}
