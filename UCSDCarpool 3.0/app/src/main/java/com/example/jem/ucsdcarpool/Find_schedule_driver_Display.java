package com.example.jem.ucsdcarpool;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by xiejingwen on 3/5/16.
 */
public class Find_schedule_driver_Display extends Activity{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_schedule_driver_display);

        final Button Confirm = (Button) findViewById(R.id.btndriverConfirm);
        final Button Back = (Button) findViewById(R.id.btndriverBack);

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(Find_schedule_driver_Display.this, Find_schedule_driver.class);
                startActivity(k);
            }
        });
        //TODO:set onclilistener for confirm button and link to firebase
    }
}



