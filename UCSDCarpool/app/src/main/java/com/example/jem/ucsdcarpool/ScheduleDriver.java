package com.example.jem.ucsdcarpool;

/**
 * Created by xiejingwen on 3/4/16.
 */
public class ScheduleDriver{

    // creat variables
    private String passenger_name;
    private String pick_loc;
    private String destination;
    private String passenger_uid;
    private int day;
    private int month;
    private int hour;
    private int minute;

    // constructor
    public ScheduleDriver(String passenger_name, int minute, String destination, String pick_loc, String passenger_uid, int day, int month, int hour) {
        this.passenger_name = passenger_name;
        this.minute = minute;
        this.destination = destination;
        this.pick_loc = pick_loc;
        this.passenger_uid = passenger_uid;
        this.day = day;
        this.month = month;
        this.hour = hour;
    }


    // call getter and setter methods
    public String getPassenger_name() {
        return passenger_name;
    }

    public void setPassenger_name(String passenger_name) {
        this.passenger_name = passenger_name;
    }

    public String getPick_loc() {
        return pick_loc;
    }

    public void setPick_loc(String pick_loc) {
        this.pick_loc = pick_loc;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
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


}