package ar.edu.unrn.seminario.gui;

import ModeloException.NotNullException;
import ar.edu.unrn.seminario.api.IApi;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class RealizarPedido extends JFrame {

    private JPanel contentPane;
    private JTextField calletextField;
    private JTextField numerotextField_1;
    private JTextField barriotextField_2;
    private JTextField residuostextField_3;
    private JTextField observacionestextField_4;

    /**
     * Launch the application.
     */
  /*  public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RealizarPedido frame = new RealizarPedido(IApi api);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
*/
    /**
     * Create the frame.
     */
    public RealizarPedido(IApi api) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Complete el formulario para poder realizar el pedido de retiro de residuos:");
        lblNewLabel.setBounds(10, 11, 391, 14);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Direccion de la vivienda:");
        lblNewLabel_1.setBounds(10, 46, 123, 14);
        contentPane.add(lblNewLabel_1);

        calletextField = new JTextField();
        calletextField.setBounds(10, 85, 86, 20);
        contentPane.add(calletextField);
        calletextField.setColumns(10);

        numerotextField_1 = new JTextField();
        numerotextField_1.setBounds(132, 85, 86, 20);
        contentPane.add(numerotextField_1);
        numerotextField_1.setColumns(10);

        barriotextField_2 = new JTextField();
        barriotextField_2.setBounds(275, 85, 86, 20);
        contentPane.add(barriotextField_2);
        barriotextField_2.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("Calle:");
        lblNewLabel_2.setBounds(10, 71, 46, 14);
        contentPane.add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("Numero:");
        lblNewLabel_3.setBounds(132, 71, 46, 14);
        contentPane.add(lblNewLabel_3);

        JLabel lblNewLabel_4 = new JLabel("Barrio:");
        lblNewLabel_4.setBounds(275, 71, 46, 14);
        contentPane.add(lblNewLabel_4);

        JLabel lblNewLabel_5 = new JLabel("Cantidad de residuos a retirar:");
        lblNewLabel_5.setBounds(10, 131, 168, 14);
        contentPane.add(lblNewLabel_5);

        residuostextField_3 = new JTextField();
        residuostextField_3.setBounds(190, 128, 86, 20);
        contentPane.add(residuostextField_3);
        residuostextField_3.setColumns(10);

        JCheckBox vehiculoNewCheckBox = new JCheckBox("Se requiere vehiculo de carga pesada?");
        vehiculoNewCheckBox.setBounds(10, 155, 242, 23);
        contentPane.add(vehiculoNewCheckBox);

        JLabel lblNewLabel_6 = new JLabel("Observaciones:");
        lblNewLabel_6.setBounds(10, 185, 99, 14);
        contentPane.add(lblNewLabel_6);

        observacionestextField_4 = new JTextField();
        observacionestextField_4.setBounds(142, 185, 86, 20);
        contentPane.add(observacionestextField_4);
        observacionestextField_4.setColumns(10);

        JButton pedidoNewButton = new JButton("Realizar Pedido");
        pedidoNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try{
                    api.registrarPedido(calletextField.getText(), Integer.parseInt(numerotextField_1.getText()),
                            barriotextField_2.getText(), LocalDate.now(), Integer.parseInt(residuostextField_3.getText()),
                                    vehiculoNewCheckBox.isSelected(), observacionestextField_4.getText());

                    JOptionPane.showMessageDialog(null, "pedido realizado con exito");
                }catch (NotNullException e){
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        pedidoNewButton.setBounds(108, 238, 168, 23);
        contentPane.add(pedidoNewButton);

        JButton cancelarNewButton_1 = new JButton("Cancelar");
        cancelarNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        cancelarNewButton_1.setBounds(335, 238, 89, 23);
        contentPane.add(cancelarNewButton_1);
    }
}