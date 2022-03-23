package com.example.transporte.modelo.estados;

import android.net.Uri;

import com.example.transporte.modelo.Viaje;

public class Fuera implements Estado{

    @Override
    public boolean mandarUbicacion() {
        return false;
    }

    @Override
    public String getEstado() {
        return "Fuera";
    }

    @Override
    public Uri conducir(Viaje viaje) {
        return null;
    }
}
