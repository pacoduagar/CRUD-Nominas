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
    /**
     * 
     * @param id
     * @param nombre
     * @param dni
     * @param sexo
     * @param categoria
     * @param anyos
     */
    public Empleados(int id, String nombre, String dni, char sexo, int categoria, int anyos) {
        this.id = id;
        this.nombre = nombre;
        this.dni = dni;
        this.sexo = sexo;
        this.categoria = categoria;
        this.anyos = anyos;
    }

    
    /**
     * 
     * @return
     */
    public int getId() {
        return id;
    }
    /**
     * 
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * 
     * @return
     */
    public String getNombre() {
        return nombre;
    }
   /**
    * 
    * @param nombre
    */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * 
     * @return
     */
    public String getDni() {
        return dni;
    }
    /**
     * 
     * @param dni
     */
    public void setDni(String dni) {
        this.dni = dni;
    }
    /**
     * 
     * @return
     */
    public String getSexo() {
        return String.valueOf(sexo);
    }
    /**
     * 
     * @param sexo
     */
    public void setSexo(char sexo) {
        this.sexo = sexo;
    }
    /**
     * 
     * @return
     */
    public int getCategoria() {
        return categoria;
    }
    /**
     * 
     * @param categoria
     */
    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }
    /**
     * 
     * @return
     */
    public int getAnyos() {
        return anyos;
    }
    /**
     * 
     * @param anyos
     */
    public void setAnyos(int anyos) {
        this.anyos = anyos;
    }
    /**
     * 
     * @return
     */
    public int calcularSueldo() {
        return SUELDO_BASE[this.categoria - 1] + 5000 * this.anyos;
    }
}
