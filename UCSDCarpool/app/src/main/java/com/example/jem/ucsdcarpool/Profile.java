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
 * Created by yucheng on 2/28/16.
 */
public class Profile extends AppCompatActivity {

    // create firebase reference
    private Firebase mRef;

    // create view
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        Firebase.setAndroidContext(this);

        // get all the edittext and buttons
        final EditText name = (EditText) findViewById(R.id.user_name_update_custom);
        final EditText id = (EditText) findViewById(R.id.user_ID_update_custom);
        final EditText email = (EditText) findViewById(R.id.user_email_update_custom);
        final EditText gender = (EditText) findViewById(R.id.user_gender_update_custom);
        final EditText ssn = (EditText) findViewById(R.id.user_ssn_update_custom);
        final EditText address = (EditText) findViewById(R.id.user_address_update_custom);
        final EditText phone = (EditText) findViewById(R.id.user_phone_update_custom);
        final EditText zipcode = (EditText) findViewById(R.id.user_zipcode_update_custom);

        Button mybutton = (Button) findViewById(R.id.todriver);
        Button save = (Button) findViewById(R.id.Basic_save);
        Button reset = (Button) findViewById(R.id.Basic_reset);
        Button back_basic = (Button) findViewById(R.id.back_basic);

        // setup my button listener
        mybutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, Driver.class);
                startActivity(intent);
            }
        });

        // setup save button listener
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // cehck whether all user input are valid
                if(name.getText().toString().equals("") ||
                        id.getText().toString().equals("") ||
                        email.getText().toString().equals("")||
                        gender.getText().toString().equals(""))
                {
                    Context context = getApplicationContext();
                    CharSequence text = "Please fill in all required information!";
                    int duration = Toast.LENGTH_SHORT;

                    // when not valid popup notification
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.setGravity(Gravity.CENTER_HORIZONTAL| Gravity.CENTER_VERTICAL, 10, 10);
                    toast.show();
                }else {

                    // put all user valid setting to firebase
                    mRef = new Firebase("https://ucsdcarpool.firebaseio.com");
                    final String na = name.getText().toString();
                    final String i = id.getText().toString();
                    final String em = email.getText().toString();
                    final String ge = gender.getText().toString();
                    final String sn = ssn.getText().toString();
                    final String ad = address.getText().toString();
                    final String ph = phone.getText().toString();
                    final String zc = zipcode.getText().toString();

                    Firebase uRef = mRef.child("user_info");
                    String uid = mRef.getAuth().getUid().toString();
                    uRef = uRef.child(uid);
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("user_email", em);
                    map.put("user_name", na);
                    map.put("user_id", i);
                    map.put("user_gender", ge);
                    map.put("user_ssn", sn);
                    map.put("user_address", ad);
                    map.put("user_phone", ph);
                    map.put("user_zipcode", zc);

                    uRef.updateChildren(map);
                }
            }
        });

        // setup reset button listener
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.setText(null);
                id.setText(null);
                email.setText(null);
                gender.setText(null);
                ssn.setText(null);
                address.setText(null);
                phone.setText(null);
                zipcode.setText(null);

            }
        });

        // setup back button listener
        back_basic.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(Profile.this, Menu.class);
                 startActivity(intent);
             }
    });
    }


}
