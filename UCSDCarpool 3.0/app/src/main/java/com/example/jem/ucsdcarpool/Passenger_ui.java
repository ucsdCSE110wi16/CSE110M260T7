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
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Jem on 3/2/16.
 */
public class Passenger_ui extends Activity {
    private Firebase mRef = new Firebase("https://ucsdcarpool.firebaseio.com/");
    private ListView userList;
    private Passenger_ui_adaptor userAdapter;
    private ArrayList<Schedule> userArray = new ArrayList<Schedule>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.passenger_ui);

        Firebase firRef = mRef.child("schedules").child("schedule_id");
        firRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String passenger_name = dataSnapshot.child("passenger_name").getValue(String.class);
                String driver_name = dataSnapshot.child("driver_name").getValue(String.class);
                String pick_loc = dataSnapshot.child("pick_loc").getValue(String.class);
                String destination = dataSnapshot.child("pick_destination").getValue(String.class);
                int day = dataSnapshot.child("Days_day").getValue(int.class);
                int month = dataSnapshot.child("Days_month").getValue(int.class);
                int hour = dataSnapshot.child("time_hour").getValue(int.class);
                int minute = dataSnapshot.child("time_minutes").getValue(int.class);

                TextView date = (TextView) findViewById(R.id.FIREBASE_Date);
                date.setText("" + month + " - " + day);

                TextView time = (TextView) findViewById(R.id.FIREBASE_Time);
                time.setText("" + hour + " : " + minute);

                TextView dest = (TextView) findViewById(R.id.FIREBASE_destination);
                dest.setText(destination);

                TextView pick = (TextView) findViewById(R.id.FIREBASE_pickup);
                pick.setText(pick_loc);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

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
        Firebase schRef = mRef.child("schedules").child("schedule_id");
        schRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String passenger_name = dataSnapshot.child("passenger_name").getValue(String.class);
                String driver_name = dataSnapshot.child("driver_name").getValue(String.class);
                String pick_loc = dataSnapshot.child("pick_loc").getValue(String.class);
                String destination = dataSnapshot.child("pick_destination").getValue(String.class);
                int day = dataSnapshot.child("Days_day").getValue(int.class);
                int month = dataSnapshot.child("Days_month").getValue(int.class);
                int hour = dataSnapshot.child("time_hour").getValue(int.class);
                int minute = dataSnapshot.child("time_minutes").getValue(int.class);

                userArray.add(new Schedule(passenger_name, driver_name, pick_loc, destination, day, month, hour, minute));
                userArray.add(new Schedule(passenger_name, driver_name, pick_loc, destination, day, month, hour, minute));
                userArray.add(new Schedule(passenger_name, driver_name, pick_loc, destination, day, month, hour, minute));
                userArray.add(new Schedule(passenger_name, driver_name, pick_loc, destination, day, month, hour, minute));
                userArray.add(new Schedule(passenger_name, driver_name, pick_loc, destination, day, month, hour, minute));
                userArray.add(new Schedule(passenger_name, driver_name, pick_loc, destination, day, month, hour, minute));
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        /*
        userArray.add(new Schedule("Schedule name", "Schedule address", "Schedule Detail"));
        userArray.add(new Schedule("Morning Schedule", "UCSD", "drive info+data+time"));
        userArray.add(new Schedule("Afternoon Schedule", "UCSD", "drive info+data+time"));
        userArray.add(new Schedule("Weekend Schedule", "UCSD", "drive info+data+time"));
        userArray.add(new Schedule("Monday Schedule", "UCSD", "drive info+data+time"));
        userArray.add(new Schedule("Holiday Schedule", "UCSD", "drive info+data+time"));
        userArray.add(new Schedule("Today Schedule", "UCSD", "drive info+data+time"));
        */
        /**
         * set item into adapter
         */
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

}



