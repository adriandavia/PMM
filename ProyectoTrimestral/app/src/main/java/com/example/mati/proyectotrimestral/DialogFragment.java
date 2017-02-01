package com.example.mati.proyectotrimestral;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class DialogFragment extends android.app.DialogFragment{


    public DialogFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_dialog, container, false);
        Bundle bundle = getArguments();
        String nombretarea = bundle.getString("nombre");
        String descripciontarea = bundle.getString("descripcion");
        TextView nombre = (TextView)view.findViewById(R.id.nombretarea);
        nombre.setText(nombretarea);
        TextView descripcion = (TextView)view.findViewById(R.id.descripcion);
        descripcion.setText(descripciontarea);

        Button atras = (Button)view.findViewById(R.id.atras);
        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        return view;
    }

}
