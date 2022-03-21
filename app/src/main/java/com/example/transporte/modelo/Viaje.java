package com.example.transporte.modelo;


import android.net.Uri;

public class Viaje {

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

    private String format(String latitud,String longitud){
        return "geo:0,0?q=" + latitud + "," +  longitud + "( Location title)";
    }


}
