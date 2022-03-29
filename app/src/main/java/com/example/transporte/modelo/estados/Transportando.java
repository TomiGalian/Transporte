package com.example.transporte.modelo.estados;

import android.net.Uri;

import com.example.transporte.modelo.Viaje;

public class Transportando implements Estado{

    @Override
    public boolean mandarUbicacion() {
        return true;
    }

    @Override
    public String getEstado() {
        return "Transportando";
    }

    @Override
    public Uri conducir(Viaje viaje) {
        return viaje.irHaciaDestino();
    }

    @Override
    public boolean estaTransportando(){
        return true;
    }
}
