package ar.edu.unrn.seminario.gui;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ModeloException.NotNullException;
import ar.edu.unrn.seminario.api.IApi;
import ar.edu.unrn.seminario.dto.PedidoDTO;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListadoDePedidos extends JFrame {

    private JPanel contentPane;
    private JTable table;
    DefaultTableModel modelo;
    IApi api;
    private JTextField textField;
    private JTextField textField_1;
    private JTable table_1;
    private JTextField textField_2;
    private JButton btnNewButton;

    /**
     * Launch the application.

     public static void main(String[] args) {
     EventQueue.invokeLater(new Runnable() {
     public void run() {
     try {
     ListadoDePedidos frame = new ListadoDePedidos();
     frame.setVisible(true);
     } catch (Exception e) {
     e.printStackTrace();
     }
     }
     });
     }
     * @throws NotNullException
     * @throws SQLException


     */
    public ListadoDePedidos(IApi api) throws SQLException, NotNullException {

        this.api = api;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 325);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        ResourceBundle labels = ResourceBundle.getBundle("labels",new Locale("en"));
        contentPane.setLayout(null);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(12, 5, 577, 270);
        contentPane.add(scrollPane);


        table_1 = new JTable();

        String [] titulos ={
                labels.getString("listado.pedidos.titulos.id.vivienda"),labels.getString("listado.pedidos.titulos.fecha"),labels.getString("listado.pedidos.titulos.residuo"),
                labels.getString("listado.pedidos.titulos.vehiculo"), labels.getString("listado.pedidos.titulos.observacion"),labels.getString("listado.pedidos.titulos.nro.pedido")};
        modelo = new DefaultTableModel(new Object[][] {}, titulos);

        List<PedidoDTO> pedidos = api.obtenerPedidos();

        for(PedidoDTO p: pedidos) {

            modelo.addRow(new Object[] {p.obtenerVivienda().getNumero(), p.getFecha(),
                    p.getResiduo(), p.getVehiculo(),p.getObservacion(),p.getObservacion()});
        }
        table_1.setModel(modelo);


        scrollPane.setViewportView(table_1);

        JPanel panel = new JPanel();
        panel.setBounds(599, 5, 175, 270);
        contentPane.add(panel);
        panel.setLayout(null);

        textField_2 = new JTextField();
        textField_2.setBounds(37, 58, 86, 20);
        panel.add(textField_2);
        textField_2.setColumns(10);

        btnNewButton = new JButton("New button");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //crea la nueva ventana
                //ventanaNueva(api,getTextField)
            }
        });
        btnNewButton.setBounds(37, 107, 89, 23);
        panel.add(btnNewButton);




    }

    private void reloadGrid() throws SQLException, NotNullException {

        // Obtiene el model del table
        DefaultTableModel modelo = (DefaultTableModel) table_1.getModel();

        // Obtiene la lista de usuarios a mostrar
        List<PedidoDTO> pedidos = api.obtenerPedidos();
        try {
            pedidos = api.obtenerPedidos();
            // Resetea el model
            modelo.setRowCount(0);

            // Agrega los usuarios en el model
            for (PedidoDTO p: pedidos) {
                modelo.addRow(new Object[] {p.obtenerVivienda().getNumero(), p.getFecha(),
                        p.getResiduo(), p.getVehiculo(),p.getObservacion(),p.getObservacion() });
            }
        } catch (SQLException | NotNullException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }



    }

}

