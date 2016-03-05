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
 * Created by Jem on 3/2/16.
 */
public class Passenger_ui extends Activity {
    ListView userList;
    Passenger_ui_adaptor userAdapter;
    ArrayList<Schedule> userArray = new ArrayList<Schedule>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.passenger_ui);

        //make view scroll
        ListView lv = (ListView) findViewById(R.id.HomeListView);
        ListView list = (ListView) findViewById(R.id.HomeListView);
        setListViewHeightBasedOnChildren(list);
        lv.setOnTouchListener(new View.OnTouchListener() {
            // Setting on Touch Listener for handling the touch inside ScrollView
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Disallow the touch request for parent scroll on touch of child view
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        /**
         * @TODO :ADD INFORMATION FROM DATABASE TO THE BELOW ARRAYLIST
         */
        userArray.add(new Schedule("Schedule name", "Schedule address", "Schedule Detail"));
        userArray.add(new Schedule("Morning Schedule", "UCSD", "drive info+data+time"));
        userArray.add(new Schedule("Afternoon Schedule", "UCSD", "drive info+data+time"));
        userArray.add(new Schedule("Weekend Schedule", "UCSD", "drive info+data+time"));
        userArray.add(new Schedule("Monday Schedule", "UCSD", "drive info+data+time"));
        userArray.add(new Schedule("Holiday Schedule", "UCSD", "drive info+data+time"));
        userArray.add(new Schedule("Today Schedule", "UCSD", "drive info+data+time"));
        /**
         * set item into adapter
         */
        userAdapter = new Passenger_ui_adaptor(Passenger_ui.this, R.layout.passenger_ui_listview_row, userArray);
        userList = (ListView) findViewById(R.id.HomeListView);
        userList.setItemsCanFocus(false);
        userList.setAdapter(userAdapter);
        /**
         * get on item click listener
         */
        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    final int position, long id) {
                Log.i("List View Clicked", "**********");
                Toast.makeText(Passenger_ui.this,
                        "List View Clicked:" + position, Toast.LENGTH_LONG)
                        .show();
            }
        });

    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();




        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, ViewGroup.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

}



