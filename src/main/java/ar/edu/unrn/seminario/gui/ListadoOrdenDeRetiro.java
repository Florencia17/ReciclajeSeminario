package ar.edu.unrn.seminario.gui;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import ModeloException.NotNullException;
import ar.edu.unrn.seminario.api.IApi;
import ar.edu.unrn.seminario.dto.OrdenDeRetiroDTO;
import ar.edu.unrn.seminario.dto.UsuarioDTO;
import ar.edu.unrn.seminario.exception.StateException;

public class ListadoOrdenDeRetiro extends JFrame {

    private JPanel contentPane;
    private JTable table;
    DefaultTableModel modelo;
    IApi api;
    JButton activarButton;
    JButton desactivarButton;

    /**
     * Create the frame.
     */
    public ListadoOrdenDeRetiro(IApi api) throws NotNullException {
        this.api = api;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);

        table = new JTable();
        String[] titulos = { "VIVIENDA", "FECHA", "RECOLECTOR", "ESTADO", };

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                // Habilitar botones
                habilitarBotones(true);

            }
        });
        modelo = new DefaultTableModel(new Object[][] {}, titulos);

        // Obtiene la lista de usuarios a mostrar
        List<OrdenDeRetiroDTO> ordenDeRetiroList = api.obtenerOrdenDeRetiro();
        // Agrega los usuarios en el model
        for (OrdenDeRetiroDTO o : ordenDeRetiroList) {
            modelo.addRow(new Object[] { o.getVivienda() , o.getFechaOrden(), o.getRecolector().obtenerNombre(), o.getEstado() });
        }

        table.setModel(modelo);

        scrollPane.setViewportView(table);


        JButton cerrarButton = new JButton("Cerrar");
        cerrarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
        });
//		contentPane.add(cerrarButton, BorderLayout.SOUTH);

        JPanel pnlBotonesOperaciones = new JPanel();
        pnlBotonesOperaciones.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        contentPane.add(pnlBotonesOperaciones, BorderLayout.SOUTH);
        pnlBotonesOperaciones.add(desactivarButton);
        pnlBotonesOperaciones.add(activarButton);
        pnlBotonesOperaciones.add(cerrarButton);

        // Deshabilitar botones que requieren tener una fila seleccionada
        habilitarBotones(false);
    }

    private void habilitarBotones(boolean b) {
        activarButton.setEnabled(b);
        desactivarButton.setEnabled(b);

    }

    private void reloadGrid() throws NotNullException {
        // Obtiene el model del table
        DefaultTableModel modelo = (DefaultTableModel) table.getModel();
        // Obtiene la lista de usuarios a mostrar
        List<OrdenDeRetiroDTO> ordenDeRetiroDTOList = api.obtenerOrdenDeRetiro();
        // Resetea el model
        modelo.setRowCount(0);

        // Agrega los usuarios en el model
        for (OrdenDeRetiroDTO o : ordenDeRetiroDTOList) {
            modelo.addRow(new Object[] { o.getVivienda(), o.getFechaOrden(), o.getRecolector().obtenerNombre(), o.getEstado() });
        }

    }

}
