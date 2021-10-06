package ar.edu.unrn.seminario.dto;

import ModeloException.NotNullException;
import ar.edu.unrn.seminario.modelo.Direccion;
import ar.edu.unrn.seminario.modelo.Propietario;
import ar.edu.unrn.seminario.modelo.Vivienda;

import java.time.LocalDate;


    public class PedidoDTO {

        private Vivienda vivienda; //se obtiene mediante la direccion
        private LocalDate fecha;
        private int qresiduo;
        private boolean vehículo; // true: se requiere vehículo de carga pesa, tratar con  checkbox en vista
        private String observacion;

        public PedidoDTO(Vivienda vivienda, LocalDate fecha, int qresiduo, boolean vehiculo, String observacion)throws NotNullException {

            if(esDatoNulo(vivienda))
                throw new NotNullException("vivienda");
            if(esDatoNulo(fecha))
                throw new NotNullException("fecha");
            if(esDatoNulo(qresiduo))
                throw new NotNullException("residuo");
            if(esDatoNulo(vehículo))
                throw new NotNullException("vehiculo");
            if (esDatoNulo(observacion))
                throw new NotNullException("observacion");


        }

        private Direccion getDireccion(){
          return this.vivienda.getDireccion();
        }

        private Propietario getPropietario(){
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
    }

