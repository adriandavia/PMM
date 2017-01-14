package com.example.mati.examenalquilercoches_adriandavia;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class PantallaPrincipal extends AppCompatActivity {
    private Coches[] datos = new Coches[]{
            new Coches("Megane", "Seat", 20, R.drawable.megan3),
            new Coches("X-11", "Ferrari", 100, R.drawable.ferrari1),
            new Coches("Leon", "Seat", 30, R.drawable.leon1),
            new Coches("Fiesta", "Ford", 40, R.drawable.fiesta1),
    };

    private float extras = 0;
    private EditText horas;
    private boolean seguro = false;
    private float total = 0;

    //PARA EL CALLBACK
    public static int COD_RESPUESTA = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_principal);

        AdaptadorZonas adaptador = new AdaptadorZonas(this);
        final Spinner zonas = (Spinner) findViewById(R.id.coches);
        zonas.setAdapter(adaptador);

        Button totalp = (Button) findViewById(R.id.total);
        Button factura = (Button) findViewById(R.id.factura);
        final CheckBox aire = (CheckBox) findViewById(R.id.aire);
        final CheckBox gps = (CheckBox) findViewById(R.id.gps);
        final CheckBox radio = (CheckBox) findViewById(R.id.radiodvd);
        horas = (EditText) findViewById(R.id.horas);
        final Spinner coche = (Spinner) findViewById(R.id.coches);
        final TextView totalprecio = (TextView) findViewById(R.id.ptotal);
        final RadioGroup rg = (RadioGroup) findViewById(R.id.seguros);

        totalp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (horas.getText().toString().isEmpty()) {
                    horas.setText("0");
                }
                total = Float.parseFloat(horas.getText().toString()) * datos[coche.getSelectedItemPosition()].getPrecio();

                float a = 0, g = 0, r = 0;
                if (aire.isChecked()) {
                    total = total + 50;
                    a = 50;
                }
                if (gps.isChecked()) {
                    total = total + 50;
                    g = 50;
                }
                if (radio.isChecked()) {
                    total = total + 50;
                    r = 50;
                }
                extras = a + g + r;
                float tarifa = 0;
                if (rg.getCheckedRadioButtonId() == R.id.segurotodo) {
                    tarifa = (total * 20) / 100;
                    seguro = true;
                }
                if (rg.getCheckedRadioButtonId() == R.id.noseguro) {
                    tarifa = 0;
                }
                total = tarifa + total;

                totalprecio.setText(String.valueOf(total) + "€");
            }
        });

        factura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent paso = new Intent(PantallaPrincipal.this, Factura.class);
                Bundle pasoobjetos = new Bundle();

                Coches c = new Coches(datos[coche.getSelectedItemPosition()].getModelo(),
                        datos[coche.getSelectedItemPosition()].getMarca(), datos[coche.getSelectedItemPosition()].getPrecio(),
                        datos[coche.getSelectedItemPosition()].getImagen());
                pasoobjetos.putSerializable("coche", c);
                pasoobjetos.putString("extras", String.valueOf(extras));
                pasoobjetos.putString("tiempo", horas.getText().toString());
                pasoobjetos.putBoolean("seguro", seguro);
                pasoobjetos.putString("total", totalprecio.getText().toString());
                paso.putExtras(pasoobjetos);
                //Sin esto no funciona el callback
                startActivityForResult(paso, COD_RESPUESTA);
            }
        });
    }
    //Callback
    public void onActivityResult (int cod_res, int code_result, Intent intent){
        if (code_result == RESULT_OK){
            Bundle objeto = intent.getExtras();
            Toast.makeText(getApplicationContext(), objeto.getString("hora"), Toast.LENGTH_LONG).show();
        }
    }

    public boolean onCreateOptionsMenu (Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected (MenuItem item){
        switch (item.getItemId()){
            case R.id.acerca:
                Intent acerca = new Intent(PantallaPrincipal.this, Acercade.class);
                startActivity(acerca);
                return true;
            case R.id.dibujar:
                Intent dibujo = new Intent(PantallaPrincipal.this, Dibujar.class);
                startActivity(dibujo);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public class AdaptadorZonas extends ArrayAdapter<Coches> {
        Activity context;

        AdaptadorZonas(Activity context){
            super(context, R.layout.listitem_coches, datos);
            this.context = context;
        }
        public View getDropDownView (int position, View convertView, ViewGroup parent){
            View vistaDesplegada = getView(position, convertView, parent);
            return  vistaDesplegada;

        }

        public View getView(int i, View convertView, ViewGroup parent){
            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater.inflate(R.layout.listitem_coches, null);

            TextView lblTitulo = (TextView) item.findViewById(R.id.Lblmodelo);
            lblTitulo.setText(datos[i].getModelo());

            TextView lblSubtitulo = (TextView) item.findViewById(R.id.Lblmarca);
            lblSubtitulo.setText(datos[i].getMarca());

            TextView lblPrecio = (TextView)item.findViewById(R.id.Lblprecio);
            lblPrecio.setText(String.valueOf(datos[i].getPrecio()) + "€");

            ImageView lblImagen = (ImageView)item.findViewById(R.id.cimagen);
            lblImagen.setBackground(getDrawable(datos[i].getImagen()));
            return (item);
        }

    }
}
