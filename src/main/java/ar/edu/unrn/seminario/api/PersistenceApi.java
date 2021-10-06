package ar.edu.unrn.seminario.api;


import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.*;

import ModeloException.AppException;
import ModeloException.NotNullException;
import ar.edu.unrn.seminario.accesos.DireccionDAOJDBC;
import ar.edu.unrn.seminario.accesos.PropietarioDAOJDBC;

import ar.edu.unrn.seminario.accesos.RolDAOJDBC;
import ar.edu.unrn.seminario.accesos.RolDao;
import ar.edu.unrn.seminario.accesos.UsuarioDAOJDBC;
import ar.edu.unrn.seminario.accesos.UsuarioDao;
import ar.edu.unrn.seminario.accesos.ViviendaDAOJDBC;
import ar.edu.unrn.seminario.dto.RolDTO;
import ar.edu.unrn.seminario.dto.UsuarioDTO;
import ar.edu.unrn.seminario.dto.ViviendaDTO;
import ar.edu.unrn.seminario.herramienta.Fecha;
import ar.edu.unrn.seminario.modelo.Direccion;
import ar.edu.unrn.seminario.modelo.Propietario;
import ar.edu.unrn.seminario.modelo.Rol;
import ar.edu.unrn.seminario.modelo.Usuario;
import ar.edu.unrn.seminario.modelo.Vivienda;

public class PersistenceApi implements IApi {

    private RolDao rolDao;
    private UsuarioDao usuarioDao;
    private PropietarioDAOJDBC propietarioDao;
    private DireccionDAOJDBC direccionDao;
    private ViviendaDAOJDBC	viviendaDao;

    public PersistenceApi() {
        propietarioDao=new PropietarioDAOJDBC();
        direccionDao=new DireccionDAOJDBC();
        viviendaDao=new ViviendaDAOJDBC();
        rolDao = new RolDAOJDBC();
        usuarioDao = new UsuarioDAOJDBC();
    }




    @Override
    public void registrarVivienda(String nombre,String apellido,String dni,String calle,int numeroCalle,String barrio) throws NotNullException, AppException {

        Propietario propietario=new Propietario(nombre,apellido,dni);
        Direccion direccion=new Direccion(calle,numeroCalle,barrio);

        Vivienda vivienda=new Vivienda(propietario, direccion);
        this.viviendaDao.create(vivienda,this.propietarioDao,this.direccionDao);

    }

    @Override
    public void registrarPedido(String calle, int numero, String barrio, LocalDate fecha, int qresiduo,
                                boolean vehículo, String observacion) {

    }


    @Override
    public boolean ingresarUsuario(String username,String password) throws AppException,NotNullException {
        boolean existe=false;

        existe=usuarioDao.iniciarSesion(username,password);


        return existe;

    }




    @Override
    public void registrarUsuario(String username, String password, String email, Integer codigoRol) {
        Rol rol = rolDao.find(codigoRol);
        Usuario usuario = new Usuario(username, password, email, rol);
        this.usuarioDao.create(usuario);

    }

    @Override
    public List<UsuarioDTO> obtenerUsuarios() {
        List<UsuarioDTO> dtos = new ArrayList<>();
        List<Usuario> usuarios = usuarioDao.findAll();
        for (Usuario u : usuarios) {
            dtos.add(new UsuarioDTO(u.getNombreUsuario(), u.getContrasenia(), u.getEmail(),
                    u.getRol(), u.estaActivo()));
        }
        return dtos;

    }


    @Override
    public List<ViviendaDTO> obtenerViviendas() throws SQLException, NotNullException {
        List<ViviendaDTO> dtos = new ArrayList<>();
        List<Vivienda>viviendas =viviendaDao.findAll();
        for (Vivienda v : viviendas) {

            dtos.add(new ViviendaDTO(v.getPropietario(),v.getDireccion()));
        }
        return dtos;
    }

    @Override
    public UsuarioDTO obtenerUsuario(String username) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void eliminarUsuario(String username) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<RolDTO> obtenerRoles() {
        List<Rol> roles = rolDao.findAll();
        List<RolDTO> rolesDTO = new ArrayList<>(0);
        for (Rol rol : roles) {
            rolesDTO.add(new RolDTO( rol.getNombre(),rol.getCodigo() ,rol.estaActivo()));
        }
        return rolesDTO;

    }

    @Override
    public List<RolDTO> obtenerRolesActivos() {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public RolDTO obtenerRolPorCodigo(Integer codigo) {
        Rol rol = rolDao.find(codigo);
        RolDTO rolDTO = new RolDTO(rol.getNombre(),rol.getCodigo() ,rol.estaActivo());
        return rolDTO;

    }

    @Override
    public void activarRol(Integer codigo) {
        // TODO Auto-generated method stub

    }

    @Override
    public void desactivarRol(Integer codigo) {
        // TODO Auto-generated method stub

    }

    @Override
    public void activarUsuario(String username) {
        // TODO Auto-generated method stub

    }

    @Override
    public void desactivarUsuario(String username) {
        // TODO Auto-generated method stub

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





    @Override
    public void guardarRol(String nombre, boolean estado) {
        Rol rol=new Rol(nombre,estado);
        this.rolDao.create(rol);

    }

    @Override
    public List<ViviendaDTO> obtenerFiltradoApellido(String apellido) throws SQLException, NotNullException {

        List<Vivienda>viviendas =viviendaDao.findAll();
        List<ViviendaDTO>viviendaDto=viviendas.stream().filter(v->apellido.isEmpty() || v.getPropietario().getApellido().equals(apellido))
                .map((v)->new ViviendaDTO(v.getPropietario(),v.getDireccion())).collect(Collectors.toList());

        return viviendaDto;
    }








}
