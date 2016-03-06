package com.example.jem.ucsdcarpool;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

/**
 * Created by xiejingwen on 3/5/16.
 */
public class Passenger_ui_display extends FragmentActivity {

    private GoogleMap googleMap;
    private CameraPosition cameraPosition;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.passenger_ui_display);

        Button arrive = (Button) findViewById(R.id.arrive_back);


        final Button Back = (Button) findViewById(R.id.btnhomeBack);

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(Passenger_ui_display.this, Passenger_ui.class);
                startActivity(k);
            }
        });

        arrive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(Passenger_ui_display.this, Menu.class);
                startActivity(k);
            }
        });

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