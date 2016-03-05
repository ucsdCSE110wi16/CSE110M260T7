package com.example.jem.ucsdcarpool;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Edit_schedule_adaptor extends ArrayAdapter<Schedule> {
    Context context;
    int layoutResourceId;
    ArrayList<Schedule> data = new ArrayList<Schedule>();

    public Edit_schedule_adaptor(Context context, int layoutResourceId,
                                 ArrayList<Schedule> data) {
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
            holder.textName = (TextView) row.findViewById(R.id.textView1);
            holder.textAddress = (TextView) row.findViewById(R.id.textView2);
            holder.textDetail = (TextView) row.findViewById(R.id.textView3);
            //holder.btnEdit = (Button) row.findViewById(R.id.button1);
            holder.btnDelete = (Button) row.findViewById(R.id.button2);

            row.setTag(holder);
        } else {
            holder = (UserHolder) row.getTag();
        }
        Schedule schedule = data.get(position);
        holder.textName.setText(schedule.getName());
        holder.textAddress.setText(schedule.getAddress());
        holder.textDetail.setText(schedule.getScheduledetail());

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
        holder.btnDelete.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Delete listview from firebase: write code here
                Log.i("Delete Button Clicked", "**********");
                Toast.makeText(context, "Delete button Clicked",
                        Toast.LENGTH_LONG).show();
            }
        });

        //TODO:make button dissapear/visible using the following code:
          String s = schedule.getAddress();
        if (s.equals("UCSD")){
            holder.displayD = (Button)row.findViewById(R.id.Display_Driver);
            holder.displayD.setVisibility(View.GONE);//make D INVISIBLE

        }







        return row;
    }

    static class UserHolder {
        TextView textName;
        TextView textAddress;
        TextView textDetail;
        //Button btnEdit;
        Button btnDelete;
        Button displayC;
        Button displayD;
    }
}