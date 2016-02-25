package menu.materialDesign.activity;

/**
 * Created by xiejingwen on 2/24/16.
 */

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.xiejingwen.ucsdcarpool20.R;

public class Drive_set extends Fragment  {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.drive_set, container, false);
        Button buttonSetTime = (Button) view.findViewById(R.id.btn_setTime);
        buttonSetTime.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getFragmentManager(), "TimePicker");

            }
        });
        Button buttonSetDate = (Button) view.findViewById(R.id.btn_setDate);
        buttonSetDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getFragmentManager(), "DatePicker");

            }
        });


//        Button buttonSetDate = (Button) view.findViewById(R.id.btn_setDate);
//        buttonSetDate.setOnClickListener(this);

        return view;
    }

    //@Override
//    public void onClick(View v) {
////        DialogFragment newFragment1 = new DatePickerFragment();
////        newFragment1.show(getFragmentManager(), "DatePicker");
//
//        DialogFragment newFragment = new TimePickerFragment();
//        newFragment.show(getFragmentManager(), "TimePicker");
//
//    }
}
