package Clases;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class Main {
    static List<Usuario> usuarios = new ArrayList<>();
    private static final String ANSI_RED = "\u001B[91m";
    private static final String ANSI_RESET = "\u001B[0m";

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("**************************************************\n" +
                    "                *                                                *\n" +
                    "                *     BIENVENIDO/A AL PROGRAMA DE  GESTIÓN DE    *\n" +
                    "                *                  TAREAS                       *\n" +
                    "                *                                                *\n" +
                    "                *     Aquí podrás organizar tus tareas con fechas  *\n" +
                    "                *       \n" +
                    "                *                                                 *\n" +
                    "                **************************************************\n" +
                    "                \n" +
                    "                Seleccione una opcion:\n" +
                    "                    1. Crear usuario\n" +
                    "                    2. Iniciar sesion\n" +
                    "                    3. Salir");
            System.out.println("Ingrese su opción: ");
            opcion = input.nextInt();
            switch (opcion) {
                case 1 -> {
                    System.out.println("Nombre de Usuario: ");
                    String nombre = input.next();
                    System.out.println("Contraseña: ");
                    String clave = input.next();
                    Usuario usuario = Usuario.crearUsuario(nombre, clave);
                    usuarios.add(usuario);
                    System.out.println("Usuario creado exitosamente");
                }
                case 2 -> {
                    System.out.print("Ingrese su nombre de usuario: ");
                    String nombreU = input.next();
                    System.out.print("Ingrese su contraseña: ");
                    String contrasena = input.next();
                    for (Usuario usuarioLista : usuarios) {
                        if (nombreU.equals(usuarioLista.nombre) && contrasena.equals(usuarioLista.clave)) {
                            if (usuarioLista.proyectos.isEmpty()) {
                                System.out.println("Ingresa el nombre de un nuevo proyecto: ");
                                String nombreP = input.next();
                                Proyecto.crearProyecto(nombreP, usuarioLista);
                            } else {
                                Proyecto.mostrarProyectos(usuarioLista);
                                System.out.println("Ingrese el nombre del proyecto al que quiere ingresar: ");
                                String proyecto = input.next();
                                for (Proyecto p : usuarioLista.proyectos) {
                                    if (p.nombre.equals(proyecto)) {
                                        int option = 0;
                                        do {
                                            System.out.println("  1. Agregar tarea            2. Ver tareas en curso\n" +
                                                    "                3. Ver tareas completadas   4. Completar proyecto\n" +
                                                    "                5. Agregar proyecto         6.Entrar a una tarea\n" +
                                                    "                7.Eliminar proyecto         8.Atras\n" +
                                                    "                ");
                                            System.out.println("Ingrese su opción: ");
                                            option = input.nextInt();
                                            switch (option) {
                                                case 1:
                                                    System.out.println("Ingrese el nombre de una nueva tarea");
                                                    String nombreT = input.next();
                                                    Tarea.crearTarea(nombreT, p);
                                                    System.out.print("Tarea creada exitosamente");
                                                    break;
                                                case 2:
                                                    Tarea.tareasEnCurso(p);
                                                    break;
                                                case 3:
                                                    Tarea.tareasFinalizadas(p);
                                                    break;
                                                case 4:
                                                    Proyecto.finalizarProyecto(p);
                                                    break;
                                                case 5:
                                                    System.out.print("Ingresa el nombre del nuevo proyecto: ");
                                                    String nombreProyecto = input.next();
                                                    Proyecto.crearProyecto(nombreProyecto, usuarioLista);
                                                    System.out.println("proyecto creado correctamente ");
                                                    break;
                                                case 6:
                                                    System.out.println( "Ingrese el nombre de la tarea a la que quiere ingresar: ");
                                                    String tarea = input.next();
                                                    for (Tarea t: p.tareas){
                                                        if (t.nombre.equals(tarea)){
                                                            int op = 0;
                                                            do {
                                                                System.out.println(ANSI_RED + "    " + t.nombre +ANSI_RESET);
                                                                System.out.println("1. Eliminar tarea \n" +
                                                                        "2. Marcar como completada\n" +
                                                                        "3. Atrás");
                                                                System.out.println("Opción: ");
                                                                op = input.nextInt();
                                                                switch (op) {
                                                                    case 1 -> Tarea.eliminarTarea(t, p);
                                                                    case 2 -> Tarea.finalizarTarea(t);
                                                                }
                                                            }while (op !=3);
                                                        }
                                                    }
                                                    break;
                                                case 7:
                                                    Proyecto.eliminarProyecto(p,usuarioLista);
                                                    break;
                                                case 8:
                                                    System.out.println("Volviendo atras...");
                                                    break;
                                                default:
                                                    System.out.println("Opción inválida, intente de nuevo.");
                                                    break;
                                            }
                                        } while (option != 8);
                                    } else {
                                        System.out.println("El proyecto al que quieres ingresar no existe");
                                    }
                                }
                            }
                        }
                    }
                }
                case 3 -> System.out.println("Saliendo");
                default -> System.out.println("Opción inválida, intente de nuevo.");
            }
        }while (opcion !=3);
    }
}