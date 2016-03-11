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
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by xiejingwen on 3/4/16.
 */
public class Passenger_ui_adaptor extends ArrayAdapter<Schedule> {
    Context context;
    int layoutResourceId;
    ArrayList<Schedule> data = new ArrayList<Schedule>();

    private Firebase mRef = new Firebase("https://ucsdcarpool.firebaseio.com");

    private Schedule toDelete;
    static Schedule sche;

    public Passenger_ui_adaptor(Context context, int layoutResourceId,
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
            holder.btnView = (Button) row.findViewById(R.id.buttonView);

            holder.btnView.setTag(position);
            holder.btnDelete.setTag(position);

            row.setTag(holder);
        } else {
            holder = (UserHolder) row.getTag();
        }
        Schedule schedule = data.get(position);
        holder.textName.setText(schedule.getPassenger_name());
        holder.textAddress.setText(schedule.getDestination());
        holder.textDetail.setText(schedule.getMonth() + " / " + schedule.getDay() + "   " + schedule.getHour() + " : " + schedule.getMinute());


        holder.btnDelete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Delete listview from firebase: write code here
                Log.i("Delete Button Clicked", "**********");
                Toast.makeText(context, "Delete button Clicked",
                        Toast.LENGTH_LONG).show();

                int pos = (Integer)v.getTag();

                toDelete = data.get(pos);

                final Firebase schRef = mRef.child("schedules").child("schedule_id");

                schRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for(DataSnapshot snap: dataSnapshot.getChildren()) {
                            if (snap.child("passenger_uid").getValue(String.class).equals(toDelete.getPassenger_uid())) {
                                if (snap.child("schedule_month").getValue(int.class).equals(toDelete.getMonth())) {
                                    if (snap.child("schedule_day").getValue(int.class).equals(toDelete.getDay())) {
                                        if (snap.child("schedule_hour").getValue(int.class).equals(toDelete.getHour())) {
                                            if (snap.child("schedule_minutes").getValue(int.class).equals(toDelete.getMinute())) {
                                                final String index = snap.getKey();
                                                Firebase dRef = schRef.child(index);

                                                dRef.child("schedule_deleted").setValue(true);
                                                dRef.child("schedule_taken").setValue(false);

                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }


                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });

                Intent k = new Intent(context, Passenger_ui.class);
//
                context.startActivity(k);

            }
        });
        holder.btnView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO when click it will go to new activity
//                Log.i("View Button Clicked", "**********");
//                Toast.makeText(context, "Delete button Clicked",
//                        Toast.LENGTH_LONG).show();
                int pos = (Integer)v.getTag();
                sche = data.get(pos);
                Intent k = new Intent(context, Passenger_ui_display.class);
//                k.putExtra("position", position);
//                // Or / And
//                k.putExtra("id", id);
                context.startActivity(k);
            }
        });


        //TODO:make button dissapear/visible using the following code:
        String id = schedule.getDriver_uid();
        String uid = mRef.getAuth().getUid();
        if (!id.equals(uid)){
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
        Button btnView;
    }
    //Adding click listener to listview
//    protected void onCreate(Bundle savedInstanceState) {

}