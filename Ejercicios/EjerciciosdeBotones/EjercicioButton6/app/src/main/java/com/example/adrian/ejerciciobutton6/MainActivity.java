package com.example.adrian.ejerciciobutton6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button pantalla2 = (Button)findViewById(R.id.button);
        final Button pantalla3 = (Button)findViewById(R.id.button3);
        final TextView result = (TextView)findViewById(R.id.pantalla);

        pantalla2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent paso = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(paso);
            }
        });

        pantalla3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent paso = new Intent(MainActivity.this, Main3Activity.class);
                startActivity(paso);
            }
        });
        Bundle bundle = getIntent().getExtras();

        if (bundle == null){
            result.setText("Pantalla 1");
        } else {
            String vuelta = getIntent().getStringExtra("pantalla");
            result.setText(vuelta + " Pantalla 1");
        }
    }
}
