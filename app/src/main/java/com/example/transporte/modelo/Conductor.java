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

    private Ubicacion ubicacion;

    public void Conductor(){
        estado = new Libre();
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


    public void funcion(){

    }


}
