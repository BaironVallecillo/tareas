package com.example.ejercicio13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ejercicio13.Configuracion.SQLiteConexion;
import com.example.ejercicio13.Configuracion.Transacciones;
import com.example.ejercicio13.Tablas.Personas;

import java.util.ArrayList;

public class ActivityLisView extends AppCompatActivity {


    public static Integer id = 0;

    SQLiteConexion conexion;
    ListView listapersonas;
    ArrayList<Personas> lista;
    ArrayList<String> ArregloPersonas;
    Button btnVolver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lis_view);

        btnVolver = findViewById(R.id.btnVolver);

        conexion = new SQLiteConexion(this, Transacciones.NameDatabase, null, 1);
        listapersonas = (ListView) findViewById(R.id.listapersonas);

        ObtenerListaPersonas();

        ArrayAdapter adp = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ArregloPersonas);
        listapersonas.setAdapter(adp);


        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        listapersonas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), EditarElementos.class);
                String ID = ArregloPersonas.get(i);
                //Toast.makeText(getApplicationContext(), ID.toString(),Toast.LENGTH_LONG).show();
                intent.putExtra("id", ID.toString());
                startActivity(intent);

            }
        });


    }





    private void ObtenerListaPersonas() {
        SQLiteDatabase db = conexion.getReadableDatabase();
        Personas list_personas = null;
        lista = new ArrayList<Personas>();

        //cursor de la base de datos : nos apoya en recorrer  la info de la tabla a la cual consultamos

        Cursor cursor = db.rawQuery("SELECT * FROM " + Transacciones.tablaPersonas, null);



        //recorremos la info del cursor
        while (cursor.moveToNext()){

            list_personas = new Personas();
            list_personas.setId(cursor.getInt(0));
            list_personas.setNombres(cursor.getString(1));
            list_personas.setApellidos(cursor.getString(2));
            list_personas.setEdad(cursor.getInt(3));
            list_personas.setCorreo(cursor.getString(4));
            list_personas.setDireccion(cursor.getString(5));



            lista.add(list_personas);


        }



        db.close();

        filllist();

    }

    private void filllist() {
        ArregloPersonas = new ArrayList<String>();

        for (int i = 0;i < lista.size(); i++)
        {
            ArregloPersonas.add(lista.get(i).getId()+ "  "
                    +lista.get(i).getNombres()+ "  "
                    +lista.get(i).getApellidos()+ "\n"
                    +lista.get(i).getCorreo());
        }


    }
}