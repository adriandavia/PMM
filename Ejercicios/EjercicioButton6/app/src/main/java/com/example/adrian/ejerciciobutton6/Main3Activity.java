package com.example.adrian.ejerciciobutton6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Button volver = (Button)findViewById(R.id.button2);
        final TextView pantalla = (TextView)findViewById(R.id.textView2);

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent volver = new Intent(Main3Activity.this, MainActivity.class);
                volver.putExtra("pantalla", "Vuelvo de " + pantalla.getText() + " y estoy en ");
                startActivity(volver);
            }
        });
    }
}
