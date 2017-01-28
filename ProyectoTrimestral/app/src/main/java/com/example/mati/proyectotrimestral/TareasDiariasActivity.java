package com.example.mati.proyectotrimestral;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.Serializable;
import java.util.Date;

import static com.example.mati.proyectotrimestral.R.id.añadir;

public class TareasDiariasActivity extends AppCompatActivity implements ATareas.OnFragmentInteractionListener{

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tareas_diarias);


        //Titulo de la pantalla
        getSupportActionBar().setTitle("Tareas");

        //Obtenemos el objecto Usuarios

        Bundle bundle = getIntent().getExtras();
        Usuarios us = (Usuarios)bundle.getSerializable("usuario");
        final Usuarios pasarusu = new Usuarios(us.getNombre(), us.getApellidos(), us.getUsername(), us.getPassword(), us.getCorreo());
        final String usu = us.getUsername();

        //Obtenemos la fecha actual
        Date fecha = new Date();
        Integer dia = fecha.getDate();
        String day = dia.toString();
        Integer mes = fecha.getMonth()+1;
        String month = mes.toString();
        Integer año = fecha.getYear()+1900;
        String year = año.toString();
        final String fechaactual = day+"/"+month+"/"+year;
        //Metemos en un textview la fecha y el usuario
        TextView u = (TextView)findViewById(R.id.nombredusuario);
        u.setText("Usuario: " + usu + "| Fecha: " +fechaactual);

        Button añadir = (Button)findViewById(R.id.añadir);
        añadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle paso = new Bundle();
                paso.putSerializable("usuario", pasarusu);
                paso.putString("fechaactual", fechaactual);

                //Hacemos invisible el segundo layout para poder ver solamente el fragment
                RelativeLayout layout = (RelativeLayout)findViewById(R.id.invisibilidad);
                layout.setVisibility(View.INVISIBLE);

                //obtenemos la instacion del administrador de fragmentos
                FragmentManager fragmentManager = getFragmentManager();
                //creamos la transaccion
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                //creamos el objeto dle fragment
                ATareas fragmento = new ATareas();
                //añadimos argumentos
                fragmento.setArguments(paso);
                //añadimos la transaccion
                transaction.add(R.id.tareasdiarias, fragmento);
                //confirmamos el cambio
                transaction.commit();
            }
        });
    }
    @Override
    public void onFragmentInteraction(Uri uri) {
    }

    //menu
    public boolean onCreateOptionsMenu (Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_menu_secundario, menu);
        return true;
    }
    public boolean onOptionsItemSelected (MenuItem item){
        switch (item.getItemId()){
            case R.id.acerca:
                Intent acerca = new Intent(this, AcercaDeActivity.class);
                startActivity(acerca);
                return true;
            case R.id.salir:
                Intent salir = new Intent(this, LogginActivity.class);
                startActivity(salir);
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
