package ar.edu.unrn.seminario.dto;

import ModeloException.NotNullException;

public class DireccionDTO {
    private String calle;
    private int numero;
    private String barrio;
    private int idDireccion;


    public DireccionDTO(String calle, int numero, String barrio,int idDireccion) throws NotNullException{

        if(esDatoNulo(calle)) {
            throw new NotNullException("la calle es dato nulo");
        }
        if(esDatoNuloInt(numero)) {
            throw new NotNullException("el numero es dato nulo");
        }
        if(esDatoNulo(barrio)) {
            throw new NotNullException("el barrio es dato nulo");
        }
        this.calle=calle;
        this.numero=numero;
        this.barrio=barrio;
        this.idDireccion=idDireccion;
    }
    public DireccionDTO(String calle, int numero, String barrio) throws NotNullException{

        if(esDatoNulo(calle)) {
            throw new NotNullException("la calle es dato nulo");
        }
        if(esDatoNuloInt(numero)) {
            throw new NotNullException("el numero es dato nulo");
        }
        if(esDatoNulo(barrio)) {
            throw new NotNullException("el barrio es dato nulo");
        }
        this.calle=calle;
        this.numero=numero;
        this.barrio=barrio;
    }
    public String getCalle() {
        return calle;
    }

    public int getNumero() {
        return numero;
    }
    private boolean esDatoNuloInt(int dato) {
        if(dato==0) {
            return true;
        }
        else {
            return false;
        }
    }

    public String getBarrio() {
        return barrio;
    }
    private boolean esDatoNulo(String dato) {
        return dato == null | dato.isEmpty();
    }
    public int getIdDireccion() {
        return this.idDireccion;
    }
}
