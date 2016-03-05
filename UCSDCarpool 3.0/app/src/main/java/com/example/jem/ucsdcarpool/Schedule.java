package com.example.jem.ucsdcarpool;


import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Schedule {
    String schedulename;
    String address;
    String scheduledetail;




    public String getName() {
        return schedulename;
    }

    public void setName(String name) {
        this.schedulename = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getScheduledetail() {
        return scheduledetail;
    }

    public void setScheduledetail(String detail) {
        this.scheduledetail = detail;
    }

    public Schedule(String name, String address, String detail) {
        super();
        this.schedulename = name;
        this.address = address;
        this.scheduledetail = detail;


    }




}