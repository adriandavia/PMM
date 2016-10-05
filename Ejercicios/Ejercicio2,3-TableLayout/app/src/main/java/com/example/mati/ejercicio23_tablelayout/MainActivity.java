package com.example.mati.ejercicio23_tablelayout;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button rojo = (Button)findViewById(R.id.rojo);
        final Button amarillo = (Button)findViewById(R.id.amarillo);
        final Button azul = (Button)findViewById(R.id.azul);
        final Button borrar = (Button)findViewById(R.id.borrar);
        final TextView textView = (TextView)findViewById(R.id.textView);
        //EJERCICIO 3 Layout
        final Context context = this;

        rojo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setBackgroundColor(ContextCompat.getColor(context, R.color.rojo));
            }
        });
        amarillo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setBackgroundColor(ContextCompat.getColor(context, R.color.amarillo));
            }
        });
        azul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setBackgroundColor(ContextCompat.getColor(context, R.color.azul));
            }
        });
        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setBackgroundColor(ContextCompat.getColor(context, R.color.blanco));
            }
        });
        //EJERCICIO 2 Layout
        /*rojo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int crojo = Color.parseColor("#ff0000");
                textView.setBackgroundColor(crojo);
            }
        });
        amarillo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int camarillo = Color.parseColor("#ffe100");
                textView.setBackgroundColor(camarillo);
            }
        });
        azul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cazul = Color.parseColor("#00bfff");
                textView.setBackgroundColor(cazul);
            }
        });
        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cborrar = Color.parseColor("#ffffff");
                textView.setBackgroundColor(cborrar);
            }
        });*/
    }
}
