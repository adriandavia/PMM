package com.example.mati.ejemploejerciciorecopilatorio;

import java.io.Serializable;

public class destino implements Serializable{
    private  String zona;
    private  String continente;
    private  double precio;

    public destino (String zona, String continente, double precio){
        this.zona = zona;
        this.continente = continente;
        this.precio = precio;
    }

    public  String getzona (){
        return zona;
    }
    public  String getcontinente (){
        return continente;
    }
    public  double getPrecio (){
        return precio;
    }
}
