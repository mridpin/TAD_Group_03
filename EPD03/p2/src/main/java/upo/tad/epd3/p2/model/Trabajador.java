/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upo.tad.epd3.p2.model;


/**
 *
 * @author ridao
 */
public class Trabajador {

    private String nombre;
    private String apellidos;
    private String dni;
    private String entrada;
    private String salida;
    private String fecha;

    public Trabajador(String nombre, String apellidos, String dni, String entrada, String salida, String fecha) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.entrada = entrada;
        this.salida = salida;
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEntrada() {
        return entrada;
    }

    public void setEntrada(String entrada) {
        this.entrada = entrada;
    }

    public String getSalida() {
        return salida;
    }

    public void setSalida(String salida) {
        this.salida = salida;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

}
