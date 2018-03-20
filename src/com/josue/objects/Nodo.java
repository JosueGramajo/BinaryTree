package com.josue.objects;

/**
 * Created by josue on 19/03/2018.
 */
public class Nodo {
    private int id;
    private Estudiante estudiante;
    private Nodo nodo1;
    private Nodo nodo2;
    private int nivel;

    public Nodo(){}

    public Nodo(int id, Estudiante estudiante, Nodo nodo1, Nodo nodo2, int nivel){
        this.setId(id);
        this.setEstudiante(estudiante);
        this.setNodo1(nodo1);
        this.setNodo2(nodo2);
        this.setNivel(nivel);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Nodo getNodo1() {
        return nodo1;
    }

    public void setNodo1(Nodo nodo1) {
        this.nodo1 = nodo1;
    }

    public Nodo getNodo2() {
        return nodo2;
    }

    public void setNodo2(Nodo nodo2) {
        this.nodo2 = nodo2;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
}
