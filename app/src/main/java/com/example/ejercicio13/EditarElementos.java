package com.example.ejercicio13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ejercicio13.Configuracion.SQLiteConexion;
import com.example.ejercicio13.Configuracion.Transacciones;

public class EditarElementos extends AppCompatActivity {

    SQLiteConexion conexion;
    Button btneliminar, btnactualizar, btnbuscar, btnvolver2;

    EditText txtnombre2, txtapellido2, txtedad2, txtcorreo2, txtdireccion2, txtid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_elementos);

        txtid = findViewById(R.id.txtid);
        txtnombre2 = findViewById(R.id.txtNombre2);
        txtapellido2 = findViewById(R.id.txtApellido2);
        txtedad2 = findViewById(R.id.txtEdad2);
        txtcorreo2 = findViewById(R.id.txtCorreo2);
        txtdireccion2 = findViewById(R.id.txtDireccion2);

        btnvolver2 = findViewById(R.id.btnVolver2);
        btnactualizar = findViewById(R.id.btnActualizar);
        btneliminar = findViewById(R.id.btnEliminar);
        btnbuscar = findViewById(R.id.btnBuscar);

        // llamar a la conexion de bd sqlite
        conexion = new SQLiteConexion(this, Transacciones.NameDatabase, null, 1);

        String txtidEnviado = getIntent().getStringExtra("id");


        txtid.setText(txtidEnviado);

        Toast.makeText(getApplicationContext(), "Dato "+ txtid, Toast.LENGTH_LONG).show();

        if(txtid == null)
        {
            Toast.makeText(getApplicationContext(), "No hay parametros ", Toast.LENGTH_LONG).show();
        }
        else
        {
            Buscar();
        }

        btnbuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Buscar();
            }
        });

        btneliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Eliminar();
                Intent intent = new Intent(getApplicationContext(), ActivityLisView.class);
                startActivity(intent);
            }
        });

        btnactualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Actualizar();
                Intent intent = new Intent(getApplicationContext(), ActivityLisView.class);
                startActivity(intent);

            }
        });

        btnvolver2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ActivityLisView.class);
                startActivity(intent);

            }
        });



    }

    private void Eliminar() {

        SQLiteDatabase db = conexion.getWritableDatabase();
        String [] params = {txtid.getText().toString()};
        String wherecond = Transacciones.id + "=?";
        db.delete(Transacciones.tablaPersonas, wherecond, params);
        Toast.makeText(getApplicationContext(), "Dato eliminado", Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(), txtid.getText(), Toast.LENGTH_LONG).show();





        Limpiar();
    }

    private void Actualizar() {
        SQLiteDatabase db = conexion.getWritableDatabase();
        String [] params = {
                txtid.getText().toString()};
        ContentValues valores = new ContentValues();
        valores.put(Transacciones.nombres, txtnombre2.getText().toString());
        valores.put(Transacciones.apellidos, txtapellido2.getText().toString());
        valores.put(Transacciones.edad, txtedad2.getText().toString());
        valores.put(Transacciones.correo, txtcorreo2.getText().toString());
        valores.put(Transacciones.direccion, txtdireccion2.getText().toString());
        db.update(Transacciones.tablaPersonas, valores, Transacciones.id + "=?", params);
        Toast.makeText(getApplicationContext(), "Elemento actualizado", Toast.LENGTH_LONG).show();
        Limpiar();
    }


    private void Buscar() {

        SQLiteDatabase db = conexion.getWritableDatabase();
        /* Parametros de configuracion de la sentencia SELECT */
        String [] params = {txtid.getText().toString()};
        // parametro de la busqueda
        String [] fields = {Transacciones.nombres,
                Transacciones.apellidos,
                Transacciones.edad,
                Transacciones.correo,
                Transacciones.direccion };
        String wherecond = Transacciones.id + "=?";

        try{
            Cursor cdata = db.query(Transacciones.tablaPersonas, fields, wherecond, params, null,null, null );
            cdata.moveToFirst();
            txtnombre2.setText(cdata.getString(0));
            txtapellido2.setText(cdata.getString(1));
            txtedad2.setText(cdata.getString(2));
            txtcorreo2.setText(cdata.getString(3));
            txtdireccion2.setText(cdata.getString(4));
            Toast.makeText(getApplicationContext(), "Consultado con exito",Toast.LENGTH_LONG).show();
        }
        catch (Exception ex) {
            Limpiar();
            Toast.makeText(getApplicationContext(), "Elemento no encontrado", Toast.LENGTH_LONG).show();
        }
    }


    private void Limpiar() {
        txtnombre2.setText("");
        txtapellido2.setText("");
        txtedad2.setText("");
        txtcorreo2.setText("");
        txtdireccion2.setText("");
    }
}