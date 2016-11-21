package com.example.mati.examenalquilercoches_adriandavia;

import java.io.Serializable;

public class Coches implements Serializable{
    private String modelo;
    private String marca;
    private float precio;
    private int imagen;

    public Coches(String modelo, String marca, float precio, int imagen){
        this.modelo = modelo;
        this.marca = marca;
        this.precio = precio;
        this.imagen = imagen;
    }

    public String getModelo(){
        return modelo;
    }

    public String getMarca(){
        return marca;
    }

    public float getPrecio(){
        return precio;
    }

    public int getImagen (){
        return imagen;
    }
}
