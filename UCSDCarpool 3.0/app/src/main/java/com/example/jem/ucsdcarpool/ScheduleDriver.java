package com.example.jem.ucsdcarpool;

/**
 * Created by xiejingwen on 3/4/16.
 */
public class ScheduleDriver {
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

    public ScheduleDriver(String name, String address, String detail) {
        super();
        this.schedulename = name;
        this.address = address;
        this.scheduledetail = detail;


    }




}