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
import java.util.Objects;

/**
 * Created by yucheng on 2/28/16.
 */
public class Profile extends AppCompatActivity {
    private Firebase mRef;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

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

        mybutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, Driver.class);
                startActivity(intent);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        });

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
    }


}