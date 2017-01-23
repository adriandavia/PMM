package com.example.adrian.proyectotrimestral_adriandavia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.example.adrian.proyectotrimestral_adriandavia.BDTareas.BDUsuarios;
import com.example.adrian.proyectotrimestral_adriandavia.BDTareas.BDTarea;

public class BaseDatosTareas extends SQLiteOpenHelper{
    private static final String NAME_DATABASE = "dbtareas";
    private static final int NOW_VERSION = 1;
    private final Context context;

    interface Tablas{
        String USERS = "usuarios";
        String TASK = "tareas";
    }

    public BaseDatosTareas (Context context){
        super(context, NAME_DATABASE, null, NOW_VERSION);
        this.context = context;
    }

    public void onCreate (SQLiteDatabase db) {
        db.execSQL(String.format("create table %s (%s text not null, " +
                        "%s text primary key not null, %s text not null, %s text not null, " +
                        "%s text not null, %s text not null unique",
                Tablas.USERS, BaseColumns._ID,
                BDUsuarios.name,
                BDUsuarios.subname,
                BDUsuarios.user,
                BDUsuarios.password,
                BDUsuarios.email));

        db.execSQL(String.format("create table %s (%s int primary key autoincrement, %s date not null, %s text mot null" +
                        "%s text, %s text null",
                Tablas.TASK, BaseColumns._ID,
                BDTarea.id_task, BDTarea.name,
                BDTarea.description, BDTarea.date,
                BDTarea.state));
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("drop table if exists " + Tablas.USERS);
        db.execSQL("drop table if exists " + Tablas.TASK);
        onCreate(db);
    }
}
