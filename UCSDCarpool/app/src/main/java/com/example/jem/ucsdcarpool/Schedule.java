package com.example.jem.ucsdcarpool;


public class Schedule {

    // create all the variables
    private String passenger_name;
    private String driver_name;
    private String passenger_uid;
    private String driver_uid;
    private String pick_loc;
    private String destination;
    private int day;
    private int month;
    private int hour;
    private int minute;
    private boolean taken;
    private boolean deleted;

    // constructor
    public Schedule(String passenger_name, String driver_name, String passenger_uid, String driver_uid, String pick_loc, String destination, int day, int month, int hour, int minute) {
        this.passenger_name = passenger_name;
        this.driver_name = driver_name;
        this.passenger_uid = passenger_uid;
        this.driver_uid = driver_uid;
        this.pick_loc = pick_loc;
        this.destination = destination;
        this.day = day;
        this.month = month;
        this.hour = hour;
        this.minute = minute;
    }

    // all geter and setter methods
    public String getPassenger_name() {
        return passenger_name;
    }

    public String getDriver_name() {
        return driver_name;
    }

    public String getPick_loc() {
        return pick_loc;
    }

    public String getDestination() {
        return destination;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setPassenger_name(String passenger_name) {
        this.passenger_name = passenger_name;
    }

    public void setDriver_name(String driver_name) {
        this.driver_name = driver_name;
    }

    public void setPick_loc(String pick_loc) {
        this.pick_loc = pick_loc;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }


    public String getPassenger_uid() {
        return passenger_uid;
    }

    public void setPassenger_uid(String passenger_uid) {
        this.passenger_uid = passenger_uid;
    }

    public String getDriver_uid() {
        return driver_uid;
    }

    public void setDriver_uid(String driver_uid) {
        this.driver_uid = driver_uid;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isTaken() {
        return taken;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }
}