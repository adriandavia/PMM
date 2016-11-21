package com.example.mati.examenalquilercoches_adriandavia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

public class Factura extends AppCompatActivity {
    private boolean saca = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factura);

        TextView modelo = (TextView)findViewById(R.id.amodelo);
        ImageView imagen = (ImageView)findViewById(R.id.imagecoche);
        TextView phora = (TextView)findViewById(R.id.apreciohora);
        TextView extra = (TextView)findViewById(R.id.aextras);
        TextView tiempo = (TextView)findViewById(R.id.atiempo);
        TextView seguro = (TextView)findViewById(R.id.aseguro);
        TextView ctotal = (TextView)findViewById(R.id.acostetotal);
        Button vuelta = (Button)findViewById(R.id.vuelta);
        final CheckBox h = (CheckBox)findViewById(R.id.hroa);
        final TextClock reloj = (TextClock)findViewById(R.id.reloj);
        Bundle objeto = getIntent().getExtras();
        Coches coche = (Coches)objeto.getSerializable("coche");

        imagen.setBackground(getDrawable(coche.getImagen()));
        modelo.setText(coche.getModelo());
        phora.setText(String.valueOf(coche.getPrecio()) + "€");
        extra.setText(getIntent().getStringExtra("extras") + "€");
        tiempo.setText(getIntent().getStringExtra("tiempo") + "horas");
        if (getIntent().getBooleanExtra("seguro", false) == true){
            seguro.setText("Con Seguro");
        }else {
            seguro.setText("Sin Seguro");
        }
        ctotal.setText(getIntent().getStringExtra("total"));

        h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (h.isChecked()){
                    reloj.getTimeZone();
                    reloj.setVisibility(View.VISIBLE);
                }else{
                    reloj.setVisibility(View.INVISIBLE);
                }
            }
        });
        vuelta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent volver = new Intent(Factura.this, PantallaPrincipal.class);
                saca = true;
                volver.putExtra("sacar", saca);
                volver.putExtra("hora", reloj.getText().toString());
                isDestroyed();
                startActivity(volver);
            }
        });

    }
}
