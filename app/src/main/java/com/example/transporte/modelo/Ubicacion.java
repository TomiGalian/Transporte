package com.example.transporte.modelo;

import android.Manifest;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;

import java.io.Serializable;

public class Ubicacion implements Serializable {

    private String latitud;

    private String longitud;

    public Ubicacion(String latitud, String longitud){
        this.latitud=latitud;
        this.longitud=longitud;
    }

    public String getLatitud(){
        return latitud;
    }

    public void setLatitud(String latitud) {

    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {

    }
}
