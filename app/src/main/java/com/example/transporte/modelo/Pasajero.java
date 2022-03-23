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
        /**
         *  Llamar al pup-up
         *  Se puede hacer de varias formas:
         *          - Suponiendo que la funcion esta en esta clase(fuera de la actuivity)
         *                  - pasarle el context a esta clase en el oncreate del main
         *                  - Esto: https://tinyurl.com/OpcionSeba (no me gusta mucho pero seria eficiente)
         *                      AlertDialog alertDialog = new AlertDialog().getWindow().setType();
         *                      alertDialog.show();
         *                  - Sea una notificacion, no se como hacerlo pero bueno
         *          - En una clase nueva utils https://tinyurl.com/OpcionPro
         *          - Encontrar una forma de que esta clase llame a una funcion de la Main (+100 de programacion cabeza)
         */
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
