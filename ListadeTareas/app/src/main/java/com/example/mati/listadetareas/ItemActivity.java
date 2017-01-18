package com.example.mati.listadetareas;

import android.app.Activity;
import android.content.Context;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ItemActivity extends Activity {
    //referencia a elementos de pantalla
    TextView mItem = null;
    TextView mPlace = null;
    TextView mDescription = null;
    TextView mImportance = null;

    //Identificador de entrada
    Integer mRowId = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        //boton de guardar
        Button saveBtn = (Button)findViewById(R.id.add);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                saveData();
                finish();
            }
        });
        //obtener referencias
        mItem = (TextView) findViewById(R.id.item);
        mPlace = (TextView)findViewById(R.id.place);
        mDescription = (TextView)findViewById(R.id.description);
        mImportance = (TextView)findViewById(R.id.imoprtante);
    }
    //obtener datos
    String itemText = mItem.getText().toString();
    String placeText = mPlace.getText().toString();
    String descriptionText = mDescription.getText().toString();
    String imoprtanteText = mImportance.getText().toString();
    //insertar
    try{
        MainActivity.mDbHelper.open();
        MainActivity.mDbHelper.insertItem(itemText, placeText, descriptionText, Integer.parseInt(imoprtanteText));
        MainActivity.mDHelper.close();
    } catch (SQLException e){
        e.printStackTrace();
        showMessage(R.string.dataError);
    }

    private void showMessage (int message){
        Context context = getApplicationContext();
        CharSequence text = getResources().getString(message);
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
