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

        //Creamos la base de datos
        final BdTareasSQLiteHelper bdHelper = new BdTareasSQLiteHelper(this, "dbTareas", null, 1);

        //Objeto de la case operacion para poder usar sus metodos
        final OperacionesSQL operacionesSQL = new OperacionesSQL();

        //Boton con callbak para ir a la pantalla registro
        Button pasar = (Button)findViewById(R.id.registrar);
        pasar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogginActivity.this, RegistroActivity.class);
                startActivityForResult(intent, COD_RESPUESTA);
            }
        });

        //boton entrar. Nos permite comprobar todos los datos de la tabla usuarios
        //habia un problema con los mensajes de error, por que lo que neceisto un boolean para
        //solucionarlo
        Button entrar = (Button)findViewById(R.id.entrar);
        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Abrimos la base de datos en modo escritura
                final SQLiteDatabase bd = bdHelper.getReadableDatabase();
                EditText usuario = (EditText)findViewById(R.id.usuario);
                EditText contraseña = (EditText)findViewById(R.id.password);

                String user = usuario.getText().toString();
                String password = contraseña.getText().toString();
                TextView mensaje = (TextView)findViewById(R.id.mensajecr);
                Cursor cursor = operacionesSQL.select_usuarios(bd);
                boolean error = true;
                if (cursor.moveToFirst()){
                    do {
                        String name = cursor.getString(0);
                        String surname = cursor.getString(1);
                        String us = cursor.getString(2);
                        String pass = cursor.getString(3);
                        String email = cursor.getString(4);
                        if (user.equals(us) && password.equals(pass)){
                            Intent intent = new Intent(LogginActivity.this, TareasDiariasActivity.class);
                            Usuarios userlogeado = new Usuarios(name, surname, us, pass, email);
                            Bundle pasarobjetos = new Bundle();
                            pasarobjetos.putSerializable("usuario", userlogeado);
                            intent.putExtras(pasarobjetos);
                            error = false;
                            startActivity(intent);
                            finish();
                            bdHelper.close();
                        }
                    }while (cursor.moveToNext());
                    if (error){
                        mensaje.setText("Usuario o contraseña incorrecta.");
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
