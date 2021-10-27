package ar.edu.unrn.seminario.api;

import ar.edu.unrn.seminario.accesos.Predicate;
import ar.edu.unrn.seminario.dto.*;
import ar.edu.unrn.seminario.exception.StateException;
import ar.edu.unrn.seminario.modelo.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import ModeloException.AppException;
import ModeloException.NotNullException;

public interface IApi {


    //VIVIENDA

    List<ViviendaDTO> filtradoViviendas(Predicate<ViviendaDTO> predicate) throws SQLException, NotNullException;

    void registrarVivienda(String nombre,String apellido,String dni,String calle,int numeroCalle,String barrio) throws NotNullException, AppException;

    ViviendaDTO obtenerVivienda(int numeroVivienda) throws NotNullException;

    void emilinarVivienda();

    List<ViviendaDTO> obtenerViviendas() throws SQLException, NotNullException; //recupera todas las viviendas

    PropietarioDTO obtenerPropietario();

    //USUARIO
    void registrarUsuario(String username, String password, String email, Integer codigoRol);

    UsuarioDTO obtenerUsuario(String username);

    void eliminarUsuario(String username);

    List<UsuarioDTO> obtenerUsuarios(); // recuperar todos los usuarios

    void activarUsuario(String username) throws StateException ; // recuperar el objeto Usuario, implementar el comportamiento de estado.

    void desactivarUsuario(String username)  throws StateException; // recuperar el objeto Usuario, implementar el comportamiento de estado.

    //ROLES
    List<RolDTO> obtenerRoles();

    List<RolDTO> obtenerRolesActivos();

    void guardarRol(String nombre, boolean estado); // crear el objeto de dominio “Rol”

    RolDTO obtenerRolPorCodigo(Integer codigo); // recuperar el rol almacenado

    void activarRol(Integer codigo); // recuperar el objeto Rol, implementar el comportamiento de estado.

    void desactivarRol(Integer codigo); // recuperar el objeto Rol, imp

    List<ViviendaDTO> obtenerViviendaPorApellido(String apellido) throws SQLException, NotNullException;

    List<ViviendaDTO> obtenerViviendaPorBarrio(String barrio) throws SQLException, NotNullException;

    void registrarPedido(ViviendaDTO viviendaDto,LocalDate fecha, ArrayList<ResiduoDTO>residuosDto,
                         boolean vehiculo, String observacion) throws NotNullException;

    ViviendaDTO obtenerVivienda();

    List<OrdenDeRetiroDTO> obtenerOrdenDeRetiro() throws NotNullException;

    void registrarOrdenDeRetiro(Vivienda vivienda, LocalDate fechaPedido, ArrayList<Residuo> residuos,
                                boolean vehivulo, String observacion, LocalDate fechaOrden, Recolector recolector,
                                String estado ) throws NotNullException;

    List<PedidoDTO> obtenerPedidos()throws SQLException, NotNullException;
}