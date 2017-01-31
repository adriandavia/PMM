package com.example.mati.proyectotrimestral;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ParseException;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.RequiresPermission;
import android.app.Fragment;
import android.support.v4.content.pm.ActivityInfoCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ATareas extends Fragment {
    private OnFragmentInteractionListener mListener;
    Activity activity;
    public ATareas() {
    }
    private TareasDiariasActivity tareasDiarias = new TareasDiariasActivity();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_atareas, container, false);

        final RelativeLayout layout = (RelativeLayout)view.findViewById(R.id.tareasdiarias);
        final BdTareasSQLiteHelper bdHelper = new BdTareasSQLiteHelper(getActivity().getApplicationContext()
                , "dbTareas", null, 1);
        final SQLiteDatabase sqLiteDatabase = bdHelper.getWritableDatabase();
        final OperacionesSQL operacionesSQL = new OperacionesSQL();

        Button añadir = (Button)view.findViewById(R.id.añadirt);
        Button volver = (Button)view.findViewById(R.id.atras);

        final Bundle objetos = getArguments();
        final EditText nombre = (EditText) view.findViewById(R.id.ntarea);
        final EditText ftarea = (EditText) view.findViewById(R.id.ftarea);
        final EditText descripcion = (EditText) view.findViewById(R.id.dtarea);
        final Usuarios usuarios = (Usuarios) objetos.getSerializable("usuario");
        final String fechaactual = objetos.getString("fechaactual");

        añadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fecha = ftarea.getText().toString();
                Tareas tareas = new Tareas(fecha, nombre.getText().toString(), descripcion.getText().toString(), usuarios.getUsername());
                try {
                    operacionesSQL.insert_tareas(sqLiteDatabase, tareas);
                    Toast.makeText(getActivity().getApplicationContext(), "Tarea añadida correctamente" +
                            "", Toast.LENGTH_SHORT).show();
                    nombre.setText("");
                    ftarea.setText("");
                    descripcion.setText("");
                } catch (Exception e) {
                    Toast.makeText(getActivity().getApplicationContext(), "Error al introducir la tarea" +
                            "", Toast.LENGTH_SHORT).show();
                    nombre.setText("");
                    ftarea.setText("");
                    descripcion.setText("");
                }
            }
        });

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //lamamos al listView de la pantalla principal para volver a rellenarlo con campos nuevos si hay
                Cursor cursor = operacionesSQL.select_tareas(sqLiteDatabase, usuarios.getUsername(), fechaactual);
                tareasDiarias.datos = new Tareas[cursor.getCount()];
                int i = 0;
                if (cursor.moveToFirst()) {
                    do {
                        String fechat = cursor.getString(0);
                        String nombre = cursor.getString(1);
                        String descripcion = cursor.getString(2);
                        tareasDiarias.datos[i] = new Tareas(nombre, fechat, descripcion, usuarios.getUsername());
                        i++;
                    } while (cursor.moveToNext());
                }
                Activity context = getActivity();
                AdaptadorTareas adaptadorTareas = new AdaptadorTareas(context);
                ListView listView = (ListView)getActivity().findViewById(R.id.listatareas);
                listView.setAdapter(adaptadorTareas);

                //Hacemos visible el layou principal e invisible el fragment
                layout.setVisibility(View.INVISIBLE);
                View tareasDiariasActivity = getActivity().getWindow().findViewById(R.id.invisibilidad);
                tareasDiariasActivity.setVisibility(View.VISIBLE);


            }
        });

        return  view;
    }

    public class AdaptadorTareas extends ArrayAdapter {
        Activity context;

        AdaptadorTareas(Activity context){
            super(context, R.layout.list_item, tareasDiarias.datos);
            this.context = context;
        }

        public View getView(int i, View convertView, ViewGroup parent){
            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater.inflate(R.layout.list_item, null);

            TextView lblTitulo = (TextView) item.findViewById(R.id.titulotarea);
            lblTitulo.setText(tareasDiarias.datos[i].getNombre());

            return (item);
        }
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
