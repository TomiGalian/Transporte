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

    public void desconectar(){
        estado = new Fuera();
    }

    public void enCamino(){
        estado = new Camino();
    }

    public void transportando(){
        estado = new Transportando();
    }

    public void libre(){
        estado = new Libre();
    }

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
