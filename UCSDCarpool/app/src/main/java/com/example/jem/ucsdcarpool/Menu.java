package com.example.jem.ucsdcarpool;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

/**
 * Created by Jem on 3/2/16.
 */
public class Menu extends AppCompatActivity {

    // create firebase reference
    private Firebase mRef;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // creaete view
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        Firebase.setAndroidContext(this);

        // initialize firebase reference
        mRef = new Firebase("https://ucsdcarpool.firebaseio.com/");

        // get all the buttons
        Button mybutton1 = (Button) findViewById(R.id.home_profile);
        Button mybutton3 = (Button) findViewById(R.id.home_Find_Schedule);
        Button mybutton4 = (Button) findViewById(R.id.home_main);
        Button mybutton5 = (Button) findViewById(R.id.home_Find_Schedule_Driver);
        Button mybutton6 = (Button) findViewById(R.id.home_Log_Out);

        // setup button listener
        mybutton1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Menu.this, Profile.class);
                startActivity(intent);

            }
        });

        // setup button listener
        mybutton3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, Find_schedule_customer.class);
                startActivity(intent);
            }
        });

        // setup button listener
        mybutton4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, Passenger_ui.class);
                startActivity(intent);
            }
        });

        // setup button listener
        mybutton5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // get uid
                String uid = mRef.getAuth().getUid();
                Firebase uRef = mRef.child("user_info").child(uid);

                // check whether the clicker is an authenticated driver
                uRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String license = dataSnapshot.child("driver_license").getValue(String.class);
                        String expire = dataSnapshot.child("driver_expire_date").getValue(String.class);
                        String col = dataSnapshot.child("driver_car_color").getValue(String.class);
                        if (license == "" || expire == "" || col == "" || license == null || expire == null || col == null) {
                            Context context = getApplicationContext();
                            CharSequence text = "Need to fill in driver info, to be authorized as driver!";
                            int duration = Toast.LENGTH_SHORT;

                            // when not a driver popup notification
                            Toast toast = Toast.makeText(context, text, duration);
                            toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, 10, 10);
                            toast.show();
                        } else {
                            Intent intent = new Intent(Menu.this, Find_schedule_driver.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });


            }
        });

        // setup button listener
        mybutton6.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mRef.unauth();
                Intent intent = new Intent(Menu.this, Log_in.class);
                startActivity(intent);
            }
        });
    }
}
