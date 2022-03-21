package com.example.transporte.activities;

import static android.location.Location.convert;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.transporte.R;
import com.example.transporte.modelo.Conductor;
import com.example.transporte.web.WebGeolocalizacion;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

public class MainActivity extends AppCompatActivity {

    private Switch aSwitch;

    private LinearLayout informacionPasajero;

    private TextView nombre,recogidaRef,destinoRef;

    private Button botonCoordenadas;

    private static final int REQUEST_LOCATION_PERMISSION = 1; //Se usa para chequear si tiene permiso
    private static final int FORMAT_DEGREES = 0;

    private boolean mTrackingLocation;
    private FusedLocationProviderClient mFusedLocationClient;
    private LocationCallback mLocationCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        Conductor conductor = intent.getParcelableExtra("conductor");
        WebGeolocalizacion geoloc = new WebGeolocalizacion();

        Intent i = new Intent(getApplicationContext(),PopActivity.class);
        startActivity(i);

        aSwitch = findViewById(R.id.swEstado);
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    conductor.libre();
                }
                else
                    conductor.desconectar();
            }
        });

        informacionPasajero = findViewById(R.id.infoPasajero);
        nombre = findViewById(R.id.nombrePasajero);
        recogidaRef = findViewById(R.id.recogidaReferencia);
        destinoRef = findViewById(R.id.destinoReferencia);

        botonCoordenadas = findViewById(R.id.btnPaxAbordo );

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        botonCoordenadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mTrackingLocation) {
                    startTrackingLocation();    /**Hay que pasar esto a que este solo, es decir fuera del onClick*/
                } else {
                    stopTrackingLocation();
                }
            }
        });

        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                Log.e( "Latitud ", convert(locationResult.getLastLocation().getLatitude(),FORMAT_DEGREES)  );
                Log.e( "Longitud ",convert(locationResult.getLastLocation().getLongitude(),FORMAT_DEGREES) );
                /**
                 *  Aca llama a conectar del WebGeolocalizacion
                 */
                geoloc.conectarCoordenadas( getApplicationContext(), conductor );
                if(conductor.tieneViaje()){
                    Intent i = new Intent(getApplicationContext(),PopActivity.class);
                    startActivity(i);
                }

            }
        };

    }

    /**
     * Lo llama cuando se acepta el pop-up del viaje
     */
    public void reciboViaje(View view){
        informacionPasajero.setVisibility(View.VISIBLE);
        nombre.setText("Nombre: Julian Alvarez");       //getNombrePasajero()
        recogidaRef.setText("Recogida: Hotel Hilton");  //getReferenciaOrigen()
        destinoRef.setText("Destino: Areopuerto");      //getReferenciaDestino()
    }

    public void navegar(View view){
        /*Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);*/
    }

    private void startTrackingLocation() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]
                            {Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION_PERMISSION);
        } else {
            mTrackingLocation = true;
            mFusedLocationClient.requestLocationUpdates(getLocationRequest(),mLocationCallback,null /* Looper */);

        }
    }

    private void stopTrackingLocation() {
        if (mTrackingLocation) {
            mTrackingLocation = false;
        }
    }

    private LocationRequest getLocationRequest() {
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority( LocationRequest.PRIORITY_HIGH_ACCURACY);
        return locationRequest;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,@NonNull String[] permissions,@NonNull int[] grantResults) {
        super.onRequestPermissionsResult( requestCode, permissions, grantResults );
        switch (requestCode) {
            case REQUEST_LOCATION_PERMISSION:

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startTrackingLocation();
                } else {
                    Toast.makeText( this,"Permiso Denegado: no puede funcionar",Toast.LENGTH_SHORT ).show();
                }
                break;
        }
    }

}




