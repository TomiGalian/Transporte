package com.example.transporte.modelo;

import android.app.AlertDialog;

import java.io.Serializable;
import java.util.ArrayList;

public class Pasajero implements Serializable {

    private String nombre;

    private Ubicacion recogida;

    private String referenciaOrigen;

    private String referenciaDestino;

    private Ubicacion destino;

    public Pasajero(String nombre, Ubicacion recogida, Ubicacion destino, String referenciaDestino, String referenciaUbicacion){
        this.nombre = nombre;
        this.recogida = recogida;
        this.referenciaOrigen = referenciaUbicacion;
        this.destino = destino;
        this.referenciaDestino = referenciaDestino;
    }

    public String getReferenciaOrigen(){
        return referenciaOrigen;
    }

    public String getReferenciaDestino(){
        return referenciaDestino;
    }

    public String getNombrePasajero(){
        return nombre;
    }

    public Ubicacion getRecogida(){
        return recogida;
    }

    public Ubicacion getDestino(){
        return destino;
    }



}
