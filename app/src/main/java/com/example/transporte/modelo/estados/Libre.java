package com.example.transporte.modelo.estados;

public class Libre implements Estado {

    @Override
    public boolean mandarUbicacion() {
        return true;
    }

    @Override
    public String getEstado() {
        return "Libre";
    }
}
