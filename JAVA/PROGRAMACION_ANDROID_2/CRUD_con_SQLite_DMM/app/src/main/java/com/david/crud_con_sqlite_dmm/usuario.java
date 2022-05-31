package com.david.crud_con_sqlite_dmm;
public class usuario {
    private Integer id;
    private String nombre, domicilio, email, password;
    // SE PASAN LOS VALORES A LAS VARIABLES
    public usuario(){
        this.id = id; this.nombre = nombre; this.domicilio = domicilio; this.email = email; this.password = password;
    }
    // COLOCAMOS LOS RESPECTIVOS GETTERS Y SETTERS
    public Integer getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public String getDomicilio() {
        return domicilio;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
