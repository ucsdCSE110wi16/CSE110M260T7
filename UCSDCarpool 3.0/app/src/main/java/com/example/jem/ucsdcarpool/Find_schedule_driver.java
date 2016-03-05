package com.example.jem.ucsdcarpool;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Jem on 3/3/16.
 */
public class Find_schedule_driver extends Activity {


        ListView userList;
        Find_schedule_driver_adaptor userAdapter;
        ArrayList<ScheduleDriver> userArray = new ArrayList<ScheduleDriver>();


@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.find_schedule_driver);

    /**
     * @TODO :ADD INFORMATION FROM DATABASE TO THE BELOW ARRAYLIST
     */
    userArray.add(new ScheduleDriver("Schedule name", "Schedule address", "Schedule Detail"));
    userArray.add(new ScheduleDriver("Morning Schedule", "UCSD", "drive info+data+time"));
    userArray.add(new ScheduleDriver("Afternoon Schedule", "UCSD", "drive info+data+time"));
    userArray.add(new ScheduleDriver("Weekend Schedule", "UCSD", "drive info+data+time"));
    userArray.add(new ScheduleDriver("Monday Schedule", "UCSD", "drive info+data+time"));
    userArray.add(new ScheduleDriver("Holiday Schedule", "UCSD", "drive info+data+time"));
    userArray.add(new ScheduleDriver("Today Schedule", "UCSD", "drive info+data+time"));
    /**
     * set item into adapter
     */
    userAdapter = new Find_schedule_driver_adaptor(Find_schedule_driver.this, R.layout.find_schedule_driver_adaptor, userArray);
    userList = (ListView) findViewById(R.id.Driverlistview1);
    userList.setItemsCanFocus(false);
    userList.setAdapter(userAdapter);
    /**
     * get on item click listener
     */
//    userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//        @Override
//        public void onItemClick(AdapterView<?> parent, View v,
//                                final int position, long id) {
//            Log.i("List View Clicked", "**********");
//            Toast.makeText(Find_schedule_driver.this,
//                    "List View Clicked:" + position, Toast.LENGTH_LONG)
//                    .show();
//        }
//    });

}

        }



//public class Find_schedule_driver extends AppCompatActivity {
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.find_schedule_driver);
//
////        Button mybutton1 = (Button) findViewById(R.id.schedule1);
////        Button mybutton2 = (Button) findViewById(R.id.schedule2);
////        Button mybutton3 = (Button) findViewById(R.id.schedule3);
////        Button mybutton4 = (Button) findViewById(R.id.schedule4);
////        Button mybutton5 = (Button) findViewById(R.id.schedule5);
////        Button mybutton6 = (Button) findViewById(R.id.schedule6);
//
//
//        mybutton1.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(Find_schedule_driver.this, Schedule_Find_Schedule.class);
//                startActivity(intent);
//
//            }
//        });
//
//        mybutton2.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Find_schedule_driver.this, Schedule_Find_Schedule.class);
//                startActivity(intent);
//            }
//        });
//
//        mybutton3.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Find_schedule_driver.this, Schedule_Find_Schedule.class);
//                startActivity(intent);
//            }
//        });
//
//        mybutton4.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Find_schedule_driver.this, Schedule_Find_Schedule.class);
//                startActivity(intent);
//            }
//        });
//
//        mybutton5.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Find_schedule_driver.this, Schedule_Find_Schedule.class);
//                startActivity(intent);
//
//            }
//        });
//
//        mybutton6.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Find_schedule_driver.this, Schedule_Find_Schedule.class);
//                startActivity(intent);
//
//            }
//        });
//    }

