package com.example.mati.examenalquilercoches_adriandavia;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Dibujar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new Dibujo(this));

    }

    public class Dibujo extends View {
        public Dibujo(Context context) {
            super(context);
        }


        protected void onDraw(Canvas canvas) {
            Paint lineas = new Paint();
            Paint circulos = new Paint();
            lineas.setColor(Color.BLACK);
            lineas.setStrokeWidth(15);
            lineas.setStyle(Paint.Style.STROKE);
            circulos.setColor(Color.BLACK);
            circulos.setStrokeWidth(15);
            circulos.setStyle(Paint.Style.STROKE);

            canvas.drawLine(270, 200, 930, 200, lineas);
            canvas.drawLine(240,400 , 150, 400, lineas);
            canvas.drawCircle(300, 400, 60, circulos);
            canvas.drawLine(360,400 ,740, 400, lineas);
            canvas.drawCircle(800, 400, 60, circulos);
            canvas.drawLine(860, 400 , 930, 400, lineas);
            canvas.drawLine(930, 400, 930, 200, lineas);
            canvas.drawLine(270, 200, 160, 400, lineas);

        }
    }
}
