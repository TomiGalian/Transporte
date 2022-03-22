package com.example.transporte.modelo.estados;

public class Fuera implements Estado{

    @Override
    public boolean mandarUbicacion() {
        return false;
    }

    @Override
    public String getEstado() {
        return "Fuera";
    }
}
