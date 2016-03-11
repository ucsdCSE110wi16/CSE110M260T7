package com.example.jem.ucsdcarpool;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;

/**
 * Created by Jem on 3/3/16.
 */
public class Find_schedule_driver extends Activity {

    // variables
    private Calendar calendar;
    private int month;
    private int day;

    private Firebase mRef;
        ListView userList;
        Find_schedule_driver_adaptor userAdapter;
        ArrayList<ScheduleDriver> userArray = new ArrayList<ScheduleDriver>();


@Override
protected void onCreate(Bundle savedInstanceState) {
    // create the view
    super.onCreate(savedInstanceState);
    setContentView(R.layout.find_schedule_driver);

    Firebase.setAndroidContext(this);
    mRef = new Firebase("https://ucsdcarpool.firebaseio.com");

    Button back = (Button) findViewById(R.id.back_find_schedule_driver);
    // get time
    calendar = Calendar.getInstance();

    month = calendar.get(Calendar.MONTH) + 1;
    day = calendar.get(Calendar.DAY_OF_MONTH);

    // setup back button listener
    back.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Find_schedule_driver.this, Menu.class);
            startActivity(intent);
        }
    });

    /**
     * @TODO :ADD INFORMATION FROM DATABASE TO THE BELOW ARRAYLIST
     */

    // update value to firebase
    Firebase findSche = mRef.child("schedules/schedule_id");
    final String user_uid = mRef.getAuth().getUid();

    findSche.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {

            // traverse firebase children
            for(DataSnapshot snap : dataSnapshot.getChildren())
            {
                // check whether meet the save uid
                String passenger_uid = snap.child("passenger_uid").getValue(String.class);
                if(snap.child("schedule_taken").getValue().toString().equals("false") && snap.child("schedule_deleted").getValue().toString().equals("false"))
                {
                    if (passenger_uid.equals(user_uid))
                    {

                    }else {
                        System.out.println("start to check time");
                        String passenger_name = snap.child("passenger_name").getValue(String.class);
                        String destination = snap.child("destination").getValue(String.class);
                        String pick_loc = snap.child("pickup_location").getValue(String.class);
                        int mon = snap.child("schedule_month").getValue(int.class);
                        int da = snap.child("schedule_day").getValue(int.class);
                        int hour = snap.child("schedule_hour").getValue(int.class);
                        int minute = snap.child("schedule_minutes").getValue(int.class);

                        System.out.println("the current month: " + month + " the current day: " + day
                                + " the schedule month: " + mon + " the schedule day: " + da);

                        int minu = calendar.get(Calendar.MINUTE);
                        int hou = calendar.get(Calendar.HOUR);

                        // check time
                        if(mon < month)
                        {

                        }else{
                            if (mon == month && da < day)
                            {}else if(mon > month)
                            {
                                userArray.add(new ScheduleDriver(passenger_name, minute, destination, pick_loc, passenger_uid, da, mon, hour));
                                Collections.sort(userArray, new ScheduleDriverCmp());
                            }else {

                                if(da > day)
                                {
                                    userArray.add(new ScheduleDriver(passenger_name, minute, destination, pick_loc, passenger_uid, da, mon, hour));
                                    Collections.sort(userArray, new ScheduleDriverCmp());
                                }else if(da == day && hour < hou){

                                }else{

                                    if(hour > hou)
                                    {
                                        userArray.add(new ScheduleDriver(passenger_name, minute, destination, pick_loc, passenger_uid, da, mon, hour));
                                        Collections.sort(userArray, new ScheduleDriverCmp());
                                    }else if(hour == hou && minute < minu)
                                    {

                                    }else{
                                        if(minute > minu)
                                        {
                                            userArray.add(new ScheduleDriver(passenger_name, minute, destination, pick_loc, passenger_uid, da, mon, hour));
                                            Collections.sort(userArray, new ScheduleDriverCmp());
                                        }
                                    }
                                }


                            }
                        }



                    }
                }else{

                }
            }
        }

        @Override
        public void onCancelled(FirebaseError firebaseError) {

        }
    });

    try {

        TimeUnit.SECONDS.sleep(1);
    } catch (InterruptedException e) {
        //Handle exception
    }


    /**
     * set item into adapter
     */
    userAdapter = new Find_schedule_driver_adaptor(Find_schedule_driver.this, R.layout.find_schedule_driver_adaptor, userArray);
    userList = (ListView) findViewById(R.id.Driverlistview1);
    userList.setItemsCanFocus(false);
    userList.setAdapter(userAdapter);

}
    class ScheduleDriverCmp implements Comparator<ScheduleDriver> {

        // comparetor when first schedule is greater than second return 1
        // when first schedule is lesser than second return -1
        // when first schedule is same as second return 0
        @Override
        public int compare(ScheduleDriver lhs, ScheduleDriver rhs) {
            if(lhs.getMonth() != rhs.getMonth())
            {
                return lhs.getMonth() - rhs.getMonth();
            }else{
                if (lhs.getDay() != rhs.getDay())
                {
                    return lhs.getDay() - rhs.getDay();
                }else{

                    if(lhs.getHour() != rhs.getHour())
                    {
                        return lhs.getHour() - rhs.getHour();
                    }else{

                        return lhs.getHour() - rhs.getHour();
                    }
                }

            }
        }
    }

}





