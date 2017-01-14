package com.example.mati.ejercicioguiado1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Juego extends AppCompatActivity {

    private VistaJuego vistaJuego;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);

        vistaJuego = (VistaJuego)findViewById(R.id.VistaJuego);
    }

    @Override
    protected void onDestroy(){
        vistaJuego.getHilo().detener();
        super.onDestroy();
    }

    @Override
    protected void onPause(){
        super.onPause();
        vistaJuego.getHilo().pausar();
    }

    @Override
    protected void onResume(){
        super.onResume();
        vistaJuego.getHilo().reanudar();
    }

}
