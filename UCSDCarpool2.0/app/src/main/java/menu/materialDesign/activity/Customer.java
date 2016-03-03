package menu.materialDesign.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.xiejingwen.ucsdcarpool20.MenuActivity;
import com.example.xiejingwen.ucsdcarpool20.R;

/**
 * Created by Jem on 3/2/16.
 */
public class Customer extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_profile);

        Button mybutton = (Button) findViewById(R.id.driver);

        mybutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Customer.this, Driver.class);
                startActivity(intent);
            }
        });
    }
}

