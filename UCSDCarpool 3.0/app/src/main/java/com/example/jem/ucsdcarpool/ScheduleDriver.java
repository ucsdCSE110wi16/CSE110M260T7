package com.example.jem.ucsdcarpool;

/**
 * Created by xiejingwen on 3/4/16.
 */
public class ScheduleDriver {
    private String passenger_name;
    private String pick_loc;
    private String destination;
    private int day;
    private int month;
    private int hour;
    private int minute;


    public ScheduleDriver(int month, String passenger_name, String pick_loc, String destination, int day, int hour, int minute) {
        this.month = month;
        this.passenger_name = passenger_name;
        this.pick_loc = pick_loc;
        this.destination = destination;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
    }


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


    // comparetor when first schedule is greater than second return 1
    // when first schedule is lesser than second return -1
    // when first schedule is same as second return 0
    private int comparetor(ScheduleDriver s1, ScheduleDriver s2)
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