package org.edgar.interfazbanco.models;

public class Usuario {
    private int id;               // ✅ ID agregado
    private String cedula;
    private String clave;
    private String rol;
    private String nombres;
    private String apellidos;

    public Usuario() {
    }

    public Usuario(int id, String cedula, String clave, String rol, String nombres, String apellidos) {
        this.id = id;
        this.cedula = cedula;
        this.clave = clave;
        this.rol = rol;
        this.nombres = nombres;
        this.apellidos = apellidos;
    }

    // Getters y Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

}
