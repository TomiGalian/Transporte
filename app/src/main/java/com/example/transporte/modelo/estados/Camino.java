package com.example.transporte.modelo.estados;

import android.net.Uri;

import com.example.transporte.modelo.Viaje;

public class Camino implements Estado{

    @Override
    public boolean mandarUbicacion() {
        return true;
    }

    @Override
    public String getEstado() {
        return "Camino";
    }

    @Override
    public Uri conducir(Viaje viaje) {
        return viaje.recogerPasajero();
    }


}
