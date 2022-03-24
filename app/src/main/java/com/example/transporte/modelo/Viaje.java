package com.example.transporte.modelo;


import android.net.Uri;

import java.io.Serializable;

public class Viaje implements Serializable {

    private Pasajero pasajero;

    public Viaje(Pasajero pasajero) {
        this.pasajero = pasajero;
    }

    public Viaje(){
        this.pasajero = null;
    }


    public Uri recogerPasajero(){
        Ubicacion ubicacionPasajero = pasajero.getRecogida();
        return Uri.parse(format(ubicacionPasajero.getLatitud(),ubicacionPasajero.getLongitud()));
    }

    public Uri irHaciaDestino(){
        Ubicacion ubicacionDestino = pasajero.getDestino();
        return Uri.parse(format(ubicacionDestino.getLatitud(),ubicacionDestino.getLongitud()));
    }

    private String format(String latitud,String longitud){      //TODO ver si se puede hacer directamente a direcciones
        return "geo:0,0?q=" + latitud + "," +  longitud + "( Location title)";
    }


    public Pasajero getPasajero() {
        return pasajero;
    }
}
