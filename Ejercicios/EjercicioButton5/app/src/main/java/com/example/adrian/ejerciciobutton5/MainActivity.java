package com.example.adrian.ejerciciobutton5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RadioGroup rg = (RadioGroup)findViewById(R.id.radio);
        final EditText n1 = (EditText)findViewById(R.id.n1);
        final EditText n2 = (EditText)findViewById(R.id.n2);
        final TextView resultado = (TextView)findViewById(R.id.resultado);
        final Button result = (Button)findViewById(R.id.result);

        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rg.getCheckedRadioButtonId() == R.id.suma){
                    int numero1 = Integer.parseInt(n1.getText().toString());
                    int numero2 = Integer.parseInt(n2.getText().toString());
                    int Resultado = numero1 + numero2;
                    String r = String.valueOf(Resultado);
                    resultado.setText(r);
                }
                if (rg.getCheckedRadioButtonId() == R.id.resta){
                    int numero1 = Integer.parseInt(n1.getText().toString());
                    int numero2 = Integer.parseInt(n2.getText().toString());
                    int Resultado = numero1 - numero2;
                    String r = String.valueOf(Resultado);
                    resultado.setText(r);
                }
            }
        });
    }
}
