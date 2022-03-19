package com.example.transporte.modelo;

import java.util.ArrayList;

public class Pasajero {

    private String nombre;

    private Ubicacion recogida;

    private String referenciaUbicacion;

    private String referenciaDestino;

    private Ubicacion destino;

    public void Pasajero(String nombre, Ubicacion recogida, Ubicacion destino, String referenciaDestino, String referenciaUbicacion){
        this.nombre = nombre;
        this.recogida = recogida;
        this.referenciaUbicacion = referenciaUbicacion;
        this.destino = destino;
        this.referenciaDestino = referenciaDestino;
        /**
         *  Llamar al pup-up
         */
    }

    public String getReferenciaUbicacion(){
        return referenciaUbicacion;
    }

    public String getReferenciaDestino(){
        return referenciaDestino;
    }

    public Ubicacion getRecogida(){
        return recogida;
    }

    public Ubicacion getDestino(){
        return destino;
    }



}
