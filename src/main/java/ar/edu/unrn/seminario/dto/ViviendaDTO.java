package ar.edu.unrn.seminario.dto;

import java.time.LocalDateTime;

import java.util.Calendar;
import java.util.Date;


import ar.edu.unrn.seminario.herramienta.Fecha;
import ar.edu.unrn.seminario.modelo.Direccion;
import ar.edu.unrn.seminario.modelo.Propietario;

public class ViviendaDTO {

    private Propietario propietario;
    private Direccion direccion;
    private LocalDateTime fechaRegistro;

    public ViviendaDTO(Propietario propietario, Direccion direccion){
        super(); //de que?
        this.propietario= propietario;
        this.direccion=direccion;

    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public Direccion getDireccion() {
        return direccion;
    }


    //es necesario????????
    public String getCalle(){
        return this.direccion.getCalle();
    }

    public int getNumero(){
        return this.direccion.getNumero();
    }

    public String getBarrio(){
        return this.direccion.getBarrio();
    }

    public String getNombrePropietario(){
        return this.propietario.getNombre();
    }

    public String getApellidoPropietario(){
        return this.propietario.getApellido();
    }

    public String getDniPropietario(){
        return this.propietario.getDni();
    }



}
