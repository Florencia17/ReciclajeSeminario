package ar.edu.unrn.seminario.modelo;

import ModeloException.NotNullException;

public class Residuo {
    String tipoResiduo;
    int puntosKilo;
    public Residuo(String tipoResiduo,int puntosKilo)throws NotNullException{

        this.tipoResiduo=tipoResiduo;
        this.puntosKilo=puntosKilo;
    }

    private boolean esDatoVacio(String dato) {
        return dato.equals("");
    }

    private boolean esDatoNulo(Object dato) {
        return dato == null;
    }
    public int getPuntosKilo() {
        return this.puntosKilo;
    }
    public String getTipoResiduo() {
        return this.tipoResiduo;
    }

}