package com.example.mati.proyectotrimestral;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ATareasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atareas);

        getSupportActionBar().setTitle("Añadir tareas");

        final BdTareasSQLiteHelper bdHelper = new BdTareasSQLiteHelper(this, "dbTareas", null, 1);

        final OperacionesSQL operacionesSQL = new OperacionesSQL();

        EditText nombre = (EditText)findViewById(R.id.ntarea);
        EditText fecha = (EditText)findViewById(R.id.ftarea);
        EditText descripcion = (EditText)findViewById(R.id.dtarea);

        String name = nombre.getText().toString();
        String dat = fecha.getText().toString();
        String descrp = descripcion.getText().toString();
        Bundle bundle = getIntent().getExtras();
        Usuarios user = (Usuarios)bundle.getSerializable("user");

        //Pasar de string a date
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date fechatask = null;
        try{
            fechatask = dateFormat.parse(dat);
        }catch (ParseException e){
        }


        final Tareas tareas = new Tareas(fechatask, name, descrp, user.getUsername());
        Button añadir = (Button)findViewById(R.id.añadir);
        añadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final SQLiteDatabase bd = bdHelper.getReadableDatabase();
                try{
                    operacionesSQL.insert_tareas(bd, tareas);
                }catch (SQLiteException e){
                    Context context = getApplicationContext();
                    Toast.makeText(context, "Formate de contraseña incorrecto prueba con dd/MM/yyyy", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
