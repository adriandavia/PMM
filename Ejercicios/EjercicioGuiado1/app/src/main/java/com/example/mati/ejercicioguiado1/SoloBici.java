package com.example.mati.ejercicioguiado1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SoloBici extends AppCompatActivity {

    private Button bAcercaDe;
    private Button bJuego;
    private Button bPreferencias;
    private Button bSalir;
    private TextView lblMensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solo_bici);

        //Boton y escuchador para la pantalla "Juego"
        bJuego = (Button) findViewById(R.id.Boton01);
        bJuego.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                lanzarJuego();
            }
        });
        //Boton y escuchador para la pantalla "Acerca de"
        bAcercaDe = (Button) findViewById(R.id.Boton03);
        bAcercaDe.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                lanzarAcercaDe();
            }
        });

        //Boton y escuchador para la pantalla "Salir"
        bSalir = (Button) findViewById(R.id.Boton04);
        bSalir.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                lanzarSalir();
            }
        });

        bPreferencias = (Button) findViewById(R.id.Boton02);
        bPreferencias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lanzarPreferencias();
            }
        });

    }

    public boolean onCreateOptionsMenu (Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected (MenuItem item){
        switch (item.getItemId()){
            case R.id.activity_pantalla_opciones:
                Intent opciones = new Intent(SoloBici.this, PantallaOpciones.class);
                startActivity(opciones);
                return true;
            case R.id.fragment:
                Intent fragment = new Intent(SoloBici.this, SettingsFragment.class);
                startActivity(fragment);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void lanzarPreferencias(){
        Intent i = new Intent(this, Preferencias.class);
        startActivity(i);
    }

    public void lanzarJuego(){
        Intent i = new Intent(this, Juego.class);
        startActivity(i);
    }

    //Metodo que activa la pantalla AcercaDe
    public void lanzarAcercaDe(){
        Intent i = new Intent(this, Acercade.class);
        startActivity(i);
    }


    //Metodo que activa la pantalla AcercaDe
    public void lanzarSalir(){
        finish();
    }

}

