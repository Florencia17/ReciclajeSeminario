package ar.edu.unrn.seminario.modelo;

import java.time.LocalDateTime;
import java.util.Date;

import ModeloException.NotNullException;

public class Propietario {

    private String nombre;
    private String apellido;
    private String dni;
    private Usuario usuario;
    private int puntosAcumulados;
    //private PedidoRetiro pedidoRetiro;

    public Propietario(String nombre, String apellido, String dni) throws  NotNullException{
        //agregar expeciones
        if (esDatoNulo(nombre))
            throw new NotNullException("nombre");
        if (esDatoNulo(apellido))
            throw new NotNullException("apellido");
        if (esDatoNulo(dni))
            throw new NotNullException("dni");
        this.apellido= apellido;
        this.dni= dni;
        this.nombre= nombre;



    }
    private boolean esDatoNulo(String dato) {
        return dato == null | dato.isEmpty();
    }
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDni() {
        return dni;
    }

}
