package ar.edu.unrn.seminario.accesos;
import ar.edu.unrn.seminario.dto.ViviendaDTO;
import ar.edu.unrn.seminario.modelo.Direccion;
import ar.edu.unrn.seminario.modelo.Pedido;
import ar.edu.unrn.seminario.modelo.Propietario;
import ar.edu.unrn.seminario.modelo.Residuo;
import ar.edu.unrn.seminario.modelo.Vivienda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ModeloException.NotNullException;

public class PedidoDAOJDBC implements PedidoDao{



    public void crearResiduo(ArrayList<Residuo> residuos) {
        try {
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement statement=null;
            for(Residuo r: residuos) {


                statement =conn.prepareStatement("INSERT INTO Residuo(puntos_kilo,tipo_residuo) "//falta id_residuos,numero_vivienda
                        + "VALUES (?,?)");
                statement.setInt(1, r.getPuntosKilo());
                statement.setString(2, r.getTipoResiduo());


            }


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
            e.getMessage();
            e.printStackTrace();
            // TODO: disparar Exception propia
        } finally {
            ConnectionManager.disconnect();
        }
    }




    public void create(Vivienda vivienda,Pedido pedido) {
        int ultimoValor=0;

        crearResiduo(pedido.getResiduos());
        try {

            Connection conn = ConnectionManager.getConnection();
            PreparedStatement statement = conn
                    .prepareStatement("INSERT INTO Pedido_retiro(fecha_del_pedido,observacion,vehiculo_pesado,numero_vivienda,id_residuos) "//falta id_residuos,numero_vivienda
                            + "VALUES (?,?,?,?,?)");


            statement.setObject(1, pedido.getFecha());
            statement.setObject(2, pedido.getObservacion());
            statement.setObject(3, pedido.getVehiculo());
            statement.setObject(4, vivienda.getNumeroVivienda());


            ResultSet rs=statement.executeQuery("SELECT MAX(id_residuo) AS id_residuo FROM Residuo");
            if(rs.next()){
                ultimoValor = rs.getInt("id_residuo");
                statement.setInt(5, ultimoValor);
            }
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
            e.getMessage();
            e.printStackTrace();
            // TODO: disparar Exception propia
        } finally {
            ConnectionManager.disconnect();
        }

    }




    @Override
    public List<Pedido> findAll() throws SQLException, NotNullException{
        List<Pedido> pedidos = new ArrayList<Pedido>();
        Statement sentencia = null;
        ResultSet rs = null;
        try {

            sentencia = ConnectionManager.getConnection().createStatement();

            rs = sentencia.executeQuery("SELECT p.fecha_del_pedido, p.observacion, p.vehiculo_pesado, r.id_residuo as id_residuo, v.numero_vivienda as numero_vivienda" +
                    "FROM pedido p JOIN residuo r ON (p.id_residuo = r.id_residuo)" + "FROM pedido p join vivienda v on (p.numero_vivienda = v.numero_vivienda)");


            while (rs.next()) {
                Direccion direccion =new Direccion(rs.getString("barrio"),rs.getInt("numero"),rs.getString("calle"));

                //String nombre, String apellido, String dni
                Propietario propietario = new Propietario(rs.getString("nombre"),rs.getString("apellido"),rs.getString("dni") );

                //int numeroVivienda,Propietario propietario, Direccion direccion
                Vivienda vivienda = new Vivienda(rs.getInt("numero_vivienda"), propietario, direccion);

                //String tipoResiduo,int puntosKilo
                Residuo residuo = new Residuo(rs.getString("tipo_residuo"), rs.getInt("puntos_kilo"));
                //Vivienda vivienda, LocalDate fecha, ArrayList<Residuo> residuos, boolean vehiculo, String observacion
                ArrayList<Residuo> residuos = new ArrayList<Residuo>();
                residuos.add(residuo);

                //Vivienda vivienda, LocalDate fecha, ArrayList<Residuo> residuos, boolean vehiculo, String observacion
                //LocalTime por Date en fecha
                Pedido pedido = new Pedido(vivienda, rs.getLocalDate("fecha"),residuos, rs.getBoolean("vehiculo_pesado"), rs.getString("observacion"));

                pedidos.add(pedido);
            }
        }catch (SQLException e) {
            System.out.println("Error de mySql\n" + e.toString());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.disconnect();
        }


        return pedidos;
    }




    @Override
    public Pedido find(int id_pedido) {//fecha_del_pedido,observacion,vehiculo_pesado,numero_vivienda,id_residuos

        Pedido pedido=null;
        try {
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement statement = conn.prepareStatement(
                    "SELECT p.fecha,p.observacion,p.vehiculo_pesado" +
                            "SELECT * FROM pedido p join vivienda v on (p.numero_vivienda = v.numero_vivienda) where p.id_pedido=?"+
                            "SELECT * FROM pedidos p join residuo r on (p.id_residuo = r.id_residuo) where p.id_pedido=?");


            statement.setInt(1, id_pedido);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Direccion direccion =new Direccion(rs.getString("barrio"),rs.getInt("numero"),rs.getString("calle"));

                //String nombre, String apellido, String dni
                Propietario propietario = new Propietario(rs.getString("nombre"),rs.getString("apellido"),rs.getString("dni") );

                //int numeroVivienda,Propietario propietario, Direccion direccion
                Vivienda vivienda = new Vivienda(rs.getInt("numero_vivienda"), propietario, direccion);

                //String tipoResiduo,int puntosKilo
                Residuo residuo = new Residuo(rs.getString("tipo_residuo"), rs.getInt("puntos_kilo"));
                //Vivienda vivienda, LocalDate fecha, ArrayList<Residuo> residuos, boolean vehiculo, String observacion
                ArrayList<Residuo> residuos = new ArrayList<Residuo>();
                residuos.add(residuo);

                //Vivienda vivienda, LocalDate fecha, ArrayList<Residuo> residuos, boolean vehiculo, String observacion
                //LocalTime por Date en fecha
                pedido = new Pedido(vivienda, rs.getDate("fecha"),residuos, rs.getBoolean("vehiculo_pesado"), rs.getString("observacion"));


            }

        } catch (SQLException e) {
            System.out.println("Error al procesar consulta");
            // TODO: disparar Exception propia
            // throw new AppException(e, e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            // TODO: disparar Exception propia
            // throw new AppException(e, e.getCause().getMessage(), e.getMessage());
        } finally {
            ConnectionManager.disconnect();
        }

        return pedido;
    }
}
