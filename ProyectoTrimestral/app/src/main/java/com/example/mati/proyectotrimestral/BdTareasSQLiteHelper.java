package com.example.mati.proyectotrimestral;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class BdTareasSQLiteHelper extends SQLiteOpenHelper {
    //CREAR TABLAS NADA MAS CREAR LA BASE DE DATOS, SI NO, NO INSERTARA MAS TABLAS
    public BdTareasSQLiteHelper(Context context, String nombre, CursorFactory almacen, int version){
        super (context, nombre, almacen, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE usuarios (" +
                "name text not null," +
                "surname text not null," +
                "username text primary key," +
                "password text not null," +
                "email text," +
                "UNIQUE (username, email))");
        sqLiteDatabase.execSQL("CREATE TABLE tareas (" +
                "id_task integer primary key AUTOINCREMENT," +
                "date_task text not null," +
                "name_task text not null," +
                "description text," +
                "state boolean DEFAULT false," +
                "username text not null," +
                " FOREIGN KEY (username) REFERENCES usuarios(username) ON DELETE CASCADE)" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS usuarios");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tareas");
        onCreate(sqLiteDatabase);
    }
}
