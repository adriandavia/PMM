package com.example.mati.proyectotrimestral;

import java.io.Serializable;

public class Usuarios implements Serializable{
    private String nombre;
    private String apellidos;
    private String username;
    private String password;
    private String correo;

    public Usuarios(String nombre, String apellidos, String username, String password, String correo){
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.username = username;
        this.password = password;
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }
    public String getApellidos() {
        return apellidos;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getCorreo() {
        return correo;
    }
}
