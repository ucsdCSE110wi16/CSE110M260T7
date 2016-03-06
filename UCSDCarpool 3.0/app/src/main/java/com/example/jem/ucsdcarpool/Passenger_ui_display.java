package com.example.jem.ucsdcarpool;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by xiejingwen on 3/5/16.
 */
public class Passenger_ui_display extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.passenger_ui_display);


        final Button Back = (Button) findViewById(R.id.btnhomeBack);

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(Passenger_ui_display.this, Passenger_ui.class);
                startActivity(k);
            }
        });
    }
}