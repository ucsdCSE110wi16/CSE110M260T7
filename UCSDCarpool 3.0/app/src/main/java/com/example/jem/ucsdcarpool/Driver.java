package com.example.jem.ucsdcarpool;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.Firebase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jem on 3/2/16.
 */
public class Driver extends AppCompatActivity {

    private Firebase mRef;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.driver_profile);

        final EditText DriverLicense = (EditText) findViewById(R.id.user_driverlicense_update_driver);
        final EditText ExpireDate = (EditText) findViewById(R.id.user_expiredate_update_driver);
        final EditText make = (EditText) findViewById(R.id.user_make_update_driver);
        final EditText year = (EditText) findViewById(R.id.user_year_update_driver);
        final EditText color = (EditText) findViewById(R.id.user_color_update_driver);

        Button mybutton = (Button) findViewById(R.id.backtobasicprofile);
        Button save = (Button) findViewById(R.id.driver_save);
        Button reset = (Button) findViewById(R.id.driver_reset);

        mybutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Driver.this, Profile.class);
                startActivity(intent);
            }
        });
        
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRef = new Firebase("https://ucsdcarpool.firebaseio.com");
                final String dl = DriverLicense.getText().toString();
                final String ed = ExpireDate.getText().toString();
                final String ma = make.getText().toString();
                final String ye = year.getText().toString();
                final String co = color.getText().toString();

                Firebase uRef = mRef.child("user_info");
                String uid = mRef.getAuth().getUid().toString();
                uRef = uRef.child(uid);

                Map<String, Object> map = new HashMap<String, Object>();
                map.put("driver_license", dl);
                map.put("driver_expire_date", ed);
                map.put("driver_car_make", ma);
                map.put("driver_car_year", ye);
                map.put("driver_car_color", co);

                uRef.updateChildren(map);
            }
        });
        
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DriverLicense.setText(null);
                ExpireDate.setText(null);
                make.setText(null);
                year.setText(null);
                color.setText(null);
            }
        });
    }
}
