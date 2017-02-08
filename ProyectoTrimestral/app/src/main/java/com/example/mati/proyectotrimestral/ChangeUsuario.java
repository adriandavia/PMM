package com.example.mati.proyectotrimestral;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ChangeUsuario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_usuario);
        //Creamos la base de datos
        final BdTareasSQLiteHelper bdHelper = new BdTareasSQLiteHelper(this, "dbTareas", null, 1);

        //Abrimos la base de datos en modo escritura
        final SQLiteDatabase bd = bdHelper.getWritableDatabase();

        //Objeto de la case operacion para poder usar sus metodos
        final OperacionesSQL operacionesSQL = new OperacionesSQL();

        Bundle bundle = getIntent().getExtras();
        String user = bundle.getString("usuario");

        Cursor cursor = operacionesSQL.select_usuarios(bd);
        final Usuarios[] u = new Usuarios[cursor.getCount()];
        if (cursor.moveToFirst()) {
            do {
                String nombre = cursor.getString(0);
                String apellidos = cursor.getString(1);
                String username = cursor.getString(2);
                String password = cursor.getString(3);
                String email = cursor.getString(4);
                u[0] = new Usuarios(nombre, apellidos, username, password, email);
            } while (cursor.moveToNext());
        }
        TextView nombre = (TextView)findViewById(R.id.nombre);
        nombre.setText("Nombre y apellidos: " + u[0].getNombre() + " " + u[0].getApellidos());
        TextView tuser = (TextView)findViewById(R.id.username);
        tuser.setText("Nombre de usuario: " + u[0].getUsername());
        TextView temail = (TextView)findViewById(R.id.email);
        temail.setText("E-mail: " +u[0].getCorreo());

        Button button = (Button)findViewById(R.id.atras);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button borrar = (Button)findViewById(R.id.borrar);
        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operacionesSQL.delete_usuario(bd, u[0].getUsername());
                Intent intent = new Intent(ChangeUsuario.this, LogginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
