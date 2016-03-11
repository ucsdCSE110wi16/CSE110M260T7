package com.example.jem.ucsdcarpool;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jem on 3/2/16.
 */
public class Driver extends AppCompatActivity {

    // create a Firebase reference
    private Firebase mRef;

    // create view
    @Override
    public void onCreate(Bundle savedInstanceState) {
        // create the view
        super.onCreate(savedInstanceState);
        setContentView(R.layout.driver_profile);

        // get all the edit text to get driver profile
        final EditText DriverLicense = (EditText) findViewById(R.id.user_driverlicense_update_driver);
        final EditText ExpireDate = (EditText) findViewById(R.id.user_expiredate_update_driver);
        final EditText make = (EditText) findViewById(R.id.user_make_update_driver);
        final EditText year = (EditText) findViewById(R.id.user_year_update_driver);
        final EditText color = (EditText) findViewById(R.id.user_color_update_driver);

        // get all the buttons
        Button mybutton = (Button) findViewById(R.id.backtobasicprofile);
        Button save = (Button) findViewById(R.id.driver_save);
        Button reset = (Button) findViewById(R.id.driver_reset);
        Button back_driver = (Button) findViewById(R.id.back_driver);

        // set on click listener
        mybutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Driver.this, Profile.class);
                startActivity(intent);
            }
        });

        // set save button click listener
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // check the user input not empty
                if(DriverLicense.getText().toString().equals("") ||
                        ExpireDate.getText().toString().equals("") ||
                        color.getText().toString().equals(""))
                {
                    Context context = getApplicationContext();
                    CharSequence text = "Please fill in all required information!";
                    int duration = Toast.LENGTH_SHORT;

                    // set the toast to show notification
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.setGravity(Gravity.CENTER_HORIZONTAL| Gravity.CENTER_VERTICAL, 10, 10);
                    toast.show();
                }else {

                    // start the firebase reference
                    mRef = new Firebase("https://ucsdcarpool.firebaseio.com");
                    // get all the user input
                    final String dl = DriverLicense.getText().toString();
                    final String ed = ExpireDate.getText().toString();
                    final String ma = make.getText().toString();
                    final String ye = year.getText().toString();
                    final String co = color.getText().toString();

                    // get the child
                    Firebase uRef = mRef.child("user_info");
                    String uid = mRef.getAuth().getUid().toString();
                    uRef = uRef.child(uid);

                    // set value
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("driver_license", dl);
                    map.put("driver_expire_date", ed);
                    map.put("driver_car_make", ma);
                    map.put("driver_car_year", ye);
                    map.put("driver_car_color", co);

                    uRef.updateChildren(map);
                }
            }
        });

        // reset button listener
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

        // back button listener
        back_driver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Driver.this, Menu.class);
                startActivity(intent);
            }
        });
    }
}
