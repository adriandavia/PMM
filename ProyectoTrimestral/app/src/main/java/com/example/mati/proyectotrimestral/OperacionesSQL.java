package com.example.mati.proyectotrimestral;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.widget.EditText;

public class OperacionesSQL {
    protected static void insert_usuarios(SQLiteDatabase sqLiteDatabase, Usuarios user){
            sqLiteDatabase.execSQL("INSERT INTO usuarios (name, surname, username, password, email) VALUES " +
                    "('"+user.getNombre()+"', '"+user.getApellidos()+"', '"+user.getUsername()+"', '"+user.getPassword()+"'" +
                    ", '"+user.getCorreo()+"')");
    }
}
