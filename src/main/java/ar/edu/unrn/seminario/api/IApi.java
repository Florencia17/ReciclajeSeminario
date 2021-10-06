package ar.edu.unrn.seminario.api;

import ar.edu.unrn.seminario.dto.RolDTO;
import ar.edu.unrn.seminario.dto.UsuarioDTO;
import ar.edu.unrn.seminario.dto.ViviendaDTO;
import ar.edu.unrn.seminario.exception.StateException;
import ar.edu.unrn.seminario.herramienta.Fecha;
import ar.edu.unrn.seminario.modelo.Direccion;
import ar.edu.unrn.seminario.modelo.Propietario;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import ModeloException.AppException;
import ModeloException.NotNullException;

public interface IApi {


    //VIVIENDA

    void registrarVivienda(String nombre,String apellido,String dni,String calle,int numeroCalle,String barrio) throws NotNullException, AppException;

    public ViviendaDTO obtenerVivienda();

    void emilinarVivienda();

    public List<ViviendaDTO> obtenerViviendas() throws SQLException, NotNullException; //recupera todas las viviendas

    public boolean ingresarUsuario(String username,String password) throws AppException, NotNullException;

    //PEDIDO

    void registrarPedido(String calle, int numero, String barrio, LocalDate fecha, int qresiduo,
                         boolean vehículo, String observacion) throws NotNullException;




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

    List<ViviendaDTO>obtenerFiltradoApellido(String apellido) throws SQLException, NotNullException;




}
