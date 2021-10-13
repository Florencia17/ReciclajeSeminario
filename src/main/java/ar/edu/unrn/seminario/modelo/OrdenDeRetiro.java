package ar.edu.unrn.seminario.modelo;

import ModeloException.NotNullException;

import java.time.LocalDate;

public class OrdenDeRetiro {

    private Vivienda vivienda;
    private Pedido pedido;
    private LocalDate fechaOrden;
    private Recolector recolector;
    private String estado;
    //deberia tener una lista de visitas?

    public OrdenDeRetiro(Pedido pedido, LocalDate fechaOrden, Recolector recolector, String estado )throws NotNullException {

        if(esDatoNulo(pedido))
            throw new NotNullException("pedido");
        if(esDatoNulo(fechaOrden))
            throw new NotNullException("fechaOrden");
        if(esDatoNulo(recolector))
            throw new NotNullException("recolector");
        if(esDatoNulo(estado))
            throw new NotNullException("estado");

        this.pedido= pedido;
        this.fechaOrden= fechaOrden;
        this.recolector= recolector;
        this.estado= estado;

    }

    private boolean esDatoNulo(Object dato) {
        return dato == null;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public int getVivienda() {
        return pedido.getNroVivienda();
    }

    public LocalDate getFechaOrden() {
        return fechaOrden;
    }


    public Recolector getRecolector(){
        return recolector;
    }

    public String getEstado() {
        return estado;
    }
}
