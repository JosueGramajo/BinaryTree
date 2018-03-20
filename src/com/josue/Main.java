package com.josue;

import com.josue.objects.Estudiante;
import com.josue.objects.Nodo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static com.josue.objects.Estudiante.EstadoLlamada.DESCONECTADO;
import static com.josue.objects.Estudiante.EstadoLlamada.EN_ESPERA;
import static com.josue.objects.Estudiante.EstadoLlamada.EN_LLAMADA;

public class Main {
    public static int id = 1;
    public static int indexEstudiante = 0;
    public static ArrayList<Estudiante> estudiantes = new ArrayList<Estudiante>();
    public static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

    public static void main (String args[]){
        Nodo nodoRaiz = crearNodo(1);
        crearEstudiantes();
        agregarEstudiante(nodoRaiz);

        try{
            int respuesta = 0;

            do{
                System.out.println("Ingrese una opcion");
                System.out.println("1. Ver estado actual de la llamada");
                System.out.println("2. Ver estado de los estudiantes");
                System.out.println("3. Desconectar estudiante de llamada");
                System.out.println("4. Salir");

                respuesta = Integer.parseInt(bf.readLine());

                switch (respuesta){
                    case 1:
                        mostrarNodos(nodoRaiz,"|--");
                        System.out.println("\n");
                        break;
                    case 2:
                        mostrarEstudiantes();
                        System.out.println("\n");
                        break;
                    case 3:
                        System.out.print("Ingrese el id del nodo a eliminar: ");
                        int idNodo = Integer.parseInt(bf.readLine());

                        if(idNodo == nodoRaiz.getId()){
                            System.out.println("NO SE PUEDE DESCONECTAR EL NODO RAIZ");
                        }else{
                            desconectarNodo(nodoRaiz, idNodo);
                            System.out.println("\n");

                            reAsignarEstudiantes(nodoRaiz);
                            System.out.println("\n");
                        }
                        break;
                }
            }while(respuesta != 4);

        }catch (IOException ioex){
            System.out.println("Error al leer respuesta del usuario");
        }catch (NumberFormatException nfe){
            System.out.println("El valor debe ser numerico");
        }
    }

    public static Nodo crearNodo(int nivel){
        Nodo nodo = new Nodo();
        nodo.setId(id++);
        nodo.setEstudiante(null);
        nodo.setNivel(nivel);
        if(nivel<4){
            nodo.setNodo1(crearNodo(nivel+1));
            nodo.setNodo2(crearNodo(nivel+1));
        }else{
            nodo.setNodo1(null);
            nodo.setNodo2(null);
        }
        return nodo;
    }

    public static void agregarEstudiante(Nodo nodo){
        Estudiante estudiante = estudiantes.get(indexEstudiante);
        nodo.setEstudiante(estudiante);
        estudiante.setEstado(EN_LLAMADA);
        indexEstudiante++;
        if(nodo.getNodo1()!=null){
            agregarEstudiante(nodo.getNodo1());
            agregarEstudiante(nodo.getNodo2());
        }
    }

    public static void mostrarNodos(Nodo nodo, String tab){
        System.out.println(tab+"Nodo No. "+nodo.getId()+". Nivel: "+nodo.getNivel()+" Estudiante: "+((nodo.getEstudiante()!=null) ? nodo.getEstudiante().getNombre() : "-- ESTUDIANTE NO ASIGANDO --"));
        if(nodo.getNodo1()!=null){
            mostrarNodos(nodo.getNodo1(),"   "+tab);
            mostrarNodos(nodo.getNodo2(),"   "+tab);
        }
    }

    public static  void mostrarEstudiantes(){
        for (Estudiante estudiante : estudiantes){
            String estado = estudiante.getEstado().name();
            System.out.println("Nombre: "+estudiante.getNombre()+" , estado: "+estado);
        }
    }

    public static void desconectarNodo(Nodo nodo, int id){

        if(nodo.getId() == id){
            System.out.println("Se desconecto al estudiante: "+nodo.getEstudiante().getNombre()+ " del nodo " + nodo.getId() + " nivel "+nodo.getNivel());
            for(Estudiante estudiante : estudiantes){
                if(estudiante.equals(nodo.getEstudiante())){
                    estudiante.setEstado(DESCONECTADO);
                    break;
                }
            }
            nodo.setEstudiante(null);

            if(nodo.getNodo1()!=null){
                desconectarNodo(nodo.getNodo1(),nodo.getNodo1().getId());
                desconectarNodo(nodo.getNodo2(),nodo.getNodo2().getId());
            }
        }else{
            if(nodo.getNodo1()!=null){
                desconectarNodo(nodo.getNodo1(),id);
                desconectarNodo(nodo.getNodo2(),id);
            }
        }
    }

    public static void reAsignarEstudiantes(Nodo nodo){
        if(nodo.getEstudiante() == null){
            if(indexEstudiante > estudiantes.size() - 1){
                System.out.println("Ya no quedan mas estudiantes para asignar a la llamada");
                return;
            }else{
                Estudiante estudiante = estudiantes.get(indexEstudiante);
                nodo.setEstudiante(estudiante);
                estudiante.setEstado(EN_LLAMADA);
                indexEstudiante++;
                System.out.println("Se asigno el estudiante: "+estudiante.getNombre()+ " al nodo "+nodo.getId()+" nivel "+nodo.getNivel());
            }
        }

        if(nodo.getNodo1()!=null){
            reAsignarEstudiantes(nodo.getNodo1());
            reAsignarEstudiantes(nodo.getNodo2());
        }
    }

    public static void crearEstudiantes(){
        estudiantes.add(new Estudiante(1, "Guillermo Marroquin", EN_ESPERA));
        estudiantes.add(new Estudiante(2, "Mario Roberto Vargas", EN_ESPERA));
        estudiantes.add(new Estudiante(3, "sergio fernando sican patzan", EN_ESPERA));
        estudiantes.add(new Estudiante(4, "Maurizzio de León", EN_ESPERA));
        estudiantes.add(new Estudiante(5, "Edner Aragon", EN_ESPERA));
        estudiantes.add(new Estudiante(6, "Adriel Hernandez", EN_ESPERA));
        estudiantes.add(new Estudiante(7, "Alex Molina", EN_ESPERA));
        estudiantes.add(new Estudiante(8, "Miguel Cajas", EN_ESPERA));
        estudiantes.add(new Estudiante(9, "Ricardo Batz", EN_ESPERA));
        estudiantes.add(new Estudiante(10, "Bany Alvarado", EN_ESPERA));
        estudiantes.add(new Estudiante(11, "Christian Ibarra", EN_ESPERA));
        estudiantes.add(new Estudiante(12, "elmer lacan", EN_ESPERA));
        estudiantes.add(new Estudiante(13, "Omar Jacobo Muñoz Veliz", EN_ESPERA));
        estudiantes.add(new Estudiante(14, "Alejandro Zeceña", EN_ESPERA));
        estudiantes.add(new Estudiante(15, "Ashly Estrada", EN_ESPERA));
        estudiantes.add(new Estudiante(16, "Jonnatan Gomez", EN_ESPERA));
        estudiantes.add(new Estudiante(17, "Victor Ruiz", EN_ESPERA));
        estudiantes.add(new Estudiante(18, "José Pérez", EN_ESPERA));
        estudiantes.add(new Estudiante(19, "Josue Gramajo", EN_ESPERA));
        estudiantes.add(new Estudiante(20, "Kevin Vasquez", EN_ESPERA));
        estudiantes.add(new Estudiante(21, "Manuel Vega", EN_ESPERA));
        estudiantes.add(new Estudiante(22, "Alan Hernandez", EN_ESPERA));
        estudiantes.add(new Estudiante(23, "Ana Lucia Viera Ruiz", EN_ESPERA));
        estudiantes.add(new Estudiante(24, "Axel Quintanilla", EN_ESPERA));
        estudiantes.add(new Estudiante(25, "Julio Rojas", EN_ESPERA));
        estudiantes.add(new Estudiante(26, "Gerson Alexander Castañeda Romero", EN_ESPERA));
        estudiantes.add(new Estudiante(27, "Kabir Gramajo", EN_ESPERA));
        estudiantes.add(new Estudiante(28, "Kevin Hernandez", EN_ESPERA));
        estudiantes.add(new Estudiante(29, "Mario Soto", EN_ESPERA));
        estudiantes.add(new Estudiante(30, "Mildred Ruano", EN_ESPERA));
        estudiantes.add(new Estudiante(31, "Javier Morales", EN_ESPERA));
    }
}
