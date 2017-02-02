package com.example.mati.proyectotrimestral;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class DialogFragment extends android.app.DialogFragment{
    private TareasDiariasActivity tareasDiarias = new TareasDiariasActivity();

    public DialogFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_dialog, container, false);
        Bundle bundle = getArguments();
        String nombretarea = bundle.getString("nombre");
        String descripciontarea = bundle.getString("descripcion");
        final Integer id = bundle.getInt("id");
        final String usuarios = bundle.getString("usuario");
        TextView nombre = (TextView)view.findViewById(R.id.nombretarea);
        nombre.setText(nombretarea);
        TextView descripcion = (TextView)view.findViewById(R.id.descripcion);
        descripcion.setText(descripciontarea);

        Date fecha = new Date();
        Integer dia = fecha.getDate();
        String day = dia.toString();
        Integer mes = fecha.getMonth()+1;
        String month = mes.toString();
        Integer año = fecha.getYear()+1900;
        String year = año.toString();
        final String fechaactual = day+"/"+month+"/"+year;

        final BdTareasSQLiteHelper bdHelper = new BdTareasSQLiteHelper(getActivity().getApplicationContext(),
                "dbTareas", null, 1);
        final SQLiteDatabase bd = bdHelper.getWritableDatabase();
        final OperacionesSQL operacionesSQL = new OperacionesSQL();

        Button atras = (Button)view.findViewById(R.id.atras);
        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        Button completar = (Button)view.findViewById(R.id.completar);
        completar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    operacionesSQL.update_tarea(bd, id);
                    Toast.makeText(getActivity().getApplicationContext(), "Tarea completada" +
                            "", Toast.LENGTH_SHORT).show();
                    Cursor cursor = operacionesSQL.select_tareas(bd, usuarios, fechaactual);
                    tareasDiarias.datos = new Tareas[cursor.getCount()];
                    int i = 0;
                    if (cursor.moveToFirst()) {
                        do {
                            String fechat = cursor.getString(1);
                            String nombre = cursor.getString(2);
                            String descripcion = cursor.getString(3);
                            tareasDiarias.datos[i] = new Tareas(nombre, fechat, descripcion, usuarios);
                            i++;
                        } while (cursor.moveToNext());
                    }
                    Activity context = getActivity();
                    DialogFragment.AdaptadorTareas adaptadorTareas = new DialogFragment.AdaptadorTareas(context);
                    ListView listView = (ListView)getActivity().findViewById(R.id.listatareas);
                    listView.setAdapter(adaptadorTareas);
                    dismiss();
                }catch (SQLiteException e){
                    Toast.makeText(getActivity().getApplicationContext(), "Ha ocurrido un error" +
                            "", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Button borrar = (Button)view.findViewById(R.id.borrar);
        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    operacionesSQL.delete_tarea(bd, id);
                    Toast.makeText(getActivity().getApplicationContext(), "Tarea borrada" +
                            "", Toast.LENGTH_SHORT).show();
                    Cursor cursor = operacionesSQL.select_tareas(bd, usuarios, fechaactual);
                    tareasDiarias.datos = new Tareas[cursor.getCount()];
                    int i = 0;
                    if (cursor.moveToFirst()) {
                        do {
                            String fechat = cursor.getString(1);
                            String nombre = cursor.getString(2);
                            String descripcion = cursor.getString(3);
                            tareasDiarias.datos[i] = new Tareas(nombre, fechat, descripcion, usuarios);
                            i++;
                        } while (cursor.moveToNext());
                    }
                    Activity context = getActivity();
                    DialogFragment.AdaptadorTareas adaptadorTareas = new DialogFragment.AdaptadorTareas(context);
                    ListView listView = (ListView)getActivity().findViewById(R.id.listatareas);
                    listView.setAdapter(adaptadorTareas);
                    dismiss();
                }catch (SQLiteException e){
                    Toast.makeText(getActivity().getApplicationContext(), "Ha ocurrido un error" +
                            "", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
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
}
