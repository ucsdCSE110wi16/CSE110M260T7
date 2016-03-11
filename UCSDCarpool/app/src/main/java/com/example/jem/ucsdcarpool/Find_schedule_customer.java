package com.example.jem.ucsdcarpool;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




/**
 * Created by Jem on 3/2/16.
 */
public class Find_schedule_customer extends FragmentActivity {

    // the values
    private int selectedMinutes;
    private int selectedHour;
    private int selectedDay;
    private int selectedMonth;
    private Firebase mRef;
    private GoogleMap googleMap;
    private CameraPosition cameraPosition;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        // create view
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_schedule_customer);
        Firebase.setAndroidContext(this);

        // setup google map
        try {
            initializeMap();
            // googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            // googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
            // googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            // googleMap.setMapType(GoogleMap.MAP_TYPE_NONE);

            // Mostrar / ocultar tu ubicación
//            googleMap.setMyLocationEnabled(true);

            // Mostrar / ocultar los controles del zoom
            googleMap.getUiSettings().setZoomControlsEnabled(true);

            // Mostrar / ocultar boton de localización
            googleMap.getUiSettings().setMyLocationButtonEnabled(true);

            // Mostrar / ocultar icon de compas
            googleMap.getUiSettings().setCompassEnabled(true);

            // Mostrar / ocultar evento de rotar
            googleMap.getUiSettings().setRotateGesturesEnabled(true);

            // Mostrar / ocultar funcionalidad del zoom
            googleMap.getUiSettings().setZoomGesturesEnabled(true);


        } catch (Exception e) {
            e.printStackTrace();
        }

        // get the search button
        Button search = (Button) findViewById(R.id.search);


        // setup search button listeener
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // get all the input locations
                EditText location_des = (EditText) findViewById(R.id.inputdestination);
                EditText location_pick = (EditText) findViewById(R.id.inputpickup);
                String location_d = location_des.getText().toString();
                String location_p = location_pick.getText().toString();
                List<Address> addressList_des = null;
                List<Address> addressList_pick = null;

                // when locations are not null, zoom in the location
                if (location_d != null || !location_d.equals("")) {
                    Geocoder geocoder = new Geocoder(Find_schedule_customer.this);
                    try {
                        addressList_des = geocoder.getFromLocationName(location_d, 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Address address_des = addressList_des.get(0);
                    LatLng latLng = new LatLng(address_des.getLatitude(), address_des.getLongitude());
                    googleMap.addMarker(new MarkerOptions().position(latLng).title("Marker"));
                    cameraPosition = new CameraPosition.Builder()
                            .target(latLng)              // Sets the center of the map to ZINTUN
                            .zoom(17)
                            .bearing(0)                // Sets the orientation of the camera to east
                            .tilt(30)                  // Sets the tilt of the camera to 30 degrees
                            .build();                  // Creates a CameraPosition from the builder
                    googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                }

                // when locations are not null, zoom in the location
                if (location_p != null || !location_p.equals("")) {
                    Geocoder geocoder = new Geocoder(Find_schedule_customer.this);
                    try {
                        addressList_pick = geocoder.getFromLocationName(location_p, 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Address address_pick = addressList_pick.get(0);
                    LatLng latLng = new LatLng(address_pick.getLatitude(), address_pick.getLongitude());
                    googleMap.addMarker(new MarkerOptions().position(latLng).title("Marker"));
                    cameraPosition = new CameraPosition.Builder()
                            .target(latLng)              // Sets the center of the map to ZINTUN
                            .zoom(13)
                            .bearing(0)                // Sets the orientation of the camera to east
                            .tilt(30)                  // Sets the tilt of the camera to 30 degrees
                            .build();                  // Creates a CameraPosition from the builder
                    googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                }
            }
        });



        // ========== test for save
        Button buttonSave = (Button) findViewById(R.id.button_save);

        // setup save button listener
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // get values of date
                TextView vew = (TextView) findViewById(R.id.tv);
                String line = vew.getText().toString();

                getIntsToTime(line);

                TextView vew2 = (TextView) findViewById(R.id.tv_date);

                String line2 = vew2.getText().toString();

                if (line != null && line2 != null) {
                    getIntsToTime(line);
                    getIntsToDate(line2);

                    mRef = new Firebase("https://ucsdcarpool.firebaseio.com");
                    Firebase uRef = mRef.child("user_info");

                    final String uid = mRef.getAuth().getUid().toString();
                    uRef = uRef.child(uid);

                    final EditText pick = (EditText) findViewById(R.id.inputpickup);
                    final EditText desti = (EditText) findViewById(R.id.inputdestination);


                    //String customer_name = uRef. add the value to firebase
                    uRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            final String user_name = dataSnapshot.child("user_name").getValue(String.class);

                            boolean schedule_taken = false;

                            final Firebase pushSche = new Firebase("https://ucsdcarpool.firebaseio.com/schedules/schedule_id");


                            pushSche.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    long numberOfChild = dataSnapshot.getChildrenCount() + 1;
                                    Map<String, Object> map = new HashMap<String, Object>();
                                    map.put("passenger_name", user_name);
                                    map.put("passenger_uid", uid);
                                    map.put("schedule_taken", false);
                                    map.put("schedule_month", selectedMonth);
                                    map.put("schedule_day", selectedDay);
                                    map.put("schedule_hour", selectedHour);
                                    map.put("schedule_minutes", selectedMinutes);
                                    map.put("destination", pick.getText().toString());
                                    map.put("pickup_location", desti.getText().toString());
                                    map.put("schedule_deleted", false);
                                    //map.put("")
                                    pushSche.child("" + numberOfChild).setValue(map);
                                }

                                @Override
                                public void onCancelled(FirebaseError firebaseError) {

                                }
                            });
                        }

                        @Override
                        public void onCancelled(FirebaseError firebaseError) {

                        }
                    });


                }


            }

            // get date
            public void getIntsToDate(String str) {

                int index = 0;
                for (int i = 0; i < str.length(); i++) {
                    if (str.charAt(i) == '-') {
                        for (int j = i + 1; j < str.length(); j++) {
                            if (str.charAt(j) == '-') {
                                //System.out.println("——" + str.substring(i + 1, j));
                                selectedMonth = Integer.parseInt(str.substring(i + 1, j));
                                index = j + 1;
                            }
                        }
                    }
                    if (index != 0) {
                        break;
                    }
                }

                selectedDay = Integer.parseInt(str.substring(index, str.length()));
                }

            // get time
            public void getIntsToTime(String str) {
                boolean foo = str.contains("PM");
                String temp = str.substring(str.indexOf("\n"));

                selectedHour = Integer.parseInt(temp.substring(0, temp.indexOf(':')).
                        replaceAll("[^0-9]+", ""));
                selectedMinutes = Integer.parseInt(temp.substring(temp.indexOf(':'),
                        temp.lastIndexOf(' ')).replaceAll("[^0-9]+", ""));
                if (foo) {
                    selectedHour += 12;
                }


            }

        });
        //======
        //set activty content
        Button buttonSetTime = (Button) findViewById(R.id.btn_setTime);

        Button buttonSetDate = (Button) findViewById(R.id.btn_setDate);

        Button buttonBack = (Button) findViewById(R.id.btn_back);

        buttonSetTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogFragment newFragment = new TimePickFragment();
                newFragment.show(getSupportFragmentManager(), "TimePicker");

            }
        });
        buttonSetDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogFragment newFragment = new DatePickFragment();
                newFragment.show(getSupportFragmentManager(), "DatePicker");

            }
        });
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Find_schedule_customer.this, Menu.class);
                startActivity(intent);

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        initializeMap();
    }


    private void initializeMap() {
        if (googleMap == null) {
            googleMap = ((com.google.android.gms.maps.MapFragment) getFragmentManager().findFragmentById(
                    R.id.find_map)).getMap();

            // revisa si el mapa se ha creado o no
            if (googleMap == null) {
                Toast.makeText(getApplicationContext(),
                        "Lo siento! No se puede cargar el mapa", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
