package ar.edu.unrn.seminario.accesos;

import ar.edu.unrn.seminario.modelo.Vivienda;

import java.time.LocalDate;

public interface PedidoDao {

    void create(ViviendaDao viviendaDao, LocalDate fecha, int qresiduo, boolean vehiculo, String observacion);
}
