package ar.edu.unrn.seminario.modelo;

import java.util.ArrayList;

public class Recolector {
    private String nombre;
    private String apellido;
    private String email;
    private String dni;
    private String turno;
    private String legajo;
    private ArrayList<Visita> visitasDelRecolector = new ArrayList<Visita>();

    public Recolector(String nombre, String apellido, String email, String dni, String legajo) {

        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.dni = dni;
        this.legajo = legajo;
    }

    public void agregarVisita(Visita nueva) {
        this.visitasDelRecolector.add(nueva);
    }

    public String obtenerLegajo() {
        return this.legajo;
    }

    public void cambiarNombre(String nombre) {
        this.nombre = nombre;
    }

    public String obtenerNombre() {
        return this.nombre;
    }

    public void cambiarApellido(String apellido) {
        this.apellido = apellido;
    }

    public String obtenerApellido() {

        return this.apellido;
    }

    public void cambiarEmail(String email) {
        this.email = email;
    }

    public String obtenerEmail() {
        return this.email;
    }

    public void cambiarTurno(String turno) {
        this.turno = turno;
    }

    public String obtenerTurno() {
        return this.turno;
    }
    public String obtenerDni() {
        return this.dni;
    }

    // Equals-> legajo

}
