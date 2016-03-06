package com.example.jem.ucsdcarpool;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

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

                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    if (snap.child("passenger_uid").getValue(String.class).equals(uid) && snap.child("schedule_taken").getValue(boolean.class) == true) {
                        userArray.add(new Schedule(snap.child("passenger_name").getValue(String.class),
                                snap.child("driver_name").getValue(String.class),
                                snap.child("passenger_uid").getValue(String.class),
                                snap.child("driver_uid").getValue(String.class),
                                snap.child("pickup_location").getValue(String.class),
                                snap.child("destination").getValue(String.class),
                                snap.child("schedule_day").getValue(int.class),
                                snap.child("schedule_month").getValue(int.class),
                                snap.child("schedule_hour").getValue(int.class),
                                snap.child("schedule_minutes").getValue(int.class)));
                    }

                    if (snap.child("schedule_taken").getValue(boolean.class) == true && snap.child("driver_uid").getValue(String.class).equals(uid)) {
                        userArray.add(new Schedule(snap.child("passenger_name").getValue(String.class),
                                snap.child("driver_name").getValue(String.class),
                                snap.child("passenger_uid").getValue(String.class),
                                snap.child("driver_uid").getValue(String.class),
                                snap.child("pickup_location").getValue(String.class),
                                snap.child("destination").getValue(String.class),
                                snap.child("schedule_day").getValue(int.class),
                                snap.child("schedule_month").getValue(int.class),
                                snap.child("schedule_hour").getValue(int.class),
                                snap.child("schedule_minutes").getValue(int.class)));
                    }

                }

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        Collections.sort(userArray);

        if(userArray.size() == 0)
        {

        }else{
            //TextView
        }

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



