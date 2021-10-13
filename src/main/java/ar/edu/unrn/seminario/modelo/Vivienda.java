package ar.edu.unrn.seminario.modelo;


import java.time.LocalDateTime;

import ar.edu.unrn.seminario.dto.DireccionDTO;
import ar.edu.unrn.seminario.dto.PropietarioDTO;


public class Vivienda {

    private Propietario propietario;
    private LocalDateTime fechaRegistro;
    private Direccion direccion;
    private int numeroVivienda;

    public Vivienda(int numeroVivienda,Propietario propietario, Direccion direccion){
        //agregar excepciones
        this.propietario=propietario;

        this.direccion=direccion;
        this.numeroVivienda=numeroVivienda;
    }
    public Vivienda(Propietario propietario, Direccion direccion) {
        this.propietario=propietario;

        this.direccion=direccion;

    }

    public Propietario getPropietario() {
        return propietario;
    }

    public Direccion getDireccion() {
        return direccion;
    }
    public int getNumeroVivienda() {
        return this.numeroVivienda;
    }

    //to string? equals?
}