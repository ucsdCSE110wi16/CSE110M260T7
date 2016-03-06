package com.example.jem.ucsdcarpool;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Jem on 3/2/16.
 */
public class Menu extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        Button mybutton1 = (Button) findViewById(R.id.home_profile);
        Button mybutton3 = (Button) findViewById(R.id.home_Find_Schedule);
        Button mybutton4 = (Button) findViewById(R.id.home_main);
        Button mybutton5 = (Button) findViewById(R.id.home_Find_Schedule_Driver);

        mybutton1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Menu.this, Profile.class);
                startActivity(intent);

            }
        });

        mybutton3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, Find_schedule_customer.class);
                startActivity(intent);
            }
        });

        mybutton4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, Passenger_ui.class);
                startActivity(intent);
            }
        });

        mybutton5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, Find_schedule_driver.class);
                startActivity(intent);
            }
        });
    }
}
