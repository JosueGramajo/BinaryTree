package com.josue.objects;

/**
 * Created by josue on 19/03/2018.
 */
public class Estudiante {
    private int id;
    private String nombre;
    private EstadoLlamada estado;

    public enum EstadoLlamada{
        EN_LLAMADA,
        EN_ESPERA,
        DESCONECTADO;
    }

    public Estudiante (int id, String nombre, EstadoLlamada estado){
        this.setId(id);
        this.setNombre(nombre);
        this.setEstado(estado);
    }

    public Estudiante(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public EstadoLlamada getEstado() {
        return estado;
    }

    public void setEstado(EstadoLlamada estado) {
        this.estado = estado;
    }
}
