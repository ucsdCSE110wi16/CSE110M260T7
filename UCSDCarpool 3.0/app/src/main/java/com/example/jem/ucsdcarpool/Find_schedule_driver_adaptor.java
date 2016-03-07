package com.example.jem.ucsdcarpool;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by xiejingwen on 3/4/16.
 */
public class Find_schedule_driver_adaptor extends ArrayAdapter<ScheduleDriver> {
    Context context;
    int layoutResourceId;
    ArrayList<ScheduleDriver> data = new ArrayList<ScheduleDriver>();

    static ScheduleDriver driver;

    public Find_schedule_driver_adaptor(Context context, int layoutResourceId,
                                 ArrayList<ScheduleDriver> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        UserHolder holder = null;



        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new UserHolder();
            holder.textName = (TextView) row.findViewById(R.id.DrivertextView1);
            holder.textAddress = (TextView) row.findViewById(R.id.DrivertextView2);
            holder.textDetail = (TextView) row.findViewById(R.id.DrivertextView3);
            //holder.btnEdit = (Button) row.findViewById(R.id.button1);
            holder.btnView = (Button) row.findViewById(R.id.Driverbutton2);

            holder.btnView.setTag(position);

            row.setTag(holder);
        } else {
            holder = (UserHolder) row.getTag();
        }

        ScheduleDriver schedule = data.get(position);
        holder.textName.setText(schedule.getPassenger_name());
        holder.textAddress.setText(schedule.getDestination());
        holder.textDetail.setText(schedule.getMonth() + " / " + schedule.getDay() + "   " + schedule.getHour() + " : " + schedule.getMinute());

//        holder.btnEdit.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                Log.i("Edit Button Clicked", "**********");
//                Toast.makeText(context, "Edit button Clicked",
//                        Toast.LENGTH_LONG).show();
//            }
//        });
        holder.btnView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Delete listview from firebase: write code here
//                Log.i("Delete Button Clicked", "**********");
//                Toast.makeText(context, "Delete button Clicked",
//                        Toast.LENGTH_LONG).show();
                Log.i("View Button", "clicked");
                if(v == null)
                {
                    System.out.println("view is null");
                }
                int pos = (Integer)v.getTag();
                System.out.println("position is : " + pos);
                driver = data.get(pos);
                Intent intent = new Intent(context,Find_schedule_driver_Display.class);
                context.startActivity(intent);


            }
        });



        return row;
    }

    static class UserHolder {
        TextView textName;
        TextView textAddress;
        TextView textDetail;
        //Button btnEdit;
        Button btnView;

    }
}
