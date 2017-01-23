package com.example.adrian.proyectotrimestral_adriandavia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.adrian.proyectotrimestral_adriandavia.BDTareas.BDUsuarios;
import com.example.adrian.proyectotrimestral_adriandavia.BDTareas.BDTarea;
import com.example.adrian.proyectotrimestral_adriandavia.BaseDatosTareas.Tablas;

public class Operaciones {
    private static BaseDatosTareas baseDatos;
    private static Operaciones instancia = new Operaciones();

    public Operaciones(){

    }

    public static Operaciones obtenerInstancia (Context context){
        if (baseDatos == null){
            baseDatos = new BaseDatosTareas(context);
        }
        return instancia;
    }

    public Cursor select_Usuarios(String usurname){
        SQLiteDatabase db = baseDatos.getReadableDatabase();

        String select_sql = String.format("select * from %s where %s=?",
            Tablas.USERS, BDTareas.BDUsuarios.user);

        String [] selection = {usurname};
        return db.rawQuery(select_sql, selection);
    }

    public String insert_Usuarios(Usuarios user){
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(BDUsuarios.name, user.nombreUsuario);
        valores.put(BDUsuarios.subname, user.apellidoUsuario);
        valores.put(BDUsuarios.user, user.nickUsuario);
        valores.put(BDUsuarios.password, user.passwordUsuario);
        valores.put(BDUsuarios.email, user.emailUsuario);

        db.insertOrThrow(Tablas.USERS, null, valores);

        return "Te has registrado correctamente";

    }

}
