package com.example.mati.proyectotrimestral;

import android.content.Context;
import android.content.Intent;
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

        Bundle bundle = getIntent().getExtras();
        Usuarios ousuario = (Usuarios)bundle.getSerializable("usua");
        final Usuarios usuario = new Usuarios(ousuario.getNombre(), ousuario.getApellidos(), ousuario.getUsername(),
                ousuario.getPassword(),ousuario.getCorreo());

        Button atras = (Button)findViewById(R.id.atras);
        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ATareasActivity.this, TareasDiariasActivity.class);
                Bundle pasar = new Bundle();
                pasar.putSerializable("u", usuario);
                startActivity(intent);
                finish();
            }
        });
    }
}
