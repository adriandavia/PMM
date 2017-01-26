package com.example.mati.proyectotrimestral;

import android.content.Intent;
import android.database.Cursor;
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
import android.widget.Toast;

public class LogginActivity extends AppCompatActivity {
    public static int COD_RESPUESTA = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loggin);
        //Titulo de la pantalla
        getSupportActionBar().setTitle("Loggin");
        BdTareasSQLiteHelper bdHelper = new BdTareasSQLiteHelper(this, "dbTareas", null, 1);
        final SQLiteDatabase bd = bdHelper.getWritableDatabase();
        final OperacionesSQL operacionesSQL = new OperacionesSQL();

        Button pasar = (Button)findViewById(R.id.registrar);
        pasar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogginActivity.this, RegistroActivity.class);
                startActivityForResult(intent, COD_RESPUESTA);
            }
        });

        Button entrar = (Button)findViewById(R.id.entrar);
        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText usuario = (EditText)findViewById(R.id.usuario);
                EditText contraseña = (EditText)findViewById(R.id.password);

                String user = usuario.getText().toString();
                String password = contraseña.getText().toString();
                TextView mensaje = (TextView)findViewById(R.id.mensajecr);
                Cursor cursor = operacionesSQL.select_usuarios(bd);
                boolean error = true;
                if (cursor.moveToFirst()){
                    do {
                        String us = cursor.getString(2);
                        String pass = cursor.getString(3);
                        if (user.equals(us) && password.equals(pass)){
                            Intent intent = new Intent(LogginActivity.this, TareasDiariasActivity.class);
                            startActivity(intent);
                            error = false;
                            finish();
                        }
                    }while (cursor.moveToNext());
                    if (error){
                        mensaje.setText("Usuario o correo incorrecto.");
                        usuario.setText("");
                        contraseña.setText("");
                    }
                }
            }
        });
    }
    //Callback
    public void onActivityResult (int cod_res, int code_result, Intent intent){
        if (code_result == RESULT_OK){
            TextView mensaje = (TextView)findViewById(R.id.mensajecr);
            mensaje.setText("Te has registrado correctamente. Prueba a entrar.");
        }
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
