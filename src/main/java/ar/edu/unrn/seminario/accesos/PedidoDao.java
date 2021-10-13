package ar.edu.unrn.seminario.accesos;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import ModeloException.NotNullException;
import ar.edu.unrn.seminario.modelo.Pedido;
import ar.edu.unrn.seminario.modelo.Vivienda;

public interface PedidoDao {
    public void create(Vivienda vivienda, Pedido pedido);
    List<Pedido> findAll()throws SQLException, NotNullException;
    public Pedido find(int id_pedido);

}

