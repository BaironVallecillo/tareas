package com.example.ejercicio13.Configuracion;

public class Transacciones {

    //Nombre de Base de Datos

    public static final String NameDatabase = "Ejercicion1.3";

    //tablas de base de datos de SQLite

    public  static final String tablaPersonas = "personas";

    //campos de la tabla personas de la base de datos en sqlite

    public static final String id = "id";
    public static final String nombres = "nombres";
    public static final String apellidos = "apellidos";
    public static final String edad = "edad";
    public static final String correo = "correo";
    public static final String direccion = "direccion";

    //Transacciones DML Tabla Personas

    public  static  final String CreateTablePersonas ="CREATE TABLE personas (id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nombres TEXT, apellidos TEXT, edad INTEGER, correo TEXT,direccion TEXT)";

    public static final String DROPTablePersonas ="DROP TABLE IF EXISTS personas";


}
