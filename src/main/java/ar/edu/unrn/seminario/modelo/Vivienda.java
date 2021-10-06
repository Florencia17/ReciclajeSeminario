package ar.edu.unrn.seminario.modelo;

import ModeloException.NotNullException;

import java.time.LocalDateTime;


public class Vivienda {

    private Propietario propietario;
    private LocalDateTime fechaRegistro;
    private Direccion direccion;

    public Vivienda(Propietario propietario, Direccion direccion) throws NotNullException {
        if(esDatoNulo(propietario))
            throw new NotNullException("propietario");
        if (esDatoNulo(direccion))
            throw new NotNullException("Direccion");

        this.propietario=propietario;
        this.direccion=direccion;
    }

    private boolean esDatoVacio(String dato) {
        return dato.equals("");
    }

    private boolean esDatoNulo(Object dato) {
        return dato == null;
    }
    public Propietario getPropietario() {
        return propietario;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    //to string? equals?
}
