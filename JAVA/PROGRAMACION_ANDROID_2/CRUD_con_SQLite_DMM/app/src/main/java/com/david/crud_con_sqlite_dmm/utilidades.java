package com.david.crud_con_sqlite_dmm;

public class utilidades {
    // CONSTANTES PARA LOS CAMPOS DE LA TABLA USUARIO
    public static final String TABLA_USUARIO = "usuario";
    public static final String CAMPO_ID = "id";
    public static final String CAMPO_NOMBRE = "nombre";
    public static final String CAMPO_DOMICILIO = "domicilio";
    public static final String CAMPO_EMAIL = "email";
    public static final String CAMPO_PASSWORD = "password";

    // SE CREA LA TABLA USUARIO CON SUS VALORES
    public static final String CREAR_TABLA_USUARIO = "CREATE TABLE "+TABLA_USUARIO+" ("+CAMPO_ID+" integer primary key autoincrement,"
            +CAMPO_NOMBRE+" text,"+CAMPO_DOMICILIO+" text,"+CAMPO_EMAIL+" text,"+CAMPO_PASSWORD+" text)";
}
