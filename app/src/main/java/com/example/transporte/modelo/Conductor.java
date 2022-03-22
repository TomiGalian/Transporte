package com.example.transporte.modelo;

import com.example.transporte.modelo.estados.Camino;
import com.example.transporte.modelo.estados.Estado;
import com.example.transporte.modelo.estados.Fuera;
import com.example.transporte.modelo.estados.Libre;
import com.example.transporte.modelo.estados.Transportando;

public class Conductor {

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
        if(viaje == null)
            return false;
        return true;
    }

    public void nuevoViaje(String nombre, Ubicacion recogida, String referenciaRecogida, Ubicacion destino, String referenciaDestino){
        viaje = new Viaje(new Pasajero(nombre,recogida,destino,referenciaDestino,referenciaRecogida));
    }

    public Conductor(){
        estado = new Fuera();
    }

    public void desconectar() {
        estado = new Fuera();
    }   //FUERA DE SERVICIO

    public void enCamino(){
        estado = new Camino();
    }       //Esta yendo a buscar al pasajero

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
}
