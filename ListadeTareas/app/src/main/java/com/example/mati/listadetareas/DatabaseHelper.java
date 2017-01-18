package com.example.mati.listadetareas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper {
    private Context mCtx = null;
    private DataBaseHelperInternal mDbHelper = null;
    private SQLiteDatabase mDb = null;
    private static final String DATABASE_NAME = "TODOLIST";
    private static final int DATABASE_VERSION = 3;
    //tabla y campos
    private static final String DATABASE_TABLE_TODOLIST = "todolist";
    public static final String SL_ID = "_id";
    public static final String SL_ITEM = "task";
    public static final String SL_PLACE = "place";
    public static final String SL_IMPORTANTE = "importante";
    public static final String SL_DESCRIPTION = "description";

    //SQL de creación de tabla
    private static final String DATABASE_CREATE_TODOLIST =
            "create table" + DATABASE_TABLE_TODOLIST + " (" + SL_ID + " integer primary key, " +
                    SL_ITEM + " text not null, " + SL_PLACE + " text not null, " + SL_IMPORTANTE +
                    " integer not null, " + SL_DESCRIPTION + " text)";

    //contructor
    public DatabaseHelper(Context ctx) {
        this.mCtx = ctx;
    }

    //clase primavada para control de la SQLite
    private static class DataBaseHelperInternal extends SQLiteOpenHelper {
        public DataBaseHelperInternal(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        public void OnCreate(SQLiteDatabase db) {
            createTables(db);
        }
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
            deleteTables(db);
            createTables(db);
        }
        private void createTables(SQLiteDatabase db){
            db.execSQL(DATABASE_CREATE_TODOLIST);
        }
        private void deleteTables(SQLiteDatabase db){
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_TODOLIST);
        }
    }

    public DatabaseHelper open(){
        mDbHelper = new DataBaseHelperInternal(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }
    public void close(){
        mDbHelper.close();
    }

    //obtener todos los alementos
    public Cursor getItems(){
        return mDb.query(DATABASE_TABLE_TODOLIST, new String[] {SL_ID, SL_PLACE, SL_IMPORTANTE}, null,
                null, null, null, SL_IMPORTANTE);
    }

    //crear elemento
    public long insertItem(String item, String place, String description, int importante){
        ContentValues initialValues = new ContentValues();
        initialValues.put(SL_IMPORTANTE, importante);
        initialValues.put(SL_ITEM, item);
        initialValues.put(SL_PLACE, place);
        initialValues.put(SL_DESCRIPTION, description);
        return mDb.insert(DATABASE_TABLE_TODOLIST, null, initialValues);
    }
}

