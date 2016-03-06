package com.example.jem.ucsdcarpool;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
 * Created by xiejingwen on 3/5/16.
 */
public class Find_schedule_driver_Display extends Activity{
    private Firebase mRef;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_schedule_driver_display);

        TextView date = (TextView) findViewById(R.id.CarpoolInfo_Date);
        TextView time = (TextView) findViewById(R.id.CarpoolInfo_Time);
        TextView dest = (TextView)findViewById(R.id.CarpoolInfo_Destination);
        TextView pick_loc = (TextView) findViewById(R.id.CarpoolInfo_Pick_Loc);

        date.setText(Find_schedule_driver_adaptor.driver.getMonth() + "/" + Find_schedule_driver_adaptor.driver.getDay());
        time.setText(Find_schedule_driver_adaptor.driver.getHour() + ":" + Find_schedule_driver_adaptor.driver.getMinute());
        dest.setText(Find_schedule_driver_adaptor.driver.getDestination());
        pick_loc.setText(Find_schedule_driver_adaptor.driver.getPick_loc());


        final Button Confirm = (Button) findViewById(R.id.btndriverConfirm);
        final Button Back = (Button) findViewById(R.id.btndriverBack);

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(Find_schedule_driver_Display.this, Find_schedule_driver.class);
                startActivity(k);
            }
        });
        //TODO:set onclilistener for confirm button and link to firebase
        Confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRef = new Firebase("https://ucsdcarpool.firebaseio.com");
                final Firebase uRef = mRef.child("schedules/schedule_id");

                uRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for(DataSnapshot snap: dataSnapshot.getChildren())
                        {
                            if(snap.child("passenger_uid").getValue(String.class).equals(Find_schedule_driver_adaptor.driver.getPassenger_uid()))
                            {
                                if(snap.child("schedule_month").getValue(int.class).equals(Find_schedule_driver_adaptor.driver.getMonth()))
                                {
                                    if(snap.child("schedule_day").getValue(int.class).equals(Find_schedule_driver_adaptor.driver.getDay()))
                                    {
                                        if(snap.child("schedule_hour").getValue(int.class).equals(Find_schedule_driver_adaptor.driver.getHour()))
                                        {
                                            if(snap.child("schedule_minutes").getValue(int.class).equals(Find_schedule_driver_adaptor.driver.getMinute()))
                                            {
                                                final String index = snap.getKey();
                                                Firebase dRef = mRef.child("user_info").child(mRef.getAuth().getUid());

                                                dRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                        String driver_name = dataSnapshot.child("user_name").getValue(String.class);

                                                        Map<String, Object> map = new HashMap<String, Object>();
                                                        map.put("driver_name", driver_name);
                                                        map.put("driver_uid", mRef.getAuth().getUid());
                                                        map.put("schedule_taken", true);
                                                        uRef.child(index).updateChildren(map);
                                                    }

                                                    @Override
                                                    public void onCancelled(FirebaseError firebaseError) {

                                                    }
                                                });

                                                break;

                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });

            }
        });
    }
}



