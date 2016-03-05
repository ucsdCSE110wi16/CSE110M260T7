package com.example.jem.ucsdcarpool;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Jem on 3/3/16.
 */
public class Find_schedule_driver extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_schedule_driver);

        Button mybutton1 = (Button) findViewById(R.id.schedule1);
        Button mybutton2 = (Button) findViewById(R.id.schedule2);
        Button mybutton3 = (Button) findViewById(R.id.schedule3);
        Button mybutton4 = (Button) findViewById(R.id.schedule4);
        Button mybutton5 = (Button) findViewById(R.id.schedule5);
        Button mybutton6 = (Button) findViewById(R.id.schedule6);


        mybutton1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Find_schedule_driver.this, Schedule_Find_Schedule.class);
                startActivity(intent);

            }
        });

        mybutton2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Find_schedule_driver.this, Schedule_Find_Schedule.class);
                startActivity(intent);
            }
        });

        mybutton3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Find_schedule_driver.this, Schedule_Find_Schedule.class);
                startActivity(intent);
            }
        });

        mybutton4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Find_schedule_driver.this, Schedule_Find_Schedule.class);
                startActivity(intent);
            }
        });

        mybutton5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Find_schedule_driver.this, Schedule_Find_Schedule.class);
                startActivity(intent);

            }
        });

        mybutton6.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Find_schedule_driver.this, Schedule_Find_Schedule.class);
                startActivity(intent);

            }
        });
    }
}
