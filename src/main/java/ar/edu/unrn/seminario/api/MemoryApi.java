package ar.edu.unrn.seminario.api;


import ar.edu.unrn.seminario.dto.*;
import ar.edu.unrn.seminario.exception.StateException;
import ar.edu.unrn.seminario.herramienta.Fecha;
import ar.edu.unrn.seminario.modelo.*;

import java.util.Calendar;
import java.util.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ModeloException.AppException;
import ModeloException.NotNullException;

public class MemoryApi implements IApi{

    private List<Vivienda> viviendas= new ArrayList<>();
    private List<Rol> roles= new ArrayList<>();
    private List<Usuario> usuarios = new ArrayList<>();
    private List<OrdenDeRetiro> ordenDeRetiroList = new ArrayList<>();

    //VIVIENDA

    @Override
    public void registrarVivienda(String nombre,String apellido,String dni,String calle,int numeroCalle,String barrio) throws NotNullException {
        Propietario propietario=new Propietario(nombre,apellido,dni);
        Direccion direccion=new Direccion(calle,numeroCalle,barrio);
        Vivienda vivienda= new Vivienda(propietario, direccion);

        viviendas.add(vivienda);
    }



    @Override
    public List<ViviendaDTO> obtenerViviendas() {
        List<ViviendaDTO> dtos = new ArrayList<>();
        for (Vivienda v : this.viviendas) {
            System.out.println("el dni:"+v.getPropietario().getDni());
            dtos.add(new ViviendaDTO(v.getPropietario(),v.getDireccion()));
        }
        return dtos;
    }
    /*@Override
    public void emilinarVivienda(String direccion) {

    }*/

    @Override
    public PropietarioDTO obtenerPropietario() {

    }

    //ORDEN DE RETIRO
    @Override
    public List<OrdenDeRetiroDTO> obtenerOrdenDeRetiro() throws NotNullException{
        List<OrdenDeRetiroDTO> dtos= new ArrayList<>();
        for (OrdenDeRetiro o : this.ordenDeRetiroList){
            dtos.add(new OrdenDeRetiroDTO(o.getPedido(), o.getFechaOrden(), o.getRecolector(), o.getEstado()));
        }
        return dtos;
    }

    @Override
    public void registrarOrdenDeRetiro(Vivienda vivienda, LocalDate fechaPedido, ArrayList<Residuo> residuos,
                                       boolean vehivulo, String observacion, LocalDate fechaOrden, Recolector recolector,
                                       String estado ) throws NotNullException{
       Pedido pedido= new Pedido(vivienda, fechaPedido, residuos, vehivulo, observacion);

        OrdenDeRetiro ordenDeRetiro= new OrdenDeRetiro( pedido, fechaOrden, recolector, estado);
        ordenDeRetiroList.add(ordenDeRetiro);
    }


    //USUARIOS
   /* @Override
    public void registrarUsuario(String username, String password, String email, String nombre, Integer rol) {

    }
*/
    @Override
    public UsuarioDTO obtenerUsuario(String username) {
        return null;
    }

    @Override
    public void eliminarUsuario(String username) {

    }

    @Override
    public List<UsuarioDTO> obtenerUsuarios() {
        return null;
    }



    //ROLES
    @Override
    public List<RolDTO> obtenerRoles() {
        List<RolDTO> dtos = new ArrayList<>();
        for (Rol r : roles) {
            dtos.add(new RolDTO(r.getNombre(), r.estaActivo()));
        }
        return dtos;
    }

    @Override
    public List<RolDTO> obtenerRolesActivos() {
        return null;
    }

    @Override
    public void guardarRol(String nombre, boolean estado) {

        Rol rol= new Rol(nombre, estado);
        roles.add(rol);

    }

    @Override
    public RolDTO obtenerRolPorCodigo(Integer codigo) {
        return null;
    }

    @Override
    public void activarRol(Integer codigo) {

    }

    @Override
    public void desactivarRol(Integer codigo) {

    }



    @Override
    public ViviendaDTO obtenerVivienda() {
        // TODO Auto-generated method stub
        return null;
    }



    @Override
    public void emilinarVivienda() {
        // TODO Auto-generated method stub

    }
    public void activarUsuario(String usuario) throws StateException {
        for (Usuario u : usuarios) {
            if (u.getNombreUsuario().equals(usuario))
                u.activar();
            //enviar mail
            //..
        }

    }

    @Override
    public void desactivarUsuario(String usuario) throws StateException {
        for (Usuario u : usuarios) {
            if (u.getNombreUsuario().equals(usuario))
                u.desactivar();

        }
    }






    @Override
    public List<ViviendaDTO> obtenerViviendaPorApellido(String apellido) throws SQLException, NotNullException {
        // TODO Auto-generated method stub
        return null;
    }



    @Override
    public boolean ingresarUsuario(String username, String password) throws AppException, NotNullException {
        // TODO Auto-generated method stub
        return false;
    }



    @Override
    public void registrarUsuario(String username, String password, String email, Integer codigoRol) {
        // TODO Auto-generated method stub

    }
}

