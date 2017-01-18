package com.example.adrian.proyectotrimestral_adriandavia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AcercaDe extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerca_de);

        Button volver = (Button)findViewById(R.id.advolver);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent volver;
                int valor = getIntent().getIntExtra("valor", 0);
                if (valor == 1){
                    volver = new Intent(AcercaDe.this, Loging.class);
                    startActivity(volver);
                    finish();
                }else if (valor == 2){
                    volver = new Intent(AcercaDe.this, Registro.class);
                    startActivity(volver);
                    finish();
                }
            }
        });
    }
}
