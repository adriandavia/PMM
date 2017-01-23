package com.example.adrian.proyectotrimestral_adriandavia;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Registro extends AppCompatActivity {
    public static DbHelper mDbHelper = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final DbHelper dbHelper = new DbHelper(this, "dbtareas", null, 1);

        setContentView(R.layout.activity_registro);
        final Button unirse = (Button)findViewById(R.id.runirse);
        unirse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                EditText nombre = (EditText)findViewById(R.id.rnombre);
                EditText apellidos = (EditText)findViewById(R.id.rapellidos);
                EditText usuario = (EditText)findViewById(R.id.rusuario);
                EditText contraseña = (EditText)findViewById(R.id.rcontraseña);
                EditText email = (EditText)findViewById(R.id.remail);

                String insert_bd = "insert into usuarios (name, surname, usuario, contraseña, email) values('"+ nombre.getText().toString() +
                        "', '" + apellidos.getText().toString() +"', '" + usuario.getText().toString() + "', '" + contraseña.getText().toString() +
                        "', '"+ email.getText().toString() +"');";

                db.execSQL(insert_bd);
                db.close();
            }
        });

        Button volver = (Button)findViewById(R.id.rvolver);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent volver = new Intent(Registro.this, Loging.class);
                startActivity(volver);
                finish();
            }
        });
    }
    //menu
    public boolean onCreateOptionsMenu (Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected (MenuItem item){
        switch (item.getItemId()){
            case R.id.acerca:
                Intent acerca = new Intent(this, AcercaDe.class);
                startActivity(acerca);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
