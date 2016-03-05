package com.example.jem.ucsdcarpool;


import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Schedule {
    private String passenger_name;
    private String driver_name;
    private String pick_loc;
    private String destination;
    private int day;
    private int month;
    private int hour;
    private int minute;

    public Schedule(String passenger_name, String driver_name, String pick_loc, String destination,
                    int day, int month, int hour, int minute) {
        this.passenger_name = passenger_name;
        this.driver_name = driver_name;
        this.pick_loc = pick_loc;
        this.destination = destination;
        this.day = day;
        this.month = month;
        this.hour = hour;
        this.minute = minute;
    }

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


    // comparetor when first schedule is greater than second return 1
    // when first schedule is lesser than second return -1
    // when first schedule is same as second return 0
    private int comparetor(Schedule s1, Schedule s2)
    {
        if(s1.getMonth() > s2.getMonth())
        {
            return 1;
        }else if(s1.getMonth() < s2.getMonth())
        {
            return  -1;
        }else{

            if (s1.getDay() > s2.getDay())
            {
                return 1;
            }else if(s1.getDay() < s2.getDay())
            {
                return -1;
            }else{

                if(s1.getHour() > s2.getHour())
                {
                    return  1;
                }else if(s1.getHour() > s2.getHour())
                {
                    return -1;
                }else{

                    if (s1.getMinute() > s2.getMinute())
                    {
                        return 1;
                    }else if (s1.getMinute() < s2.getMinute())
                    {
                        return -1;
                    }else{
                        return 0;
                    }
                }
            }


        }


    }
}