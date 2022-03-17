package com.example.transporte.modelo;


import android.net.Uri;

public class Viaje {

    private Conductor conductor;

    private Pasajero pasajero;

    //Tiene una tarifa el viaje o va a parte?

    public void Viaje(Conductor conductor, Pasajero pasajero){
        this.conductor = conductor;
        this.pasajero = pasajero;
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
