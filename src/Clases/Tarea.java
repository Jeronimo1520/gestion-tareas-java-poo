package Clases;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Tarea {
    public final String nombre;
    protected boolean estado;
    public final String fechaInicio;

    static final String TAREA_COLOR ="\u001B[0m";

    static final String COMPLETADA = "Completada";

    static final String EN_CURSO = "En curso";

    static final String ESTADO_TRUE_TAREA = "\u001B[32m";
    static final String ESTADO_FALSE_TAREA = "\u001B[33m";
    public Tarea(String nombre, String fechaInicio) {
        this.nombre = nombre;
        this.estado = false;
        this.fechaInicio = fechaInicio;
    }

    public static void crearTarea(String nombre, Proyecto proyecto) {
        LocalDate fechaInicio = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaInicioStr = fechaInicio.format(formatter);
        Tarea tarea = new Tarea(nombre, fechaInicioStr);
        proyecto.tareas.add(tarea);
    }

    public static void finalizarTarea(Tarea tarea) {
        tarea.estado = true;
        System.out.println("Tarea completada");
    }

    public static void eliminarTarea(Tarea tarea, Proyecto proyecto) {
        proyecto.tareas.remove(tarea);
        System.out.println("La tarea ha sido eliminada exitosamente.");
    }

    public static void mostrarTareas(Proyecto proyecto) {
        System.out.println("\u001B[94m   Proyecto" + proyecto.nombre + TAREA_COLOR);
        for (Tarea i : proyecto.tareas) {
            String estadoTexto = i.estado ? COMPLETADA : EN_CURSO;
            String color = i.estado ? ESTADO_TRUE_TAREA: ESTADO_FALSE_TAREA;

            System.out.printf("         %s - %s%s\u001B[0m - Tarea creada %s %n", i.nombre, color, estadoTexto, i.fechaInicio);
        }
    }

    public static void tareasFinalizadas(Proyecto proyecto) {
        System.out.println("\u001B[96m         Tareas completadas " + proyecto.nombre + TAREA_COLOR);
        for (Tarea j : proyecto.tareas) {
            if (j.estado) {
                String estadoTexto = j.estado ? COMPLETADA : EN_CURSO;
                String color = j.estado ? ESTADO_TRUE_TAREA : ESTADO_FALSE_TAREA;
                System.out.printf("         %s - %s%s\u001B[0m - Tarea creada %s %n", j.nombre, color, estadoTexto, j.fechaInicio);
            }
        }
    }

    public static void tareasEnCurso(Proyecto proyecto) {
        System.out.println("\u001B[96m        Tareas en curso " + proyecto.nombre+ TAREA_COLOR);
        for (Tarea k : proyecto.tareas) {
            if (!k.estado) {
                String estadoTexto = k.estado ? COMPLETADA : EN_CURSO;
                String color = k.estado ? ESTADO_TRUE_TAREA: ESTADO_FALSE_TAREA;
                System.out.printf("         %s - %s%s\u001B[0m - Tarea creada %s %n", k.nombre, color, estadoTexto, k.fechaInicio);
            }
        }
    }
}