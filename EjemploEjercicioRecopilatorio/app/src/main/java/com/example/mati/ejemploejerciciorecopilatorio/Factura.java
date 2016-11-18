package com.example.mati.ejemploejerciciorecopilatorio;

import android.content.Intent;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.mati.ejemploejerciciorecopilatorio.R.menu.menu_ctx_imageview;

public class Factura extends AppCompatActivity {
    private TextView zona;
    private String zona1;
    private String zona2;
    private ImageView imagen;
    private destino Destino;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factura);

        zona = (TextView) findViewById(R.id.zona);
        imagen = (ImageView)findViewById(R.id.imagen);
        TextView preciozona = (TextView) findViewById(R.id.preciozona);
        TextView tarifa = (TextView) findViewById(R.id.tarifa);
        TextView complemento = (TextView) findViewById(R.id.complemento1);
        TextView complemento2 = (TextView) findViewById(R.id.complemento2);
        TextView peso = (TextView) findViewById(R.id.peso);
        TextView preciopeso = (TextView) findViewById(R.id.preciopeso);
        TextView total = (TextView) findViewById(R.id.total);
        Button recalcular = (Button) findViewById(R.id.recalcular);

        Bundle objeto = getIntent().getExtras();
        Destino = (destino) objeto.getSerializable("destino");
        if (Destino.getcontinente().equalsIgnoreCase("Europa")){
            zona.setText(Destino.getcontinente());
        }
        if (Destino.getcontinente().equalsIgnoreCase("América y África")){
            zona1 = "América";
            zona2 = "África";
        }
        if (Destino.getcontinente().equalsIgnoreCase("Asia y Oceanía")){
            zona1 = "Asia";
            zona2 = "Oceanía";
        }
        preciozona.setText(String.valueOf(Destino.getPrecio()) + "€");

        if (getIntent().getStringExtra("nombretarifa").equalsIgnoreCase("TARIFA URGENTE")) {
            tarifa.setText(getIntent().getStringExtra("nombretarifa") + " (+30%)");
        } else {
            tarifa.setText(getIntent().getStringExtra("nombretarifa"));
        }

        if (getIntent().getBooleanExtra("checked", false) == true) {
            complemento.setText(getIntent().getStringExtra("tarjeta"));
        }
        if (getIntent().getBooleanExtra("checked", false) == true) {
            complemento2.setText(getIntent().getStringExtra("cajaregalo"));
        }

        peso.setText(getIntent().getStringExtra("peso") + "kg");
        preciopeso.setText(getIntent().getStringExtra("preciopeso") + "€");

        total.setText(String.valueOf(Destino.getPrecio()) + "€ + " + getIntent().getStringExtra("preciopeso")
                + "€ + " + getIntent().getStringExtra("tarifa") + "€ = " + getIntent().getStringExtra("total") + "€");

        recalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent volver = new Intent(Factura.this, MainActivity.class);
                startActivity(volver);
            }
        });
        //Menu Contextual
        registerForContextMenu(imagen);
        imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.showContextMenu();
            }
        });
    }
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        menu.setHeaderTitle(Destino.getcontinente());
        inflater.inflate(menu_ctx_imageview, menu);
    }
    public boolean onContextItemSelected(MenuItem itemMnuContex) {
        switch (itemMnuContex.getItemId()) {
            case R.id.Pais1:
                if (Destino.getcontinente().equalsIgnoreCase("América y África")) {
                    zona.setText("América");
                    return true;
                }
                if (Destino.getcontinente().equalsIgnoreCase("Asia y Oceanía")){
                    zona.setText("Asia");
                    return true;
                }
            case R.id.Pais2:
                if (Destino.getcontinente().equalsIgnoreCase("América y África")) {
                    zona.setText("África");
                    return true;
                }
                if (Destino.getcontinente().equalsIgnoreCase("Asia y Oceanía")){
                    zona.setText("Oceanía");
                    return true;
                }
            default:
                return super.onContextItemSelected(itemMnuContex);
        }
    }
}
