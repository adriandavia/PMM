package com.example.mati.listadetareas;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends ListActivity {
    //acciones
    public static final int NEW_ITEM = 1;
    public static final int EDIT_ITEM = 2;
    public static final int SHOW_ITEM = 3;

    //elemento seleccionado
    private int mLastRowSelected = 0;
    public static DatabaseHelper mDbHelper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //abrir la base de datos
        mDbHelper = new DatabaseHelper(this);
        try{

        }catch (SQLException e){
            e.printStackTrace();
            showMessage(R.string.dataError);
        }
    }
    private void showMessage(int message){
        Context context = getApplicationContext();
        CharSequence text = getResources().getString(message);
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
    private void fillData(){
        //se abre la base de datos y se obtienen los elementos
        mDbHelper.open();
        Cursor itemCursor = mDbHelper.getItems();
        ListEntry item = null;
        ArrayList<ListEntry> resultList = new ArrayList<ListEntry>();
        //se procesa el resultado
        while (itemCursor.moveToNext()){
            int id = itemCursor.getInt(itemCursor.getColumnIndex(DatabaseHelper.SL_ID));
            String task = itemCursor.getString(itemCursor.getColumnIndex(DatabaseHelper.SL_ITEM));
            String place = itemCursor.getString(itemCursor.getColumnIndex(DatabaseHelper.SL_PLACE));
            String importante = itemCursor.getString(itemCursor.getColumnIndex(DatabaseHelper.SL_IMPORTANTE));
            item = new ListEntry();
            item.id = id;
            item.task = task;
            item.place = place;
            item.importante = importante;
            resultList.add(item);
        }
        //cerramos la base de datos
        itemCursor.close();
        mDbHelper.close();
        //se genera el adaptador
        TaskAdapter items = new TaskAdapter(this, R.layou.row_list, resultList, getLayoutInflater());
        //asignar adaptador a la lista
        setListAdapter(items);
    }
    public boolean onCreateOptionsMenu (Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }
    public boolean onOptionsItemSelected (MenuItem item){
        //Handle item selection
        switch (item.getItemId()){
            case R.id.new_item:
                Intent intent = new Intent (this, ItemActivity.class);
                startActivityForResult(intent, NEW_ITEM);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class TaskAdapter extends ArrayAdapter<ListEntry>{

    }
    public View getView(int position, View converView, ViewGroup parent){

    }
    private class ListEntry{
        int id;
        String task;
        String place;
        int importante;
    }
}
