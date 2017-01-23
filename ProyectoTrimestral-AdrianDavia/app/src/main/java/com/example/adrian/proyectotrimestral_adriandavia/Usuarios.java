package com.example.adrian.proyectotrimestral_adriandavia;

public class Usuarios {
    public String nombreUsuario;
    public String apellidoUsuario;
    public String nickUsuario;
    public String passwordUsuario;
    public String emailUsuario;

    public Usuarios (String nombreUsuario, String apellidoUsuario,
                     String nickUsuario, String passwordUsuario,
                     String emailUsuario){
        this.nickUsuario = nombreUsuario;
        this.apellidoUsuario = apellidoUsuario;
        this.nickUsuario = nickUsuario;
        this.passwordUsuario = passwordUsuario;
        this.emailUsuario = emailUsuario;
    }
}
