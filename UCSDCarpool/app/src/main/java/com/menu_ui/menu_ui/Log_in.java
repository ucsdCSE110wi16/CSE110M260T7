package com.menu_ui.menu_ui;

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

public class Log_in extends AppCompatActivity {

    private Firebase mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        Firebase.setAndroidContext(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final EditText user_name = (EditText) findViewById(R.id.user_name);
        final EditText pwd = (EditText) findViewById(R.id.pwd);
        final Button logIn = (Button) findViewById(R.id.login);
        final Button register = (Button) findViewById(R.id.quit);

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mRef = new Firebase("https://ucsdcarpool.firebaseio.com");
                final String email = user_name.getText().toString();
                final String password = pwd.getText().toString();
                mRef.authWithPassword(email, password, new Firebase.AuthResultHandler() {
                    @Override
                    public void onAuthenticated(AuthData authData) {
                        System.out.println("User ID: " + authData.getUid() + ", Provider: " + authData.getProvider());

                        Intent k = new Intent(Log_in.this, Menu_activity.class);
                        startActivity(k);

                    }

                    @Override
                    public void onAuthenticationError(FirebaseError firebaseError) {

                    }
                });
            }
            //click login button jump to app main page
            public void loginToApp(View view){
                Intent intent = new Intent (Log_in.this, passengerUI.class);
                startActivity(intent);

            }
        });



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent k = new Intent(Log_in.this, Register.class);
                startActivity(k);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void logIn()
    {
        //System.out.print("" + this.email + " and " + this.pwd);

    }

}
