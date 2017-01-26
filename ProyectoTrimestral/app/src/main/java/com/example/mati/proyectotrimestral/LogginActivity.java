package com.example.mati.proyectotrimestral;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LogginActivity extends AppCompatActivity {
    public static int COD_RESPUESTA = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loggin);

        BdTareasSQLiteHelper bdHelper = new BdTareasSQLiteHelper(this, "DBTareas.bd", null, 1);
        SQLiteDatabase bd = bdHelper.getWritableDatabase();

        Button pasar = (Button)findViewById(R.id.registrar);
        pasar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogginActivity.this, RegistroActivity.class);
                startActivityForResult(intent, COD_RESPUESTA);
                finish();
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
