package com.webnominas.model;

public class Empleados {
    private int id;
    private String nombre;
    private String dni;
    private char sexo;
    private int categoria;
    private int anyos;

    private static final int SUELDO_BASE[] = {50000, 70000, 90000, 110000, 130000,
            150000, 170000, 190000, 210000, 230000};
    
    public Empleados() {
    }

    public Empleados(int id, String nombre, String dni, char sexo, int categoria, int anyos) {
        this.id = id;
        this.nombre = nombre;
        this.dni = dni;
        this.sexo = sexo;
        this.categoria = categoria;
        this.anyos = anyos;
    }

    // Getters y Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getSexo() {
        return String.valueOf(sexo);
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public int getAnyos() {
        return anyos;
    }

    public void setAnyos(int anyos) {
        this.anyos = anyos;
    }
    
    public int calcularSueldo() {
        return SUELDO_BASE[this.categoria - 1] + 5000 * this.anyos;
    }
}
