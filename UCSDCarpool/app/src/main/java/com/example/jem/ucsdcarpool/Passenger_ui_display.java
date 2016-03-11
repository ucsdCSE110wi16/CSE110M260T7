package com.example.jem.ucsdcarpool;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

/**
 * Created by xiejingwen on 3/5/16.
 */
public class Passenger_ui_display extends FragmentActivity {
    // create variables
    private Firebase mRef ;
    private GoogleMap googleMap;
    private CameraPosition cameraPosition;

    // create view
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.passenger_ui_display);
        Firebase.setAndroidContext(this);
        mRef = new Firebase("https://ucsdcarpool.firebaseio.com");
        Button arrive = (Button) findViewById(R.id.arrive_back);

        // get all the text
        TextView date = (TextView) findViewById(R.id.CarpoolInfo_Date);
        TextView time = (TextView) findViewById(R.id.FIREBASE_Time);
        TextView dest = (TextView) findViewById(R.id.FIREBASE_destination);
        TextView pick = (TextView) findViewById(R.id.FIREBASE_pickup);

        // set text detail
        date.setText(Passenger_ui_adaptor.sche.getMonth() + " / " + Passenger_ui_adaptor.sche.getDay());
        time.setText(Passenger_ui_adaptor.sche.getHour() + " : " + Passenger_ui_adaptor.sche.getMinute());
        dest.setText(Passenger_ui_adaptor.sche.getDestination());
        pick.setText(Passenger_ui_adaptor.sche.getPick_loc());

        final Button Back = (Button) findViewById(R.id.btnhomeBack);

        // get map
        try {
            initializeMap();

            googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);

            googleMap.getUiSettings().setZoomControlsEnabled(true);

            // Mostrar / ocultar boton de localización
            googleMap.getUiSettings().setMyLocationButtonEnabled(true);

            // Mostrar / ocultar icon de compas
            googleMap.getUiSettings().setCompassEnabled(true);

            // Mostrar / ocultar evento de rotar
            googleMap.getUiSettings().setRotateGesturesEnabled(true);

            // Mostrar / ocultar funcionalidad del zoom
            googleMap.getUiSettings().setZoomGesturesEnabled(true);

            String location_d = Passenger_ui_adaptor.sche.getDestination();
            String location_p = Passenger_ui_adaptor.sche.getPick_loc();
            List<Address> addressList_des = null;
            List<Address> addressList_pick = null;

            Geocoder geocoder = new Geocoder(Passenger_ui_display.this);
            try {
                addressList_des = geocoder.getFromLocationName(location_d, 1);
                addressList_pick = geocoder.getFromLocationName(location_p, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Address address_des = addressList_des.get(0);
            LatLng DestlatLng = new LatLng(address_des.getLatitude(), address_des.getLongitude());

            Address address_pic = addressList_pick.get(0);
            LatLng PicklatLng = new LatLng(address_pic.getLatitude(), address_pic.getLongitude());

//            // crear marker
            MarkerOptions marker = new MarkerOptions().position(DestlatLng).title("Instituto Tecnologico de Cd. Altamirano");
//            // cambiar color marcardor
            marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
//            // agregar marker
            googleMap.addMarker(marker);

            MarkerOptions marker1 = new MarkerOptions().position(PicklatLng).title("Instituto Tecnologico de Cd. Altamirano");
//            // cambiar color marcardor
            marker1.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
//            // agregar marker
            googleMap.addMarker(marker1);

            cameraPosition = new CameraPosition.Builder()
                    .target(DestlatLng)              // Sets the center of the map to ZINTUN
                    .zoom(17)
                    .bearing(0)                // Sets the orientation of the camera to east
                    .tilt(30)                  // Sets the tilt of the camera to 30 degrees
                    .build();                  // Creates a CameraPosition from the builder
//                    googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


        } catch (Exception e) {
            e.printStackTrace();
        }

        // setup back button listener
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(Passenger_ui_display.this, Passenger_ui.class);
                startActivity(k);
            }
        });

        // setup arrive button listener
        arrive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Firebase uRef = mRef.child("schedules/schedule_id");

                uRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for (DataSnapshot snap : dataSnapshot.getChildren()) {
                            // check whether schedule match
                            if (snap.child("passenger_uid").getValue(String.class).equals(Passenger_ui_adaptor.sche.getPassenger_uid())) {
                                if (snap.child("schedule_month").getValue(int.class).equals(Passenger_ui_adaptor.sche.getMonth())) {
                                    if (snap.child("schedule_day").getValue(int.class).equals(Passenger_ui_adaptor.sche.getDay())) {
                                        if (snap.child("schedule_hour").getValue(int.class).equals(Passenger_ui_adaptor.sche.getHour())) {
                                            if (snap.child("schedule_minutes").getValue(int.class).equals(Passenger_ui_adaptor.sche.getMinute())) {
                                                final String index = snap.getKey();

                                                Firebase dRef = uRef.child(index);

                                                dRef.child("schedule_deleted").setValue(true);

                                                Context context = getApplicationContext();
                                                CharSequence text = "Schedule successfully finished!";
                                                int duration = Toast.LENGTH_SHORT;


                                                Toast toast = Toast.makeText(context, text, duration);
                                                toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, 10, 10);
                                                toast.show();

                                                Intent k = new Intent(Passenger_ui_display.this, Passenger_ui.class);
                                                startActivity(k);

                                                break;

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


                Intent k = new Intent(Passenger_ui_display.this, Menu.class);
                startActivity(k);
            }
        });
    }

    @Override
    protected void onResume () {
        super.onResume();
        initializeMap();
    }



    private void initializeMap() {
        if (googleMap == null) {
            googleMap = ((com.google.android.gms.maps.MapFragment) getFragmentManager().findFragmentById(
                    R.id.find_map_schedule)).getMap();

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