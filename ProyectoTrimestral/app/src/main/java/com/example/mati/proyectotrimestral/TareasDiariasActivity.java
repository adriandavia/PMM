package com.example.mati.proyectotrimestral;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;

public class TareasDiariasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tareas_diarias);

        //Titulo de la pantalla
        getSupportActionBar().setTitle("Tareas");

        Button añadir = (Button)findViewById(R.id.añadir);
        añadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = getIntent().getExtras();
                Usuarios usuario = (Usuarios)bundle.getSerializable("usuario");
                String NUsuario = usuario.getUsername();
                Intent intent = new Intent(TareasDiariasActivity.this, ATareasActivity.class);
                intent.putExtra("user", NUsuario);
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
