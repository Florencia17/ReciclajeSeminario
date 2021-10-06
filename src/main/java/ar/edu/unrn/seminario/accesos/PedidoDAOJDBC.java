package ar.edu.unrn.seminario.accesos;

import ar.edu.unrn.seminario.modelo.Propietario;
import ar.edu.unrn.seminario.modelo.Vivienda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class PedidoDAOJDBC implements PedidoDao{

    public void create(ViviendaDao viviendadao, LocalDate fecha, int qresiduo, boolean vehiculo, String observacion) {
        try {

            Connection conn = ConnectionManager.getConnection();
            PreparedStatement statement = conn
                    .prepareStatement("INSERT INTO Propietario(nombre, apellido, dni) "
                            + "VALUES (?, ?, ?)");



            statement.setString(1, );
            statement.setString(2, );
            statement.setString(3, );



            int cantidad = statement.executeUpdate();
            if (cantidad > 0) {
                // System.out.println("Modificando " + cantidad + " registros");
            } else {
                System.out.println("Error al actualizar");
                // TODO: disparar Exception propia
            }

        } catch (SQLException e) {
            System.out.println("Error al procesar consulta");
            e.printStackTrace();
            // TODO: disparar Exception propia
        } catch (Exception e) {
            System.out.println("Error al insertar un pedido");
            // TODO: disparar Exception propia
        } finally {
            ConnectionManager.disconnect();
        }

    }
}
