package ar.edu.unrn.seminario.dto;

import ModeloException.NotNullException;

public class ResiduoDTO {
    String tipoResiduo;
    int puntosKilo;
    public ResiduoDTO(String tipoResiduo,int puntosKilo)throws NotNullException{
        super();
        this.tipoResiduo=tipoResiduo;


        this.puntosKilo=puntosKilo;
    }

    public String getTipoResiduo() {
        return this.tipoResiduo;
    }
    public int getPuntosKilo() {
        return this.puntosKilo;
    }

}