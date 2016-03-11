package com.example.jem.ucsdcarpool;

/**
 * Created by Yukana on 16/2/25.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

public class Log_in extends AppCompatActivity {

    // create firebase reference
    private Firebase mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // create the view
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        Firebase.setAndroidContext(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // get all the text view and button
        final EditText user_name = (EditText) findViewById(R.id.user_name);
        final EditText pwd = (EditText) findViewById(R.id.pwd);
        final Button logIn = (Button) findViewById(R.id.login);
        final Button register = (Button) findViewById(R.id.quit);

        // setup longin button listener
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // check whether user input is valid
                if(user_name.getText().toString().equals("") ||
                        pwd.getText().toString().equals("") )
                {
                    Context context = getApplicationContext();
                    CharSequence text = "Please fill in all required information!";
                    int duration = Toast.LENGTH_SHORT;


                    Toast toast = Toast.makeText(context, text, duration);
                    toast.setGravity(Gravity.CENTER_HORIZONTAL| Gravity.CENTER_VERTICAL, 10, 10);
                    toast.show();
                }else {

                    // check whether user passward or email is valid
                    mRef = new Firebase("https://ucsdcarpool.firebaseio.com");
                    final String email = user_name.getText().toString();
                    final String password = pwd.getText().toString();
                    mRef.authWithPassword(email, password, new Firebase.AuthResultHandler() {
                        @Override
                        public void onAuthenticated(AuthData authData) {
                            System.out.println("User ID: " + authData.getUid() + ", Provider: " + authData.getProvider());

                            Intent k = new Intent(Log_in.this, Menu.class);
                            startActivity(k);

                        }

                        @Override
                        public void onAuthenticationError(FirebaseError firebaseError) {

                            // when authentication failed, popup a notification
                            String errMag = firebaseError.getMessage();
                            Context context = getApplicationContext();
                            CharSequence text = errMag;
                            int duration = Toast.LENGTH_SHORT;


                            Toast toast = Toast.makeText(context, text, duration);
                            toast.setGravity(Gravity.CENTER_HORIZONTAL| Gravity.CENTER_VERTICAL, 0, 0);
                            toast.show();
                        }
                    });
                }
            }
            //click login button jump to app main page
            public void loginToApp(View view){
                Intent intent = new Intent (Log_in.this, Menu.class);
                startActivity(intent);

            }
        });


        // setup register button listener
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
