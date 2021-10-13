package ar.edu.unrn.seminario.dto;
import ModeloException.NotNullException;
import ar.edu.unrn.seminario.modelo.Direccion;
import ar.edu.unrn.seminario.modelo.Propietario;
import ar.edu.unrn.seminario.modelo.Vivienda;

import java.time.LocalDate;
import java.util.Date;


public class PedidoDTO {

    private ViviendaDTO vivienda; //se obtiene mediante la direccion
    private LocalDate fecha;
    private int qresiduo;
    private boolean vehiculo; // true: se requiere vehiculo de carga pesa, tratar con  checkbox en vista
    private String observacion;
    private int nro_pedido;


    public PedidoDTO(ViviendaDTO vivienda, LocalDate fecha, int qresiduo, boolean vehiculo, String observacion, int nro_pedido)throws NotNullException {

        if(esDatoNulo(vivienda))
            throw new NotNullException("id_vivienda");
        if(esDatoNulo(fecha))
            throw new NotNullException("fecha");
        if(esDatoNulo(qresiduo))
            throw new NotNullException("residuo");
        if(esDatoNulo(vehiculo))
            throw new NotNullException("vehiculo");
        if (esDatoNulo(observacion))
            throw new NotNullException("observacion");
        if(esDatoNulo(nro_pedido))
            throw new NotNullException("nro_pedido");


    }


    private DireccionDTO getDireccion(){
        return this.vivienda.getDireccion();
    }

    private PropietarioDTO getPropietario(){
        return this.vivienda.getPropietario();
    }



    private boolean esDatoVacio(String dato) {
        return dato.equals("");
    }


    private boolean esDatoNulo(Object dato) {
        return dato == null;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public int getResiduo() {
        return qresiduo;
    }

    public String getObservacion(){
        return observacion;
    }

    public int getNumeroPedido() {
        return this.nro_pedido;
    }

    public ViviendaDTO obtenerVivienda(){
        return vivienda;
    }

    public boolean getVehiculo() {
        return vehiculo;
    }


}
