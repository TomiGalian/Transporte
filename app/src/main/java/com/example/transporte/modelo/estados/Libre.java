package com.example.transporte.modelo.estados;

import android.net.Uri;

import com.example.transporte.modelo.Viaje;

public class Libre implements Estado {

    @Override
    public boolean mandarUbicacion() {
        return true;
    }

    @Override
    public String getEstado() {
        return "Libre";
    }

    @Override
    public Uri conducir(Viaje viaje) {
        return null;
    }


}
