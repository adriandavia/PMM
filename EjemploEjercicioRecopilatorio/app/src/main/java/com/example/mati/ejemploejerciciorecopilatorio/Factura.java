package com.example.mati.ejemploejerciciorecopilatorio;

import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Factura extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factura);

        TextView zona = (TextView)findViewById(R.id.zonaa);
        TextView preciozona = (TextView)findViewById(R.id.preciozonaa);
        TextView tarifa = (TextView)findViewById(R.id.tarifaa);

        Bundle objeto = getIntent().getExtras();
        destino Destino = (destino)objeto.getSerializable("destino");

        zona.setText(Destino.getcontinente());
        preciozona.setText(String.valueOf(Destino.getPrecio()));
        tarifa.setText(getIntent().getStringExtra("nombretarifa"));

    }
}
