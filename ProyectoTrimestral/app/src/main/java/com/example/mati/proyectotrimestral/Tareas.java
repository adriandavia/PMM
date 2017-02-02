package com.example.mati.proyectotrimestral;

import java.io.Serializable;
import java.util.Date;

public class Tareas implements Serializable{
    Integer id;
    String fecha;
    String nombre;
    String descripcion;
    String username;

    public Tareas (String fecha, String nombre, String descripcion, String username){
        this.fecha = fecha;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.username = username;
    }

    public Tareas (Integer id, String fecha, String nombre, String descripcion, String username){
        this.id = id;
        this.fecha = fecha;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.username = username;
    }

    public Integer getId() {return id;}
    public String getFecha() {
        return fecha;
    }
    public String getNombre() {
        return nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public String getUsername() {
        return username;
    }
}
