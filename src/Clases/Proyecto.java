package Clases;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Proyecto {

    public final String nombre;
    protected boolean estado;
    protected List<Tarea> tareas;

    public Proyecto(String nombre) {
        this.nombre = nombre;
        this.estado = false;
        this.tareas = new ArrayList<>();
    }

    public static void finalizarProyecto(Proyecto proyecto) {
        for (Tarea tarea : proyecto.tareas) {
            if (!tarea.estado) {
                System.out.println("Hay tareas sin completar");
                return;
            }
        }
        proyecto.estado= true;
        System.out.println("Felicitaciones proyecto completado!!!");
    }

    public static void crearProyecto(String nombre, Usuario usuario) {
        Proyecto proyecto = new Proyecto(nombre);
        usuario.proyectos.add(proyecto);
    }

    public static void eliminarProyecto(Proyecto proyecto, Usuario usuario) {
        usuario.proyectos.remove(proyecto);
        System.out.println("El proyecto ha sido eliminado exitosamente.");
    }

    public static void mostrarProyectos(Usuario usuario) {
        String magenta = "\033[35m";
        String reset = "\033[0m";
        System.out.println(magenta + "          Tus proyectos" + reset);
        for (Proyecto i : usuario.proyectos) {
            String estadoTexto = i.estado ? "Finalizado" : "En curso";
            String color = i.estado ? "\033[32m" : "\033[33m";
            System.out.printf("          %s - %s%s%s%n", i.nombre, color, estadoTexto, reset);
        }
    }
}