package com.example.mati.ejercicio1_linearlayout;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RadioGroup rgcolor = (RadioGroup)findViewById(R.id.rgcolor);
        final Button color = (Button)findViewById(R.id.acolor);
        final Button borrar = (Button)findViewById(R.id.borrar);
        final TextView textview = (TextView)findViewById(R.id.color);

        final Context context = this;
        color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rgcolor.getCheckedRadioButtonId() == R.id.rbutton)
                    textview.setBackgroundColor(ContextCompat.getColor(context, R.color.rojo));
                if (rgcolor.getCheckedRadioButtonId() == R.id.abutton)
                    textview.setBackgroundColor(ContextCompat.getColor(context, R.color.azul));
                if (rgcolor.getCheckedRadioButtonId() == R.id.vbutton)
                    textview.setBackgroundColor(ContextCompat.getColor(context, R.color.verde));
            }
        });
        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textview.setBackgroundColor(ContextCompat.getColor(context, R.color.blanco));
            }
        });
    }
}
