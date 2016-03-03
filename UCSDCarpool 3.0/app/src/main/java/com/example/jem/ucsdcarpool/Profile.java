package com.example.jem.ucsdcarpool;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by yucheng on 2/28/16.
 */
public class Profile extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        Button mybutton = (Button) findViewById(R.id.todriver);

        mybutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, Driver.class);
                startActivity(intent);
            }
        });
    }


}
