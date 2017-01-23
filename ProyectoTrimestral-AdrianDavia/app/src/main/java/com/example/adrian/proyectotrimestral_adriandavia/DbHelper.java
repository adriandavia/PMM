package com.example.adrian.proyectotrimestral_adriandavia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    String usuarios= "create table usuarios (name text, surname text, usuario text, contraseña text, email text)";

    public DbHelper(Context contexto, String nombre, SQLiteDatabase.CursorFactory almacen, int version){
        super(contexto, nombre, almacen, version);
    }

    @Override
    public void onCreate(SQLiteDatabase bd) {
        bd.execSQL(usuarios);
    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int versionAnterior, int versionNueva) {
        bd.execSQL("drop table if exist usuarios");
        bd.execSQL(usuarios);
    }


}