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
import android.widget.TextView;

import com.example.adrian.proyectotrimestral_adriandavia.Operaciones;
public class Registro extends AppCompatActivity {
    private final Operaciones op = new Operaciones();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_registro);
        final Button unirse = (Button)findViewById(R.id.runirse);
        unirse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText nombre = (EditText)findViewById(R.id.rnombre);
                EditText apellidos = (EditText)findViewById(R.id.rapellidos);
                EditText usuario = (EditText)findViewById(R.id.rusuario);
                EditText contraseña = (EditText)findViewById(R.id.rcontraseña);
                EditText email = (EditText)findViewById(R.id.remail);

                TextView mensaje = (TextView)findViewById(R.id.mensajeerror);
                Usuarios user = new Usuarios(nombre.getText().toString(), apellidos.getText().toString(),
                        usuario.getText().toString(), contraseña.getText().toString(), email.getText().toString());

                mensaje.setText(op.insert_Usuarios(user));

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
