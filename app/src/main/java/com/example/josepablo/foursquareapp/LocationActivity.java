package com.example.josepablo.foursquareapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.josepablo.foursquareapp.objects.Ubicacion;
import com.example.josepablo.foursquareapp.recycler.RecyclerViewClickListener;
import com.example.josepablo.foursquareapp.recycler.RecyclerViewCustomAdapter;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;
// TODO EXTRA. SharedPreferences, OKHttp, Gson


// TODO 1. Implementar interfaces de la Google API
public class LocationActivity extends AppCompatActivity
        implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    // TODO 2. Definir Varialbes
    private GoogleApiClient googleApiClient;
    private Location location;
    private final int REQUEST_LOCATION = 1;

    String lat;
    String lon;

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        // TODO 3.
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        processLocation();
    }

    // TODO 4. Mandar a llamar la funcion processLocation()
    private void processLocation(){
        //Se trata de obtener la última ubicación registrada
        getLocation();

        // Si ubicación es diferente de nulo se actualizan los campos para escribir las variables de la ubicacion

        if(location != null){
            updateLocationUI();
        }
    }

    private void getLocation(){
        // Se valida que haya permisos garantizados
        if(isLocationPermissionGranted()){
            // Si los hay se regresa la ubicacion al objeto Location
            try {
                location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
            } catch (SecurityException se){
                Toast.makeText(this, "Unknown Security Exception:" + se, Toast.LENGTH_SHORT).show();
            }
        } else {
            // De otra forma se le piden permisos al usuario
            requestPermission();
        }
    }

    private boolean isLocationPermissionGranted(){
        // Valida si ya se dio permiso o no
        int permission = ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        // Se regresa el resultado del permiso
        return permission == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            Toast.makeText(this, "No quisiste dar acceso a tu ubicacion", Toast.LENGTH_SHORT).show();
        } else {
            ActivityCompat.requestPermissions(
                    this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION
            );
        }
    }

    private void updateLocationUI(){
        lat = String.valueOf(location.getLatitude());
        lon = String.valueOf(location.getLongitude());
        Toast.makeText(this, "Location: "+lat+", "+lon, Toast.LENGTH_SHORT).show();

        // TODO E.01: Load RecyclerView List
        loadContent();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        if(requestCode == REQUEST_LOCATION){
            if(grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                try {
                    location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
                } catch (SecurityException se){
                    Toast.makeText(this, "Unknown Security Exception:" + se, Toast.LENGTH_SHORT).show();
                }
                if(location != null){
                    updateLocationUI();
                } else {
                    Toast.makeText(this, "Ubicación no encontrada", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Permisos no Otorgados", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onConnectionSuspended(int i){
        // STUB function
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult){

    }

    @Override
    protected void onStart(){
        super.onStart();
        googleApiClient.connect();
    }

    @Override
    protected void onStop(){
        super.onStop();
        googleApiClient.disconnect();
    }

    private void loadContent(){
        recyclerView = (RecyclerView) findViewById(R.id.rvLocations);
        recyclerView.setHasFixedSize(true);

        linearLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(linearLayoutManager);

        //Add content
        final ArrayList<Ubicacion> locations = loadLocations();

        RecyclerViewCustomAdapter adapter = new RecyclerViewCustomAdapter(locations, new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                //Toast.makeText(LocationActivity.this, "ELEMENTO " + position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LocationActivity.this, DetailActivity.class);
                intent.putExtra("parcelable_location", locations.get(position));

                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    private ArrayList<Ubicacion> loadLocations(){
        ArrayList<Ubicacion> ubicaciones = new ArrayList<>();

        // TODO E.02: Implement API call to get locations
        //
        //

        ubicaciones.add(new Ubicacion("Nortesur", "Ignacio Comonfort 302 (Jose Ma. Pino Suarez), Toluca de Lerdo", R.drawable.nortesur));
        ubicaciones.add(new Ubicacion("Cinepolis VIP", "Galerías Toluca (Av. Paseo Tollocan & Robert Fulton), Toluca de Lerdo", R.drawable.cinepolis));
        ubicaciones.add(new Ubicacion("Bistró Mecha Centro Histórico", "Aldama Nte. 102, Toluca de Lerdo", R.drawable.mecha_centro));
        ubicaciones.add(new Ubicacion("Bistro Mecha Primero de Mayo", "Av. Primero De Mayo (Josefa Ortiz de Dominguez), Toluca de Lerdo", R.drawable.mecha_mayo));
        ubicaciones.add(new Ubicacion("Fonda Argentina", "Sebastián Lerdo De Tejada, Toluca de Lerdo", R.drawable.fonda));

        return ubicaciones;
    }

}
