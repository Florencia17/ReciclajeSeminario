package ar.edu.unrn.seminario.modelo;

//fecha del pedido, residuos a retirar, si se requiere vehículo
//de carga pesada y una observación

//fecha del pedido, residuos a retirar, si se requiere vehículo
//de carga pesada y una observación

import ModeloException.DataEmptyException;
import ModeloException.NotNullException;
import ar.edu.unrn.seminario.herramienta.Fecha;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Pedido {

    private Vivienda vivienda; //se obtiene mediante el numero de vivienda
    private LocalDate fecha;
    private ArrayList <Residuo> residuos=new ArrayList<Residuo>();
    private boolean vehículo; // true: se requiere vehículo de carga pesa, tratar con  checkbox en vista
    private String observacion;
    private int idPedidoRetiro;

    public Pedido(Vivienda vivienda, LocalDate fecha, ArrayList<Residuo> residuos, boolean vehiculo, String observacion)throws NotNullException {

        if(esDatoNulo(vivienda))
            throw new NotNullException("vivienda");
        else
            this.vivienda=vivienda;
        if(esDatoNulo(fecha))
            throw new NotNullException("fecha");
        else
            this.fecha=fecha;
        if(esDatoNulo(residuos))
            throw new NotNullException("residuo");
        else
            this.residuos=residuos;

        if(esDatoNulo(vehiculo))
            throw new NotNullException("vehiculo");
        else
            this.vehículo=vehiculo;
        if (esDatoNulo(observacion))
            throw new NotNullException("observacion");
        else
            this.observacion=observacion;


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

    public ArrayList<Residuo> getResiduos() {
        return this.residuos;
    }

    public String getObservacion(){
        return observacion;
    }
    public boolean getVehiculo() {
        return this.vehículo;
    }

    public Vivienda getVivienda() {
        return vivienda;
    }

    public int getNroVivienda(){
        return vivienda.getNumeroVivienda();
    }
}

