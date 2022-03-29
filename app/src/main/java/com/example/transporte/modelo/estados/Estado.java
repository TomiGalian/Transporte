package com.example.transporte.modelo.estados;

import android.net.Uri;

import com.example.transporte.modelo.Viaje;

import java.io.Serializable;

public interface Estado extends Serializable {

    boolean mandarUbicacion();

    String getEstado();

    Uri conducir(Viaje viaje);

    default boolean estaEnCamino(){
        return false;
    }

    default boolean estaTransportando(){
        return false;
    }

}
