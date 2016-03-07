package com.example.jem.ucsdcarpool;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

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
 * Created by Jem on 3/2/16.
 */
public class Passenger_ui extends Activity {
    private Firebase mRef = new Firebase("https://ucsdcarpool.firebaseio.com/");
    private ListView userList;
    private Passenger_ui_adaptor userAdapter;
    private ArrayList<Schedule> userArray = new ArrayList<Schedule>();

    private Calendar calendar;
    private int month;
    private int day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.passenger_ui);

        Button back_passenger_ui = (Button) findViewById(R.id.back_passenger_ui);

        back_passenger_ui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(Passenger_ui.this, Menu.class);
                startActivity(k);
            }
        });

        calendar = Calendar.getInstance();

        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DAY_OF_MONTH);

        final TextView homeDate = (TextView) findViewById(R.id.HomePageDatenull);
        final TextView homeTime = (TextView) findViewById(R.id.FIREBASE_Time_null);
        final TextView homeDest = (TextView) findViewById(R.id.FIREBASE_destination_null);
        final TextView homePick = (TextView) findViewById(R.id.FIREBASE_pickup_null);

        //make view scroll
        ListView lv = (ListView) findViewById(R.id.HomeListView);
        ListView list = (ListView) findViewById(R.id.HomeListView);
        setListViewHeightBasedOnChildren(list);
        lv.setOnTouchListener(new View.OnTouchListener() {
            // Setting on Touch Listener for handling the touch inside ScrollView
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Disallow the touch request for parent scroll on touch of child view
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        /**
         * @TODO :ADD INFORMATION FROM DATABASE TO THE BELOW ARRAYLIST
         */

        final String uid = mRef.getAuth().getUid();

        Firebase firRef = mRef.child("schedules").child("schedule_id");
        firRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println("in ondatachange");
                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    if (snap.child("passenger_uid").getValue(String.class).equals(uid)  && snap.child("schedule_taken").getValue(boolean.class) == true && snap.child("schedule_deleted").getValue(boolean.class) == false) {
                        int mon = snap.child("schedule_month").getValue(int.class);
                        int da = snap.child("schedule_day").getValue(int.class);
                        int hou = snap.child("schedule_hour").getValue(int.class);
                        int min = snap.child("schedule_minutes").getValue(int.class);
                        int minute = calendar.get(Calendar.MINUTE);
                        int hour = calendar.get(Calendar.HOUR);

                        if(mon < month)
                        {

                        }else if(mon > month)
                        {
                            userArray.add(new Schedule(snap.child("passenger_name").getValue(String.class),
                                    snap.child("driver_name").getValue(String.class),
                                    snap.child("passenger_uid").getValue(String.class),
                                    snap.child("driver_uid").getValue(String.class),
                                    snap.child("pickup_location").getValue(String.class),
                                    snap.child("destination").getValue(String.class),
                                    da, mon, hou, min));
                            Collections.sort(userArray, new ScheduleCmp());
                        }else{

                            if(da < day)
                            {

                            }else if(da > day)
                            {
                                userArray.add(new Schedule(snap.child("passenger_name").getValue(String.class),
                                        snap.child("driver_name").getValue(String.class),
                                        snap.child("passenger_uid").getValue(String.class),
                                        snap.child("driver_uid").getValue(String.class),
                                        snap.child("pickup_location").getValue(String.class),
                                        snap.child("destination").getValue(String.class),
                                        da, mon, hou, min));
                                Collections.sort(userArray, new ScheduleCmp());
                            }else{

                                if(hou < hour)
                                {

                                }else if(hou > hour)
                                {
                                    userArray.add(new Schedule(snap.child("passenger_name").getValue(String.class),
                                            snap.child("driver_name").getValue(String.class),
                                            snap.child("passenger_uid").getValue(String.class),
                                            snap.child("driver_uid").getValue(String.class),
                                            snap.child("pickup_location").getValue(String.class),
                                            snap.child("destination").getValue(String.class),
                                            da, mon, hou, min));
                                    Collections.sort(userArray, new ScheduleCmp());
                                }else{

                                    if(min > minute)
                                    {
                                        userArray.add(new Schedule(snap.child("passenger_name").getValue(String.class),
                                                snap.child("driver_name").getValue(String.class),
                                                snap.child("passenger_uid").getValue(String.class),
                                                snap.child("driver_uid").getValue(String.class),
                                                snap.child("pickup_location").getValue(String.class),
                                                snap.child("destination").getValue(String.class),
                                                da, mon, hou, min));
                                        Collections.sort(userArray, new ScheduleCmp());
                                    }
                                }
                            }

                        }

                    }

                    if (snap.child("schedule_taken").getValue(boolean.class) == true && snap.child("schedule_deleted").getValue(boolean.class) == false && snap.child("driver_uid").getValue(String.class).equals(uid)) {
                        int mon = snap.child("schedule_month").getValue(int.class);
                        int da = snap.child("schedule_day").getValue(int.class);
                        int hou = snap.child("schedule_hour").getValue(int.class);
                        int min = snap.child("schedule_minutes").getValue(int.class);
                        int minute = calendar.get(Calendar.MINUTE);
                        int hour = calendar.get(Calendar.HOUR);

                        if(mon < month)
                        {

                        }else if(mon > month)
                        {
                            userArray.add(new Schedule(snap.child("passenger_name").getValue(String.class),
                                    snap.child("driver_name").getValue(String.class),
                                    snap.child("passenger_uid").getValue(String.class),
                                    snap.child("driver_uid").getValue(String.class),
                                    snap.child("pickup_location").getValue(String.class),
                                    snap.child("destination").getValue(String.class),
                                    da, mon, hou, min));
                            Collections.sort(userArray, new ScheduleCmp());
                        }else{

                            if(da < day)
                            {

                            }else if(da > day)
                            {
                                userArray.add(new Schedule(snap.child("passenger_name").getValue(String.class),
                                        snap.child("driver_name").getValue(String.class),
                                        snap.child("passenger_uid").getValue(String.class),
                                        snap.child("driver_uid").getValue(String.class),
                                        snap.child("pickup_location").getValue(String.class),
                                        snap.child("destination").getValue(String.class),
                                        da, mon, hou, min));
                                Collections.sort(userArray, new ScheduleCmp());
                            }else{

                                if(hou < hour)
                                {

                                }else if(hou > hour)
                                {
                                    userArray.add(new Schedule(snap.child("passenger_name").getValue(String.class),
                                            snap.child("driver_name").getValue(String.class),
                                            snap.child("passenger_uid").getValue(String.class),
                                            snap.child("driver_uid").getValue(String.class),
                                            snap.child("pickup_location").getValue(String.class),
                                            snap.child("destination").getValue(String.class),
                                            da, mon, hou, min));
                                    Collections.sort(userArray, new ScheduleCmp());
                                }else{

                                    if(min > minute)
                                    {
                                        userArray.add(new Schedule(snap.child("passenger_name").getValue(String.class),
                                                snap.child("driver_name").getValue(String.class),
                                                snap.child("passenger_uid").getValue(String.class),
                                                snap.child("driver_uid").getValue(String.class),
                                                snap.child("pickup_location").getValue(String.class),
                                                snap.child("destination").getValue(String.class),
                                                da, mon, hou, min));
                                        Collections.sort(userArray, new ScheduleCmp());
                                    }
                                }
                            }

                        }
                    }

                }

                if (userArray.size() == 0) {
                    System.err.println("user array not set up, length is 0");
                } else {
                    System.out.println("month: " + userArray.get(0).getMonth());
                    homeDate.setText(userArray.get(0).getMonth() + " / " + userArray.get(0).getDay());
                    homeTime.setText(userArray.get(0).getHour() + " : " + userArray.get(0).getMinute());
                    homeDest.setText(userArray.get(0).getDestination());
                    homePick.setText(userArray.get(0).getPick_loc());
                }

                /*
                setContentView(R.layout.passenger_ui);

                userAdapter = new Passenger_ui_adaptor(Passenger_ui.this, R.layout.passenger_ui_listview_row, userArray);
                userList = (ListView) findViewById(R.id.HomeListView);
                userList.setItemsCanFocus(false);
                userList.setAdapter(userAdapter);
                */
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }

        });


        /**
         * set item into adapter
         */

        System.out.println("TESTHERE" + userArray.size());
        //=============
        try {

            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            //Handle exception
        }
        userAdapter = new Passenger_ui_adaptor(Passenger_ui.this, R.layout.passenger_ui_listview_row, userArray);
        userList = (ListView) findViewById(R.id.HomeListView);
        userList.setItemsCanFocus(false);
        userList.setAdapter(userAdapter);

        /**
         * get on item click listener
         */
        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    final int position, long id) {
                Log.i("List View Clicked", "**********");
                /*Toast.makeText(Passenger_ui.this,
                        "List View Clicked:" + position, Toast.LENGTH_LONG)
                        .show();*/

                Intent k = new Intent(Passenger_ui.this, Passenger_ui_display.class);
                startActivity(k);
            }
        });

    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();




        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, ViewGroup.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    class ScheduleCmp implements Comparator<Schedule>
    {

        @Override
        public int compare(Schedule lhs, Schedule rhs) {
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



