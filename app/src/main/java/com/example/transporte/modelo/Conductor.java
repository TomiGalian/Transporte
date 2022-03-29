package com.example.transporte.modelo;

import android.net.Uri;

import com.example.transporte.modelo.estados.Camino;
import com.example.transporte.modelo.estados.Estado;
import com.example.transporte.modelo.estados.Fuera;
import com.example.transporte.modelo.estados.Libre;
import com.example.transporte.modelo.estados.Transportando;

import java.io.Serializable;

public class Conductor implements Serializable {

    private Estado estado;

    private String usuario;

    private String contraseña;

    private Viaje viaje;

    private Ubicacion ubicacion;

    public Conductor(String usuario, String contraseña) {
        this.usuario=usuario;
        this.contraseña=contraseña;
        estado= new Fuera();
        viaje = null;

    }

    public boolean tieneViaje(){
        return viaje != null;
    }

    public void nuevoViaje(String nombre, String recogidaLat, String recogidaLong, String referenciaRecogida, String destinoLat, String destinoLong, String referenciaDestino){
        viaje = new Viaje(new Pasajero(nombre,new Ubicacion( recogidaLat, recogidaLong),new Ubicacion( destinoLat,destinoLong ),referenciaDestino,referenciaRecogida));
    }

    public void desconectar() {
        estado = new Fuera();
    }   //FUERA DE SERVICIO

    public void enCamino(){ estado = new Camino(); }

    public void transportando(){ estado = new Transportando(); }   //TIENE UN PASAJERO A BORDO DEL COCHE Y YENDO A DESTINO

    public void libre(){ estado = new Libre(); }       //DESPACHO AL ULTIMO PASAJERO  Y ESTA ESPERANDO UNO NUEVO

    public boolean mandarUbicacion(){
        return estado.mandarUbicacion();
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public Ubicacion getUbicacion(){    return ubicacion;    }

    public Viaje getViaje() {
        return viaje;
    }

    public Uri conducir() {
        return estado.conducir(viaje);
    }

    public boolean estaEnCamino(){ return estado.estaEnCamino(); }

    public boolean estaTransportando(){ return estado.estaTransportando(); }

}
