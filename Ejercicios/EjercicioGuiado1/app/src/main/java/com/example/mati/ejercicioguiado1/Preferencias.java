package com.example.mati.ejercicioguiado1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Preferencias extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferencias);


        Button boton1 = (Button) findViewById(R.id.buttonprefe);
        Button boton2 = (Button) findViewById(R.id.buttonconsultar);

        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Preferencias.this, PantallaOpciones.class);
                startActivity(intent1);
            }
        });

        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(Preferencias.this);
                String preferen1 = "desactivada";
                if(pref.getBoolean("opcion1",false))
                    preferen1="activada";
                Toast.makeText(getApplicationContext(),preferen1,Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(),pref.getString("opcion2",""),Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(),pref.getString("opcion3",""),Toast.LENGTH_SHORT).show();
            }
        });



    }
}
