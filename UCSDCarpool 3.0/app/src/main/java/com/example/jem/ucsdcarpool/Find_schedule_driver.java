package com.example.jem.ucsdcarpool;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Button;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Jem on 3/3/16.
 */
public class Find_schedule_driver extends Activity {

    private Firebase mRef = new Firebase("https://ucsdcarpool.firebaseio.com/");
        ListView userList;
        Find_schedule_driver_adaptor userAdapter;
        ArrayList<ScheduleDriver> userArray = new ArrayList<ScheduleDriver>();


@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.find_schedule_driver);

    Button back = (Button) findViewById(R.id.back_find_schedule_driver);


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

    Firebase findSche = mRef.child("schedules/schedule_id");
    final String user_uid = mRef.getAuth().getUid();

    findSche.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            for(DataSnapshot snap : dataSnapshot.getChildren())
            {
                String passenger_uid = snap.child("passenger_uid").getValue(String.class);
                if(snap.child("schedule_taken").getValue().toString().equals("false") )
                {
                    if (passenger_uid.equals(user_uid))
                    {
                        System.out.println(user_uid);
                    }else {
                        System.out.println("not same, the uid from user_info: " + user_uid + " the uid from schedule: " + passenger_uid);
                        String passenger_name = snap.child("passenger_name").getValue(String.class);
                        String destination = null;
                        String pick_loc = null;
                        int month = snap.child("schedule_month").getValue(int.class);
                        int day = snap.child("schedule_day").getValue(int.class);
                        int hour = snap.child("schedule_hour").getValue(int.class);
                        int minute = snap.child("schedule_minutes").getValue(int.class);
                        userArray.add(new ScheduleDriver(passenger_name, minute, destination, pick_loc, passenger_uid, day, month, hour));
                        Collections.sort(userArray, new ScheduleDriverCmp());
                    }
                }else{

                }
            }
        }

        @Override
        public void onCancelled(FirebaseError firebaseError) {

        }
    });


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





