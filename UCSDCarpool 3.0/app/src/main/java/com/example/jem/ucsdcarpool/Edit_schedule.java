package com.example.jem.ucsdcarpool;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import java.util.ArrayList;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class Edit_schedule extends Activity {
    ListView userList;
    Edit_schedule_adaptor userAdapter;
    ArrayList<Schedule> userArray = new ArrayList<Schedule>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_schedule);

        /**
         * @TODO :ADD INFORMATION FROM DATABASE TO THE BELOW ARRAYLIST
         */


        /*
        userArray.add(new Schedule("Schedule name", "Schedule address", "Schedule Detail"));
        userArray.add(new Schedule("Morning Schedule", "UCSD", "drive info+data+time"));
        userArray.add(new Schedule("Afternoon Schedule", "UCSD", "drive info+data+time"));
        userArray.add(new Schedule("Weekend Schedule", "UCSD", "drive info+data+time"));
        userArray.add(new Schedule("Monday Schedule", "UCSD", "drive info+data+time"));
        userArray.add(new Schedule("Holiday Schedule", "UCSD", "drive info+data+time"));
        userArray.add(new Schedule("Today Schedule", "UCSD", "drive info+data+time"));

        */


        /**
         * set item into adapter
         */
        userAdapter = new Edit_schedule_adaptor(Edit_schedule.this, R.layout.edit_schedule_row, userArray);
        userList = (ListView) findViewById(R.id.listView);
        userList.setItemsCanFocus(false);
        userList.setAdapter(userAdapter);
        /**
         * get on item click listener
         */
        userList.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    final int position, long id) {
                Log.i("List View Clicked", "**********");
                Toast.makeText(Edit_schedule.this,
                        "List View Clicked:" + position, Toast.LENGTH_LONG)
                        .show();
            }
        });

    }

}
