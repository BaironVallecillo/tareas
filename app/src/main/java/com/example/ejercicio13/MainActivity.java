package com.example.ejercicio13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ejercicio13.Configuracion.SQLiteConexion;
import com.example.ejercicio13.Configuracion.Transacciones;

public class MainActivity extends AppCompatActivity {

    EditText txtnombre, txtapellido, txtedad, txtcorreo, txtdireccion;
    Button btnenviar, btnVer;

   public static String txtnombre2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        txtnombre = findViewById(R.id.txtNombre);
        txtapellido = findViewById(R.id.txtApellido);
        txtedad = findViewById(R.id.txtEdad);
        txtcorreo = findViewById(R.id.txtCorreo);
        txtdireccion = findViewById(R.id.txtDireccion);

        btnenviar = findViewById(R.id.btnEnviar);
        btnVer = findViewById(R.id.btnVer);


        btnenviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AgregarPersonas();
            }
        });

        btnVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ActivityLisView.class);
                startActivity(intent);
            }
        });
    }

    private void AgregarPersonas() {

        SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.NameDatabase, null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(Transacciones.nombres, txtnombre.getText().toString());
        valores.put(Transacciones.apellidos, txtapellido.getText().toString());
        valores.put(Transacciones.edad, txtedad.getText().toString());
        valores.put(Transacciones.correo, txtcorreo.getText().toString());
        valores.put(Transacciones.direccion, txtdireccion.getText().toString());

        Long resultado = db.insert(Transacciones.tablaPersonas,Transacciones.id,valores);

        Toast.makeText(getApplicationContext(), "Registro Ingresado : Codigo : " + resultado.toString(), Toast.LENGTH_LONG).show();

        db.close();

        LimpiarPantalla();

    }

    private void LimpiarPantalla() {

        txtnombre.setText("");
        txtapellido.setText("");
        txtedad.setText("");
        txtcorreo.setText("");
        txtdireccion.setText("");

    }
}