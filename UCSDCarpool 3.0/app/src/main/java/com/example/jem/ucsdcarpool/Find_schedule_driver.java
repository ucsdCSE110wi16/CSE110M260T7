package com.example.jem.ucsdcarpool;

import android.app.Activity;
import android.content.Context;
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

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

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

    /**
     * @TODO :ADD INFORMATION FROM DATABASE TO THE BELOW ARRAYLIST
     */

    Firebase findSche = mRef.child("schedules/schedule_id");
    final Firebase uRef = mRef.child("user_info").child(mRef.getAuth().getUid());

    findSche.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            for(DataSnapshot snap : dataSnapshot.getChildren())
            {
                final DataSnapshot shot = snap;
                if(snap.child("schedule_taken").getValue().toString().equals("false"))
                {
                    uRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String user_name = dataSnapshot.child("user_name").getValue(String.class);
                            String passenger_name = shot.child("passenger_name").getValue(String.class);
                            if (user_name.equals(passenger_name)) {
                                System.out.println("same");
                            } else {
                                System.out.println("not the same");
                                String destination = null;
                                String pick_loc = null;
                                int month = shot.child("schedule_month").getValue(int.class);
                                int day = shot.child("schedule_day").getValue(int.class);
                                int hour = shot.child("schedule_hour").getValue(int.class);
                                int minute = shot.child("schedule_minute").getValue(int.class);
                                userArray.add(new ScheduleDriver(month, passenger_name, pick_loc, destination, day, hour, minute));
                            }
                        }

                        @Override
                        public void onCancelled(FirebaseError firebaseError) {

                        }
                    });


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
    /**
     * get on item click listener
     */
//    userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//        @Override
//        public void onItemClick(AdapterView<?> parent, View v,
//                                final int position, long id) {
//            Log.i("List View Clicked", "**********");
//            Toast.makeText(Find_schedule_driver.this,
//                    "List View Clicked:" + position, Toast.LENGTH_LONG)
//                    .show();
//        }
//    });

}

        }



//public class Find_schedule_driver extends AppCompatActivity {
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.find_schedule_driver);
//
////        Button mybutton1 = (Button) findViewById(R.id.schedule1);
////        Button mybutton2 = (Button) findViewById(R.id.schedule2);
////        Button mybutton3 = (Button) findViewById(R.id.schedule3);
////        Button mybutton4 = (Button) findViewById(R.id.schedule4);
////        Button mybutton5 = (Button) findViewById(R.id.schedule5);
////        Button mybutton6 = (Button) findViewById(R.id.schedule6);
//
//
//        mybutton1.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(Find_schedule_driver.this, Schedule_Find_Schedule.class);
//                startActivity(intent);
//
//            }
//        });
//
//        mybutton2.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Find_schedule_driver.this, Schedule_Find_Schedule.class);
//                startActivity(intent);
//            }
//        });
//
//        mybutton3.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Find_schedule_driver.this, Schedule_Find_Schedule.class);
//                startActivity(intent);
//            }
//        });
//
//        mybutton4.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Find_schedule_driver.this, Schedule_Find_Schedule.class);
//                startActivity(intent);
//            }
//        });
//
//        mybutton5.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Find_schedule_driver.this, Schedule_Find_Schedule.class);
//                startActivity(intent);
//
//            }
//        });
//
//        mybutton6.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Find_schedule_driver.this, Schedule_Find_Schedule.class);
//                startActivity(intent);
//
//            }
//        });
//    }

