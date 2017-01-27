package com.example.mati.proyectotrimestral;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;

import static com.example.mati.proyectotrimestral.R.id.añadir;

public class TareasDiariasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tareas_diarias);

        //Titulo de la pantalla
        getSupportActionBar().setTitle("Tareas");

        //Obtenemos el objecto Usuarios
        
        Bundle bundle = getIntent().getExtras();
        Usuarios us = (Usuarios)bundle.getSerializable("usuario");
        final Usuarios pasarusu = new Usuarios(us.getNombre(), us.getApellidos(), us.getUsername(), us.getPassword(), us.getCorreo());
        final String usu = us.getUsername();

        TextView u = (TextView)findViewById(R.id.nombredusuario);
        u.setText("Usuario: " + usu);
        Button añadir = (Button)findViewById(R.id.añadir);
        añadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TareasDiariasActivity.this, ATareasActivity.class);
                Bundle paso = new Bundle();
                paso.putSerializable("usua", pasarusu);
                intent.putExtras(paso);
                startActivity(intent);
            }
        });
    }
    //menu
    public boolean onCreateOptionsMenu (Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_menu_secundario, menu);
        return true;
    }
    public boolean onOptionsItemSelected (MenuItem item){
        switch (item.getItemId()){
            case R.id.acerca:
                Intent acerca = new Intent(this, AcercaDeActivity.class);
                startActivity(acerca);
                return true;
            case R.id.salir:
                Intent salir = new Intent(this, LogginActivity.class);
                startActivity(salir);
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
