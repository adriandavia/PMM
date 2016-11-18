package com.example.mati.ejemploejerciciorecopilatorio;

import android.content.Intent;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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
        TextView total = (TextView)findViewById(R.id.total);
        Button recalcular = (Button) findViewById(R.id.recalcular);

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

        total.setText(String.valueOf(Destino.getPrecio()) + "€ + " + getIntent().getStringExtra("preciopeso")
        + "€ + " + getIntent().getStringExtra("tarifa") +"€ = " + getIntent().getStringExtra("total") + "€");

        recalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent volver = new Intent(Factura.this, MainActivity.class);
                startActivity(volver);
            }
        });
    }
}
