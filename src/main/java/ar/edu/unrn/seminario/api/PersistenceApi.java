package ar.edu.unrn.seminario.api;


import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.*;

import ModeloException.AppException;
import ModeloException.NotNullException;
import ar.edu.unrn.seminario.accesos.DireccionDAOJDBC;
import ar.edu.unrn.seminario.accesos.LoginUsuarioDAOJDBC;
import ar.edu.unrn.seminario.accesos.LoginUsuarioDao;
import ar.edu.unrn.seminario.accesos.PedidoDAOJDBC;
import ar.edu.unrn.seminario.accesos.PedidoDao;
import ar.edu.unrn.seminario.accesos.PropietarioDAOJDBC;

import ar.edu.unrn.seminario.accesos.RolDAOJDBC;
import ar.edu.unrn.seminario.accesos.RolDao;
import ar.edu.unrn.seminario.accesos.UsuarioDAOJDBC;
import ar.edu.unrn.seminario.accesos.UsuarioDao;
import ar.edu.unrn.seminario.accesos.ViviendaDAOJDBC;
import ar.edu.unrn.seminario.dto.DireccionDTO;
import ar.edu.unrn.seminario.dto.PedidoDTO;
import ar.edu.unrn.seminario.dto.PropietarioDTO;
import ar.edu.unrn.seminario.dto.ResiduoDTO;
import ar.edu.unrn.seminario.dto.RolDTO;
import ar.edu.unrn.seminario.dto.UsuarioDTO;
import ar.edu.unrn.seminario.dto.ViviendaDTO;
import ar.edu.unrn.seminario.modelo.Direccion;
import ar.edu.unrn.seminario.modelo.Pedido;
import ar.edu.unrn.seminario.modelo.Propietario;
import ar.edu.unrn.seminario.modelo.Residuo;
import ar.edu.unrn.seminario.modelo.Rol;
import ar.edu.unrn.seminario.modelo.Usuario;
import ar.edu.unrn.seminario.modelo.Vivienda;

public class PersistenceApi implements IApi {

    private RolDao rolDao;
    private UsuarioDao usuarioDao;
    private PropietarioDAOJDBC propietarioDao;
    private DireccionDAOJDBC direccionDao;
    private ViviendaDAOJDBC	viviendaDao;

    private PedidoDao pedidoDao;

    public PersistenceApi() {
        propietarioDao=new PropietarioDAOJDBC();
        direccionDao=new DireccionDAOJDBC();
        viviendaDao=new ViviendaDAOJDBC();
        rolDao = new RolDAOJDBC();
        usuarioDao = new UsuarioDAOJDBC();

        pedidoDao=new PedidoDAOJDBC();
    }




    @Override
    public void registrarVivienda(String nombre,String apellido,String dni,String calle,int numeroCalle,String barrio) throws NotNullException, AppException {

        Propietario propietario=new Propietario(nombre,apellido,dni);
        Direccion direccion=new Direccion(calle,numeroCalle,barrio);

        Vivienda vivienda=new Vivienda(propietario, direccion);
        this.viviendaDao.create(vivienda,this.propietarioDao,this.direccionDao);


    }
  /*  @Override
    public boolean ingresarUsuario(String username,String password) throws AppException,NotNullException {
        boolean existe=false;

        existe=loginDao.iniciarSesion(username,password);


        return existe;

    }*/
    @Override
    public void registrarUsuario(String username, String password, String email, Integer codigoRol) {
        Rol rol = rolDao.find(codigoRol);
        RolDTO rolDto=new RolDTO(rol);
        Usuario usuario = new Usuario(username, password, email, rolDto);
        this.usuarioDao.create(usuario);

    }

    //ViviendaDTO viviendaDto,Date fecha, ArrayList<ResiduoDTO>residuosDto,boolean vehiculo,String observacion

	/*
	 * @Override
    public void registrarPedido(ViviendaDTO viviendaDto,Date fecha, ArrayList<ResiduoDTO>residuosDto,
                                boolean vehiculo, String observacion) throws NotNullException {

    	Propietario propietario=new Propietario(viviendaDto.getNombrePropietario(),viviendaDto.getApellidopropietarioDto(),viviendaDto.getDnipropietarioDto());
    	Direccion direccion=new Direccion(viviendaDto.getCalle(),viviendaDto.getNumero(),viviendaDto.getBarrio(),viviendaDto.getDireccion().getIdDireccion());
    	Vivienda vivienda=new Vivienda(viviendaDto.getNumeroVivienda(),propietario,direccion);
    	ArrayList<Residuo>residuos=new ArrayList<>();
    	for(ResiduoDTO dto: residuosDto) {

    			residuos.add(new Residuo(dto.getTipoResiduo(),dto.getPuntosKilo()));

    	}
    	//(Vivienda vivienda, Date fecha, ArrayList<Residuo> residuos, boolean vehiculo, String observacion
    	Pedido pedido=new Pedido(vivienda,fecha,residuos,vehiculo,observacion);

    	this.pedidoDao.create(vivienda, pedido);
    }
	 */

    //List<ViviendaDTO> obtenerViviendas()
    public List<PedidoDTO> obtenerPedidos() throws SQLException, NotNullException{
        List<PedidoDTO> dtos = new ArrayList<>();
        List<Pedido> pedidos = pedidoDao.findAll();

        for(Pedido p : pedidos) {
            //String nombre, String apellido, String dni
            PropietarioDTO propietario = new PropietarioDTO(p.getVivienda().getPropietario().getNombre(),
                    p.getVivienda().getPropietario().getApellido(),
                    p.getVivienda().getPropietario().getDni());

            //String calle, int numero, String barrio,int idDireccion
            DireccionDTO direccion= new DireccionDTO(p.getVivienda().getDireccion().getCalle(),
                    p.getVivienda().getDireccion().getNumero(),
                    p.getVivienda().getDireccion().getBarrio(),
                    p.getVivienda().getDireccion().getIdDireccion());

            //int numeroVivienda,PropietarioDTO propietarioDto, DireccionDTO direccionDto
            ViviendaDTO vivienda= new ViviendaDTO(p.getVivienda().getNumeroVivienda(),propietario, direccion);



            //ViviendaDTO vivienda, LocalDate fecha, int qresiduo, boolean vehiculo, String observacion, int nro_pedido
            dtos.add(new PedidoDTO(vivienda, p.getFecha(), p.getResiduos().size(),
                    p.getVehiculo(),p.getObservacion(),p.getidPedidoRetiro()));
        }

        return dtos;
    }

    public ViviendaDTO obtenerVivienda(int numeroVivienda) throws NotNullException {

        Vivienda vivienda=this.viviendaDao.find(numeroVivienda);
        PropietarioDTO propietarioDto=new PropietarioDTO(vivienda.getPropietario().getNombre(),vivienda.getPropietario().getApellido(),vivienda.getPropietario().getDni());
        DireccionDTO direccionDto=new DireccionDTO (vivienda.getDireccion().getCalle(),vivienda.getDireccion().getNumero(),vivienda.getDireccion().getBarrio());
        ViviendaDTO viviendaDto=new ViviendaDTO(vivienda.getNumeroVivienda(),propietarioDto,direccionDto);
        return viviendaDto;
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
            PropietarioDTO propietarioDto=new PropietarioDTO(v.getPropietario().getNombre(),v.getPropietario().getApellido(),v.getPropietario().getDni());
            DireccionDTO direccionDto=new DireccionDTO (v.getDireccion().getCalle(),v.getDireccion().getNumero(),v.getDireccion().getBarrio());
            dtos.add(new ViviendaDTO(v.getNumeroVivienda(),propietarioDto,direccionDto));
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
                .map((v)->{
                            ViviendaDTO viviendaDtoc=null;
                            try {
                                viviendaDtoc= new ViviendaDTO(new PropietarioDTO(v.getPropietario().getNombre(),v.getPropietario().getApellido(),v.getPropietario().getDni()),
                                        new DireccionDTO(v.getDireccion().getCalle(),v.getDireccion().getNumero(),v.getDireccion().getBarrio()));
                            } catch (NotNullException e) {

                                e.getMessage();
                            }
                            return viviendaDtoc;

                        }



                ).collect(Collectors.toList());






        return viviendaDto;
    }



//Vivienda vivienda, Date fecha2, ArrayList<Residuo> residuos, boolean vehiculo, String observacion


    @Override
    public void registrarPedido(ViviendaDTO viviendaDto, LocalDate fecha, ArrayList<ResiduoDTO> residuosDto,
                                boolean vehiculo, String observacion) throws NotNullException {

        Propietario propietario=new Propietario(viviendaDto.getNombrePropietario(),viviendaDto.getApellidopropietarioDto(),viviendaDto.getDnipropietarioDto());
        Direccion direccion=new Direccion(viviendaDto.getCalle(),viviendaDto.getNumero(),viviendaDto.getBarrio(),viviendaDto.getDireccion().getIdDireccion());
        Vivienda vivienda=new Vivienda(viviendaDto.getNumeroVivienda(),propietario,direccion);
        ArrayList<Residuo>residuos=new ArrayList<>();

        for(ResiduoDTO dto: residuosDto) {

            residuos.add(new Residuo(dto.getTipoResiduo(),dto.getPuntosKilo()));

        }
        //Vivienda vivienda, Date fecha2, ArrayList<Residuo> residuos, boolean vehiculo, String observacion
        Pedido pedido= new Pedido(vivienda,fecha,residuos,vehiculo,observacion);

        this.pedidoDao.create(vivienda, pedido);

    }




}
