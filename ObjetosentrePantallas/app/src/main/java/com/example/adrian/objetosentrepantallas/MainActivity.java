package com.example.adrian.objetosentrepantallas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button guardar = (Button) findViewById(R.id.guardar);
        final EditText nombre = (EditText) findViewById(R.id.nombre);
        final EditText apellidos = (EditText) findViewById(R.id.apellidos);
        final EditText edad = (EditText) findViewById(R.id.edad);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pasar = new Intent(MainActivity.this, Main2Activity.class);
                Bundle objetos = new Bundle();

                Peronsa persona = new Peronsa(nombre.getText().toString(),
                        apellidos.getText().toString(), Integer.parseInt(edad.getText().toString()));

                objetos.putSerializable("persona", persona);
                pasar.putExtras(objetos);

                startActivity(pasar);
            }
        });
    }
}
