package com.example.jem.ucsdcarpool;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Jem on 3/2/16.
 */
public class Find_schedule_customer extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_schedule_customer);


        //set activty content
        Button buttonSetTime = (Button) findViewById(R.id.btn_setTime);

        Button buttonSetDate = (Button) findViewById(R.id.btn_setDate);
        buttonSetTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogFragment newFragment = new TimePickFragment();
                newFragment.show(getSupportFragmentManager(), "TimePicker");

            }
        });
        buttonSetDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogFragment newFragment = new DatePickFragment();
                newFragment.show(getSupportFragmentManager(), "DatePicker");

            }
        });

    }


}
