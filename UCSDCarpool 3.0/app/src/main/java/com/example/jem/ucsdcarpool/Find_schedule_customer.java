package com.example.jem.ucsdcarpool;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jem on 3/2/16.
 */
public class Find_schedule_customer extends AppCompatActivity {
    private int selectedMinutes;
    private int selectedHour;
    private int selectedDay;
    private int selectedMonth;
    private Firebase mRef;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_schedule_customer);


        // ========== test for save
        Button buttonSave = (Button)findViewById(R.id.button_save);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView vew = (TextView) findViewById(R.id.tv);
                String line = vew.getText().toString();
                //System.out.println("Line is: " + line);
                //
                //List receive = getInts(line);
                getIntsToTime(line);

                TextView vew2 = (TextView) findViewById(R.id.tv_date);
                // if(vew2 == null)
                // {
                //     System.out.println("alallalallalal");
                // }
                String line2 = vew2.getText().toString();
                // System.out.println("String :***" + line2 + "***");
                if (line != null && line2 != null) {
                    getIntsToTime(line);
                    getIntsToDate(line2);

                    mRef = new Firebase("https://ucsdcarpool.firebaseio.com");
                    Firebase uRef = mRef.child("user_info");

                    final String uid = mRef.getAuth().getUid().toString();
                    uRef = uRef.child(uid);


                    //String customer_name = uRef.
                    uRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            final String user_name = dataSnapshot.child("user_name").getValue(String.class);

                            boolean schedule_taken = false;

                            System.out.println("Customer name: " + user_name);

                            final Firebase pushSche = new Firebase("https://ucsdcarpool.firebaseio.com/schedules/schedule_id");


                            //pushSche.child("1").setValue(map);

                            pushSche.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    long numberOfChild = dataSnapshot.getChildrenCount() + 1;
                                    Map<String, Object> map = new HashMap<String, Object>();
                                    map.put("passenger_name", user_name);
                                    map.put("passenger_uid", uid);
                                    map.put("schedule_taken", false);
                                    map.put("schedule_month", selectedMonth);
                                    map.put("schedule_day", selectedDay);
                                    map.put("schedule_hour", selectedHour);
                                    map.put("schedule_minutes", selectedMinutes);
                                    map.put("destination", "$$$");
                                    map.put("pickup_location", "$$$");
                                    map.put("schedule_deleted", false);
                                    //map.put("")
                                    pushSche.child("" + numberOfChild).setValue(map);
                                }

                                @Override
                                public void onCancelled(FirebaseError firebaseError) {

                                }
                            });
                        }

                        @Override
                        public void onCancelled(FirebaseError firebaseError) {

                        }
                    });


                }


            }

            public void getIntsToDate(String str) {
                //String temp = str.substring(str.indexOf("\n"));
                //temp.replace("\n", "");
                //String temp = str;
                //  System.out.println("String :***" + temp + "***");
                // temp.replaceAll(" 2016-", "");
                //  System.out.println("String :***" + temp + "***");
                //  System.out.println("String :***" + temp.substring(temp.indexOf('-') + 1, temp.lastIndexOf('-')) + "***");
                // System.out.println("String :***" + temp.substring(temp.lastIndexOf('-') + 1,
                //        temp.length()) + "***");
                int index = 0;
                for (int i = 0; i < str.length(); i++) {
                    if (str.charAt(i) == '-') {
                        for (int j = i + 1; j < str.length(); j++) {
                            if (str.charAt(j) == '-') {
                                //System.out.println("——" + str.substring(i + 1, j));
                                selectedMonth = Integer.parseInt(str.substring(i + 1, j));
                                index = j + 1;
                            }
                        }
                    }
                    if (index != 0) {
                        break;
                    }
                }
                // System.out.println("test");
                // selectedMonth = Integer.parseInt(temp.substring(temp.indexOf('-') + 1), temp.lastIndexOf('-'));

                selectedDay = Integer.parseInt(str.substring(index, str.length()));
                //  temp.length()));
                //System.out.println("Time—— " + selectedMonth + " : " + selectedDay);
            }

            public void getIntsToTime(String str) {
                boolean foo = str.contains("PM");
                String temp = str.substring(str.indexOf("\n"));

                selectedHour = Integer.parseInt(temp.substring(0, temp.indexOf(':')).
                        replaceAll("[^0-9]+", ""));
                selectedMinutes = Integer.parseInt(temp.substring(temp.indexOf(':'),
                        temp.lastIndexOf(' ')).replaceAll("[^0-9]+", ""));
                if (foo) {
                    selectedHour += 12;
                }


            }
            //String temp = str.replaceAll("[^0-9]+", " ");

            //return Arrays.asList(str.trim().split(" "));

            //System.out.println("Line is: " + line);
        });
        //======
        //set activty content
        Button buttonSetTime = (Button) findViewById(R.id.btn_setTime);

        Button buttonSetDate = (Button) findViewById(R.id.btn_setDate);
        buttonSetTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogFragment newFragment = new TimePickFragment();
                newFragment.show(getSupportFragmentManager(), "TimePicker");

            }
        });
        buttonSetDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogFragment newFragment = new DatePickFragment();
                newFragment.show(getSupportFragmentManager(), "DatePicker");

            }
        });





    }
}
