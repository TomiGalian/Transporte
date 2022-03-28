package com.example.transporte.activities;

import static android.location.Location.convert;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
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
import com.example.transporte.modelo.Ubicacion;
import com.example.transporte.web.WebEstados;
import com.example.transporte.web.WebGeolocalizacion;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

    private Switch aSwitch;

    private LinearLayout informacionPasajero;

    private TextView nombre,recogidaRef,destinoRef;

    private Conductor conductor;

    private Button botonABordo, botonFinalizado, botonNavegar;

    private static final int REQUEST_LOCATION_PERMISSION = 1; //Se usa para chequear si tiene permiso
    private static final int FORMAT_DEGREES = 0;

    private boolean mTrackingLocation;
    private FusedLocationProviderClient mFusedLocationClient;
    private LocationCallback mLocationCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO Cuando apreta el boton para atras pierde el viaje

        Intent intent = getIntent();
        conductor = (Conductor) intent.getSerializableExtra( "conductor" );
        WebGeolocalizacion geoloc = new WebGeolocalizacion();
        WebEstados estados = new WebEstados();

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        Log.e( "MAin","MandarUbicacion=" +  conductor.mandarUbicacion());
        aSwitch = findViewById(R.id.swEstado);
        if(conductor.mandarUbicacion()){
            aSwitch.setChecked( true );

        }

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    conductor.libre();
                    conductor.nuevoViaje("Robertito","-34.63546964021847","-58.364756302115595","Bombonera","-34.814810596004655","-58.53482840210992","Aeropuerto Ezeiza");
                    Intent i = new Intent(getApplicationContext(),PopActivity.class);
                    i.putExtra("conductor", (Serializable) conductor );
                    startActivity(i);
                }
                else{
                    conductor.desconectar();
                    //stopTrackingLocation(); //TODO: NO FUNCIONA ESTO xd
                }
                    //estados.conectarEstadoNuevo( getApplicationContext(), conductor ); //TODO
            }
        });

        informacionPasajero = findViewById(R.id.infoPasajero);
        nombre = findViewById(R.id.nombrePasajero);
        recogidaRef = findViewById(R.id.recogidaReferencia);
        destinoRef = findViewById(R.id.destinoReferencia);

        botonABordo = findViewById(R.id.btnPaxAbordo );
        botonABordo.setEnabled( false );
        botonFinalizado = findViewById( R.id.btnFinViaje );
        botonFinalizado.setEnabled( false );
        botonNavegar = findViewById( R.id.btnNavegar );
        botonNavegar.setEnabled( false );


        botonABordo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conductor.transportando();
                //estados.conectarEstadoNuevo( getApplicationContext(), conductor ); //TODO
                botonABordo.setEnabled( false );
                botonFinalizado.setEnabled( true );
            }
        });

        botonFinalizado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conductor.libre();
                //estados.conectarEstadoNuevo( getApplicationContext(), conductor ); //TODO
                botonFinalizado.setEnabled( false );
                botonNavegar.setEnabled( false );
                informacionPasajero.setVisibility(View.INVISIBLE);

            }
        });

        botonNavegar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri =conductor.conducir();
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        } );

        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                if (locationResult.getLastLocation() != null) {
                    Log.e( "Latitud ", convert(locationResult.getLastLocation().getLatitude(),FORMAT_DEGREES)  );
                    Log.e( "Longitud ",convert(locationResult.getLastLocation().getLongitude(),FORMAT_DEGREES) );
                    //geoloc.conectarCoordenadas( getApplicationContext(), conductor ); //TODO
                }
            }
        };

        if(conductor.tieneViaje()){
            viajeAceptado();
            Log.e( "ViajeAceptado","Acepto el viaje" );
        }

    }
    @Override
    protected void onStart() {
        super.onStart();
        startTrackingLocation();
        Log.e( "OnStart","Entro Al OnStart" );
    }

    public void viajeAceptado(){
        botonABordo.setEnabled( true );
        botonNavegar.setEnabled( true );
        informacionPasajero.setVisibility(View.VISIBLE);
        nombre.setText( "Nombre del Pasajaero: " + conductor.getViaje().getPasajero().getNombrePasajero() );
        recogidaRef.setText("Origen: "+conductor.getViaje().getPasajero().getReferenciaOrigen());
        destinoRef.setText("Destino: "+conductor.getViaje().getPasajero().getReferenciaDestino());


    }


    private void startTrackingLocation() {
        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_PERMISSION);
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
        locationRequest.setInterval(60000);
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




