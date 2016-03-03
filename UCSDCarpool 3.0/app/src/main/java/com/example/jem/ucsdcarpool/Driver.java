package com.example.jem.ucsdcarpool;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Jem on 3/2/16.
 */
public class Driver extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.driver_profile);

        Button mybutton = (Button) findViewById(R.id.backtobasicprofile);

        mybutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Driver.this, Profile.class);
                startActivity(intent);
            }
        });
    }
}
