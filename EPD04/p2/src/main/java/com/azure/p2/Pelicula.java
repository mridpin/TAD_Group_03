package com.azure.p2;

import java.util.ArrayList;

public class Pelicula {

    public String titulo;
    public String sipnosis;
    public String sala;
    public ArrayList<String> sesiones;

    public Pelicula() {
        sesiones = new ArrayList<>();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSipnosis() {
        return sipnosis;
    }

    public void setSipnosis(String sipnosis) {
        this.sipnosis = sipnosis;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public ArrayList<String> getSesiones() {
        return sesiones;
    }

    public void setSesiones(ArrayList<String> sesiones) {
        this.sesiones = sesiones;
    }

    public void addSesiones(String sesiones) {
        this.sesiones.add(sesiones);
    }
}
