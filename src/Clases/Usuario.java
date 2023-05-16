package Clases;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

    public final String nombre;
    public final String clave;
    public final List<Proyecto> proyectos;

    public Usuario(String nombre, String clave) {
        this.nombre = nombre;
        this.clave = clave;
        this.proyectos = new ArrayList<>();
    }

    public static Usuario crearUsuario(String nombre, String clave) {
        return new Usuario(nombre, clave);
    }
}