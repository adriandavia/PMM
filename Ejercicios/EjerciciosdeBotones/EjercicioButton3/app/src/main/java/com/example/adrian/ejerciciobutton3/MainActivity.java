package com.example.adrian.ejerciciobutton3;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RadioGroup rg = (RadioGroup) findViewById(R.id.rgcolor);
        final RadioButton rojo = (RadioButton) findViewById(R.id.rojo);
        final RadioButton verde = (RadioButton) findViewById(R.id.verde);
        final RadioButton azul = (RadioButton) findViewById(R.id.azul);
        final Button dibujar = (Button)findViewById(R.id.dibujar);
        final TextView textView = (TextView) findViewById(R.id.textView);

        final Context context = this;
        dibujar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rg.getCheckedRadioButtonId() == R.id.rojo) {
                    textView.setBackgroundColor(ContextCompat.getColor(context, R.color.rojo));
                }
                if (rg.getCheckedRadioButtonId() == R.id.verde) {
                    textView.setBackgroundColor(ContextCompat.getColor(context, R.color.verde));
                }
                if (rg.getCheckedRadioButtonId() == R.id.azul) {
                    textView.setBackgroundColor(ContextCompat.getColor(context, R.color.azul));
                }
            }
        });

    }
}
