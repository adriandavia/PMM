package com.example.adrian.objetosentrepantallas;

import java.io.Serializable;

public class Peronsa implements Serializable{
    private String nombre, apellidos;
    private int edad;

    public Peronsa (String nombre, String apellidos, int edad){
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
    }

    public String getNombre(){
        return this.nombre;
    }
    public String getApellidos(){
        return this.apellidos;
    }
    public int getEdad(){
        return this.edad;
    }
}
