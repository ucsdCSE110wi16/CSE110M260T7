package menu.materialDesign.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.xiejingwen.ucsdcarpool20.R;

import java.util.Calendar;

/**
 * Created by xiejingwen on 2/25/16.
 */
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        //do some stuff for example write on log and update TextField on activity
        TextView tv_date = (TextView) getActivity().findViewById(R.id.tv_date);
        tv_date.setText("Your chosen date is...\n\n");
        //Display the user changed time on TextView
        tv_date.setText(tv_date.getText() + " " + year +  "-" + month + "-" + day);
    }


}