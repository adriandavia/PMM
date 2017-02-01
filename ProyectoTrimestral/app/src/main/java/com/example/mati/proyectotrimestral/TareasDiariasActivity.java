package com.example.mati.proyectotrimestral;

import android.app.*;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.Serializable;
import java.util.Date;

import static com.example.mati.proyectotrimestral.R.id.añadir;

public class TareasDiariasActivity extends AppCompatActivity implements ATareas.OnFragmentInteractionListener{
    protected Tareas[] datos;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tareas_diarias);


        //Titulo de la pantalla
        getSupportActionBar().setTitle("Tareas");

        Bundle bundle = getIntent().getExtras();
        Usuarios us = (Usuarios)bundle.getSerializable("usuario");
        final Usuarios pasarusu = new Usuarios(us.getNombre(), us.getApellidos(), us.getUsername(), us.getPassword(), us.getCorreo());
        final String usu = us.getUsername();

        //Creamos la base de datos
        final BdTareasSQLiteHelper bdHelper = new BdTareasSQLiteHelper(this, "dbTareas", null, 1);

        //Abrimos la base de datos en modo escritura
        final SQLiteDatabase bd = bdHelper.getReadableDatabase();

        //Objeto de la case operacion para poder usar sus metodos
        final OperacionesSQL operacionesSQL = new OperacionesSQL();

        //Obtenemos la fecha actual
        Date fecha = new Date();
        Integer dia = fecha.getDate();
        String day = dia.toString();
        Integer mes = fecha.getMonth()+1;
        String month = mes.toString();
        Integer año = fecha.getYear()+1900;
        String year = año.toString();
        final String fechaactual = day+"/"+month+"/"+year;

        //Creamos array de tareas
        Cursor cursor = operacionesSQL.select_tareas(bd, us.getUsername(), fechaactual);
        datos = new Tareas[cursor.getCount()];
        int i = 0;
        if (cursor.moveToFirst()) {
            do {
                String fechat = cursor.getString(0);
                String nombre = cursor.getString(1);
                String descripcion = cursor.getString(2);
                datos[i] = new Tareas(nombre, fechat, descripcion, us.getUsername());
                i++;
            } while (cursor.moveToNext());
        }

        //Llamamos a adaptador
        AdaptadorTareas adaptador = new AdaptadorTareas(this);
        ListView tareas = (ListView) findViewById(R.id.listatareas);
        tareas.setAdapter(adaptador);

        //Metemos en un textview la fecha y el usuario
        TextView u = (TextView)findViewById(R.id.nombredusuario);
        u.setText(u.getText() + " " + usu);
        TextView f = (TextView)findViewById(R.id.f);
        f.setText(f.getText() + " " + fechaactual);

        //obtenemos la instacion del administrador de fragmentos
        final FragmentManager fragmentManager = getFragmentManager();
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
        final DialogFragment dialogFragment = new DialogFragment();
        tareas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle paso = new Bundle();
                paso.putString("nombre", datos[i].getNombre());
                paso.putString("descripcion", datos[i].getDescripcion());
                dialogFragment.setArguments(paso);
                dialogFragment.show(fragmentManager, "Tarea seleccionada");
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

    //Rellenar Lista
    public class AdaptadorTareas extends ArrayAdapter {
        Activity context;

        AdaptadorTareas(Activity context){
            super(context, R.layout.list_item, datos);
            this.context = context;
        }

        public View getView(int i, View convertView, ViewGroup parent){
            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater.inflate(R.layout.list_item, null);

            TextView lblTitulo = (TextView) item.findViewById(R.id.titulotarea);
            lblTitulo.setText(datos[i].getNombre());

            return (item);
        }
    }
}
