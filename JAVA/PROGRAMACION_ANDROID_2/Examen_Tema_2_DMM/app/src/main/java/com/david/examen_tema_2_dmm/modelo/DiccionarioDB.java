package com.david.examen_tema_2_dmm.modelo;

public class DiccionarioDB {
    // CONSTANTES PARA LOS CAMPOS DE LA TABLA USUARIO
    public static final String NOMBRE_TABLA = "citas";
    public static final String CAMPO_ID = "id";
    public static final String CAMPO_NOMBRE = "nombre";
    public static final String CAMPO_APELLIDOP = "apellidop";
    public static final String CAMPO_APELLIDOM = "apellidom";
    public static final String CAMPO_CALLE = "calle";
    public static final String CAMPO_NoINTE = "nointerior";
    public static final String CAMPO_NoEXTE = "noexterior";
    public static final String CAMPO_COLONIA = "colonia";
    public static final String CAMPO_MUNICIPIO = "municipio";
    public static final String CAMPO_ESTADO = "estado";
    public static final String CAMPO_PAIS = "pais";
    public static final String CAMPO_TELEFONO = "telefono";
    public static final String CAMPO_EMAIL = "correo";
    public static final String CAMPO_SEXO = "sexo";
    public static final String CAMPO_EDAD = "edad";
    public static final String CAMPO_DIA = "dia";
    public static final String CAMPO_MES = "mes";
    public static final String CAMPO_YEAR = "year";
    public static final String CAMPO_ESTATURA = "estatura";
    public static final String CAMPO_PESO = "peso";
    public static final String CAMPO_IMAGEN = "imagen";

    // SE CREA LA TABLA CITAS CON SUS VALORES
    public static final String CREAR_TABLA_CITAS = "CREATE TABLE "+NOMBRE_TABLA+" ("+
            CAMPO_ID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
            CAMPO_NOMBRE+" TEXT,"+
            CAMPO_APELLIDOP+ " TEXT,"+
            CAMPO_APELLIDOM+ " TEXT,"+
            CAMPO_CALLE+ " TEXT,"+
            CAMPO_NoINTE+ " INTEGER,"+
            CAMPO_NoEXTE+ " INTEGER,"+
            CAMPO_COLONIA+ " TEXT,"+
            CAMPO_MUNICIPIO+ " TEXT,"+
            CAMPO_ESTADO+ " TEXT,"+
            CAMPO_PAIS+ " TEXT,"+
            CAMPO_TELEFONO+ " TEXT,"+
            CAMPO_EMAIL+ " TEXT,"+
            CAMPO_SEXO+ " TEXT,"+
            CAMPO_EDAD+ " INTEGER,"+
            CAMPO_DIA+ " INTEGER,"+
            CAMPO_MES+ " INTEGER,"+
            CAMPO_YEAR+ " INTEGER,"+
            CAMPO_ESTATURA+ " REAL,"+
            CAMPO_PESO+" REAL,"+
            CAMPO_IMAGEN+" BLOB);";
}
