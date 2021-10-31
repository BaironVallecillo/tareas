package com.example.ejercicio13.Configuracion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteConexion extends SQLiteOpenHelper {

    public SQLiteConexion(Context context, String dbname, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, dbname, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //creacion de la primera tablas de DB
        db.execSQL(Transacciones.CreateTablePersonas);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        //creacion de las tablas al momento de eliminar la info de la BD // BD Limpia

        db.execSQL(Transacciones.DROPTablePersonas);
        onCreate(db);
    }

}
