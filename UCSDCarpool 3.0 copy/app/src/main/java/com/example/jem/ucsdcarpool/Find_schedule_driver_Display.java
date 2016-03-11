package com.example.jem.ucsdcarpool;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xiejingwen on 3/5/16.
 */
public class Find_schedule_driver_Display extends Activity{
    private Firebase mRef;
    private GoogleMap googleMap;
    private CameraPosition cameraPosition;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_schedule_driver_display);
        Firebase.setAndroidContext(this);

        TextView date = (TextView) findViewById(R.id.CarpoolInfo_Date);
        TextView time = (TextView) findViewById(R.id.CarpoolInfo_Time);
        TextView dest = (TextView)findViewById(R.id.CarpoolInfo_Destination);
        TextView pick_loc = (TextView) findViewById(R.id.CarpoolInfo_Pick_Loc);

        date.setText(Find_schedule_driver_adaptor.driver.getMonth() + " / " + Find_schedule_driver_adaptor.driver.getDay());
        time.setText(Find_schedule_driver_adaptor.driver.getHour() + " : " + Find_schedule_driver_adaptor.driver.getMinute());
        dest.setText(Find_schedule_driver_adaptor.driver.getDestination());
        pick_loc.setText(Find_schedule_driver_adaptor.driver.getPick_loc());


        final Button Confirm = (Button) findViewById(R.id.btndriverConfirm);
        final Button Back = (Button) findViewById(R.id.btndriverBack);
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

            String location_d = Find_schedule_driver_adaptor.driver.getDestination();
            String location_p = Find_schedule_driver_adaptor.driver.getPick_loc();
            List<Address> addressList_des = null;
            List<Address> addressList_pick = null;

            Geocoder geocoder = new Geocoder(Find_schedule_driver_Display.this);
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

//            double latitude = 18.370955;
//            double longitude = -100.679940;
//
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
                    .zoom(17)                  // 缩放比例
                    .bearing(0)                // Sets the orientation of the camera to east
                    .tilt(30)                  // Sets the tilt of the camera to 30 degrees
                    .build();                  // Creates a CameraPosition from the builder
//                    googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


            /*
            // ROSE color icon
            marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE));

            // GREEN color icon
            marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
            */

//            double latitude = 18.370955;
//            double longitude = -100.679940;
//
//            // crear marker
//            MarkerOptions marker = new MarkerOptions().position(new LatLng(latitude, longitude)).title("Instituto Tecnologico de Cd. Altamirano");
//            // cambiar color marcardor
//            marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
//            // agregar marker
//            googleMap.addMarker(marker);

        } catch (Exception e) {
            e.printStackTrace();
        }

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(Find_schedule_driver_Display.this, Find_schedule_driver.class);
                startActivity(k);
            }
        });
        //TODO:set onclilistener for confirm button and link to firebase
        Confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRef = new Firebase("https://ucsdcarpool.firebaseio.com");
                final Firebase uRef = mRef.child("schedules/schedule_id");

                uRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for(DataSnapshot snap: dataSnapshot.getChildren())
                        {
                            if(snap.child("passenger_uid").getValue(String.class).equals(Find_schedule_driver_adaptor.driver.getPassenger_uid()))
                            {
                                if(snap.child("schedule_month").getValue(int.class).equals(Find_schedule_driver_adaptor.driver.getMonth()))
                                {
                                    if(snap.child("schedule_day").getValue(int.class).equals(Find_schedule_driver_adaptor.driver.getDay()))
                                    {
                                        if(snap.child("schedule_hour").getValue(int.class).equals(Find_schedule_driver_adaptor.driver.getHour()))
                                        {
                                            if(snap.child("schedule_minutes").getValue(int.class).equals(Find_schedule_driver_adaptor.driver.getMinute()))
                                            {
                                                final String index = snap.getKey();
                                                Firebase dRef = mRef.child("user_info").child(mRef.getAuth().getUid());

                                                dRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                        String driver_name = dataSnapshot.child("user_name").getValue(String.class);

                                                        Map<String, Object> map = new HashMap<String, Object>();
                                                        map.put("driver_name", driver_name);
                                                        map.put("driver_uid", mRef.getAuth().getUid());
                                                        map.put("schedule_taken", true);
                                                        uRef.child(index).updateChildren(map);

                                                        Context context = getApplicationContext();
                                                        CharSequence text = "Driver authorized, schedule added to your home!";
                                                        int duration = Toast.LENGTH_SHORT;


                                                        Toast toast = Toast.makeText(context, text, duration);
                                                        toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, 10, 10);
                                                        toast.show();

                                                        Intent k = new Intent(Find_schedule_driver_Display.this, Menu.class);
                                                        startActivity(k);
                                                    }

                                                    @Override
                                                    public void onCancelled(FirebaseError firebaseError) {

                                                    }
                                                });

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
                    R.id.find_map_driver_schedule)).getMap();

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



