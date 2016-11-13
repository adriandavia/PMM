package com.example.adrian.ejerciciobutton4;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ToggleButton rojo = (ToggleButton)findViewById(R.id.rojo);
        final ToggleButton verde = (ToggleButton)findViewById(R.id.verde);
        final ToggleButton azul = (ToggleButton)findViewById(R.id.azul);
        final TextView textView = (TextView)findViewById(R.id.textView2);

        rojo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rojo.isChecked() == true){
                    int crojo = Color.parseColor("#ff0000");
                    textView.setBackgroundColor(crojo);
                }
                else{
                    int cnegro = Color.parseColor("#ff000000");
                    textView.setBackgroundColor(cnegro);
                }
            }
        });

        verde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (verde.isChecked() == true){
                    int cverde = Color.parseColor("#FF40FF43");
                    textView.setBackgroundColor(cverde);
                }
                else{
                    int cnegro = Color.parseColor("#ff000000");
                    textView.setBackgroundColor(cnegro);
                }
            }
        });

        azul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (azul.isChecked() == true){
                    int cazul = Color.parseColor("#FF03A7ED");
                    textView.setBackgroundColor(cazul);
                }
                else{
                    int cnegro = Color.parseColor("#ff000000");
                    textView.setBackgroundColor(cnegro);
                }
            }
        });
    }
}
