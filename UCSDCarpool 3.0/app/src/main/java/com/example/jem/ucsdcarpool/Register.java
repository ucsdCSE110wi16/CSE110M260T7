package com.example.jem.ucsdcarpool;

/**
 * Created by Yukana on 16/2/25.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jem on 2/21/16.
 */

public class Register extends AppCompatActivity {

    private Firebase mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        final EditText user_name = (EditText) findViewById(R.id.user_name);

        final EditText user_pwd = (EditText) findViewById(R.id.user_password);

        final EditText user_email = (EditText) findViewById(R.id.user_email);

        Button register = (Button) findViewById(R.id.register);

        Button reset = (Button) findViewById(R.id.reset);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mRef = new Firebase("https://ucsdcarpool.firebaseio.com");

                final String email = user_email.getText().toString();
                final String password = user_pwd.getText().toString();
                final String name = user_name.getText().toString();

                mRef.createUser(email, password, new Firebase.ResultHandler() {
                    @Override
                    public void onSuccess() {

                        mRef.authWithPassword(email, password, new Firebase.AuthResultHandler(){
                            @Override
                            public void onAuthenticated(AuthData authData) {
                                // Authentication just completed successfully :)
                                Map<String, String> map = new HashMap<String, String>();
                                map.put("user_email", email);
                                map.put("user_name", name);
                                mRef.child("user_info").child(authData.getUid()).setValue(map);
                            }
                            @Override
                            public void onAuthenticationError(FirebaseError error) {
                                // Something went wrong :(
                            }
                        });


                        Intent k = new Intent(Register.this, Menu.class);
                        startActivity(k);
                    }

                    @Override
                    public void onError(FirebaseError firebaseError) {

                    }
                });
            }
        });


        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                user_email.setText(null);

                user_name.setText(null);

                user_pwd.setText(null);
            }
        });
    }
}
