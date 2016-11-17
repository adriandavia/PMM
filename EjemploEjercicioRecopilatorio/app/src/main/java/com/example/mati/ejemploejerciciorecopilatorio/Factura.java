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

        TextView zona = (TextView)findViewById(R.id.zona);
        TextView preciozona = (TextView)findViewById(R.id.preciozona);
        TextView tarifa = (TextView)findViewById(R.id.tarifa);
        TextView complemento = (TextView)findViewById(R.id.complemento1);
        TextView complemento2 = (TextView)findViewById(R.id.complemento2);
        TextView peso = (TextView)findViewById(R.id.peso);
        TextView preciopeso = (TextView)findViewById(R.id.preciopeso);


        Bundle objeto = getIntent().getExtras();
        destino Destino = (destino)objeto.getSerializable("destino");

        zona.setText(Destino.getcontinente());

        preciozona.setText(String.valueOf(Destino.getPrecio())+"€");

        if (getIntent().getStringExtra("nombretarifa").equalsIgnoreCase("TARIFA URGENTE")){
            tarifa.setText(getIntent().getStringExtra("nombretarifa") + " (+30%)");
        }else {
            tarifa.setText(getIntent().getStringExtra("nombretarifa"));
        }

        if (getIntent().getBooleanExtra("checked", false) == true){
           complemento.setText(getIntent().getStringExtra("tarjeta"));
        }
        if (getIntent().getBooleanExtra("checked", false) == true) {
           complemento2.setText(getIntent().getStringExtra("cajaregalo"));
        }

        peso.setText(getIntent().getStringExtra("peso") + "kg");
        preciopeso.setText(getIntent().getStringExtra("preciopeso") + "€");
    }
}
