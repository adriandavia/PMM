package com.example.mati.proyectotrimestral;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mati.proyectotrimestral.OperacionesSQL;

public class RegistroActivity extends AppCompatActivity {
    BdTareasSQLiteHelper bdTareasSQLiteHelper = new BdTareasSQLiteHelper(this, "dbTareas", null, 1);
    SQLiteDatabase sqLiteDatabase;
    OperacionesSQL operacionesSQL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        Button pasar = (Button)findViewById(R.id.rvolver);
        pasar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistroActivity.this, LogginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        Button registrar = (Button)findViewById(R.id.runirse);
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText nombre = (EditText)findViewById(R.id.rnombre);
                EditText apellidos = (EditText)findViewById(R.id.rapellidos);
                EditText username = (EditText)findViewById(R.id.rusuario);
                EditText password = (EditText)findViewById(R.id.rcontraseña);
                EditText correo = (EditText)findViewById(R.id.remail);

                Usuarios user = new Usuarios(nombre.getText().toString(), apellidos.getText().toString(),
                        username.getText().toString(), password.getText().toString(), correo.getText().toString());
                sqLiteDatabase = bdTareasSQLiteHelper.getWritableDatabase();
                TextView mensaje = (TextView)findViewById(R.id.mensajeerror);
               try{
                   operacionesSQL.insert_usuarios(sqLiteDatabase, user);
                   Intent volver = new Intent(RegistroActivity.this, LogginActivity.class);
                   setResult(RESULT_OK, volver);
                   finish();
               }catch (SQLiteException e){
                   mensaje.setText("El usuario o el correo que ha añadido ya ha sido registrado");
                   nombre.setText("");
                   apellidos.setText("");
                   username.setText("");
                   password.setText("");
                   correo.setText("");
               }
               bdTareasSQLiteHelper.close();
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
                Intent acerca = new Intent(this, AcercaDeActivity.class);
                startActivity(acerca);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
