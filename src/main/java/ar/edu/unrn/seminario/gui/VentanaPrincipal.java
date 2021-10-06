package ar.edu.unrn.seminario.gui;

import ar.edu.unrn.seminario.api.IApi;
import ar.edu.unrn.seminario.api.MemoryApi;
import ar.edu.unrn.seminario.api.PersistenceApi;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ModeloException.NotNullException;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;

public class VentanaPrincipal extends JFrame {



    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    IApi api = new PersistenceApi();
                    VentanaPrincipal frame = new VentanaPrincipal(api);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public VentanaPrincipal(IApi api) {
        //ii8n
        //ResourceBundle labels=ResourceBundle.getBundle("labels");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        ResourceBundle labels = ResourceBundle.getBundle("labels", new Locale("es"));
        //ResourceBundle labels = ResourceBundle.getBundle("labels");
        JMenuBar menuBar = new JMenuBar();
        menuBar.setToolTipText("texto");
        setJMenuBar(menuBar);

        JMenu UsuariosNewMenu = new JMenu(labels.getString("ventana.principal.menu.usuarios"));
        menuBar.add(UsuariosNewMenu);

        JMenuItem AltaModificacionNewMenuItem = new JMenuItem(labels.getString("ventana.principal.menu.alta.modificacion"));
        AltaModificacionNewMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AltaUsuario altaUsuario= new AltaUsuario(api);
                altaUsuario.setVisible(true);
            }
        });
        UsuariosNewMenu.add(AltaModificacionNewMenuItem);

        JMenuItem ListadoNewMenuItem = new JMenuItem(labels.getString("ventana.principal.menu.item.listado"));
        ListadoNewMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                ListarUsuario listadoUsuario= new ListarUsuario(api);
                listadoUsuario.setVisible(true);
            }
        });
        UsuariosNewMenu.add(ListadoNewMenuItem);

        JMenu RolesNewMenu = new JMenu(labels.getString("ventana.principal.menu.rol"));
        menuBar.add(RolesNewMenu);

        JMenuItem AltaRolNewMenuItem = new JMenuItem(labels.getString("ventana.principal.menu.item.rol"));
        AltaRolNewMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AltaRol altaRol= new AltaRol(api);
                altaRol.setVisible(true);
            }
        });
        RolesNewMenu.add(AltaRolNewMenuItem);

        JMenuItem ListarNewMenuItem = new JMenuItem(labels.getString("ventana.principal.menu.lista.rol"));
        ListarNewMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        RolesNewMenu.add(ListarNewMenuItem);

        JMenu ViviendasNewMenu = new JMenu(labels.getString("ventana.principal.menu.viviendas"));
        menuBar.add(ViviendasNewMenu);

        JMenuItem RegistrarViviendaNewMenuItem = new JMenuItem(labels.getString("ventana.principal.menu.item.registrar.vivienda"));
        RegistrarViviendaNewMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RegistrarVivienda registrarVivienda= new RegistrarVivienda(api);
                registrarVivienda.setVisible(true);
            }
        });
        ViviendasNewMenu.add(RegistrarViviendaNewMenuItem);

        JMenuItem ListarViviendaNewMenuItem = new JMenuItem(labels.getString("ventana.principal.menu.item.listar.vivienda"));
        ListarViviendaNewMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ListadoVivienda listado;
                try {
                    listado = new ListadoVivienda(api);
                    listado.setVisible(true);
                } catch (SQLException | NotNullException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
        });
        ViviendasNewMenu.add(ListarViviendaNewMenuItem);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);


        JMenu pedidosNewMenu = new JMenu("Pedidos");
        menuBar.add(pedidosNewMenu);

        JMenuItem realizarpedidoNewMenuItem = new JMenuItem("Realizar Pedido");
        realizarpedidoNewMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RealizarPedido realizarPedido= new RealizarPedido(api); //agregar api
                setVisible(true);
            }
        });
        pedidosNewMenu.add(realizarpedidoNewMenuItem);

        JMenuItem listadopedidosNewMenuItem = new JMenuItem("Listado de Pedidos");
        listadopedidosNewMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //crear clase
            }
        });
        pedidosNewMenu.add(listadopedidosNewMenuItem);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton SalirNewButton = new JButton(labels.getString("ventana.principal.menu.item.salir"));
        SalirNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        SalirNewButton.setBounds(179, 217, 89, 23);
        contentPane.add(SalirNewButton);
    }
}

