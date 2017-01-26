package com.example.mati.proyectotrimestral;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.icu.util.Calendar;
import android.icu.util.GregorianCalendar;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OperacionesSQL {
    protected static void insert_usuarios(SQLiteDatabase sqLiteDatabase, Usuarios user){
            sqLiteDatabase.execSQL("INSERT INTO usuarios (name, surname, username, password, email) VALUES " +
                    "('"+user.getNombre()+"', '"+user.getApellidos()+"', '"+user.getUsername()+"', '"+user.getPassword()+"'" +
                    ", '"+user.getCorreo()+"')");
    }
    protected static Cursor select_usuarios(SQLiteDatabase sqLiteDatabase){
        return sqLiteDatabase.rawQuery("SELECT * from usuarios;", null);
    }
    protected static void insert_tareas(SQLiteDatabase sqLiteDatabase, Tareas tareas){
        sqLiteDatabase.execSQL("INSERT INTO tareas (date_task, name_task, description, username) VALUES " +
                "('"+tareas.getFecha()+", '"+tareas.getNombre()+" ,'"+tareas.getDescripcion()+", " +
                "'"+tareas.getUsername()+"')");
    }
    protected static Cursor select_tareas(SQLiteDatabase sqLiteDatabase, Usuarios username){
        //Para sacar la fecha acutal
        Date date = new Date();
        String hora = (date.getDate())+"/"+(date.getMonth()+1)+"/"+(date.getYear()+1900);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaactual = null;
        try{
            fechaactual = dateFormat.parse(hora);
        }catch (ParseException e){
        }
        return sqLiteDatabase.rawQuery("SELECT name_task, date_task, description FROM tareas where username = " +
                "'"+username.getUsername()+"' and state = false and date_task = "+fechaactual+";", null);
    }
}
