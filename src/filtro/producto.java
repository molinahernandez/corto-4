/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filtro;

/**
 *
 * @author LN710Q
 */
public class producto {
    private int codigo;
    private int precio;
    private String nombre;
    private int cantidad;
    private String tipo;
    private boolean disponibilidad;
    
    public producto(){
    }

    public producto(int codigo, int precio, String nombre, int cantidad, String tipo, boolean disponibilidad) {
        this.codigo = codigo;
        this.precio = precio;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.tipo = tipo;
        this.disponibilidad = disponibilidad;
    }

    public producto(int precio, String nombre, int cantidad, String tipo, boolean disponibilidad) {
        this.precio = precio;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.tipo = tipo;
        this.disponibilidad = disponibilidad;
    }

    public producto(String nombre, int cantidad, String tipo, boolean disponibilidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.tipo = tipo;
        this.disponibilidad = disponibilidad;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }
  
}