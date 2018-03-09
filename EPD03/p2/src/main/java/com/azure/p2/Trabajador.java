package com.azure.p2;

import java.util.Date;

public class Trabajador {

    public String nombre;
    public String apellidos;
    public String dni;
    public String horaEntrada;
    public String horaSalida;
    public Date Fecha;

    public Trabajador(String nombre, String apellidos, String dni, String horaEntrada, String horaSalida, Date Fecha) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.Fecha = Fecha;
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

    public String getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(String horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date Fecha) {
        this.Fecha = Fecha;
    }

    @Override
    public String toString() {
        return nombre + " " + apellidos + ", DNI: " + dni + ", Hora entrada: " + horaEntrada + ", Hora salida: " + horaSalida + ", Fecha: " + Fecha.getDay() +"/"+ Fecha.getMonth() +"/"+ Fecha.getYear();
    }

}
