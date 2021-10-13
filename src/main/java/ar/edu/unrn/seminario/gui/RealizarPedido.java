package ar.edu.unrn.seminario.gui;

import ModeloException.NotNullException;
import ar.edu.unrn.seminario.api.IApi;
import ar.edu.unrn.seminario.dto.ResiduoDTO;
import ar.edu.unrn.seminario.dto.ViviendaDTO;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.List;
import java.awt.Button;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RealizarPedido extends JFrame {

    private JPanel contentPane;
    private JTextField observacionestextField_4;
    private JTextField textField;
    private JTextField textField_1;
    private ViviendaDTO vivienda;

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
    public RealizarPedido(IApi api,ViviendaDTO viviendaDto) {
        this.vivienda=viviendaDto;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 687, 315);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Complete el formulario para poder realizar el pedido de retiro de residuos:");
        lblNewLabel.setToolTipText("sadasd");
        lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 11));
        lblNewLabel.setBounds(10, 0, 524, 46);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Numero de vivienda elegida:");
        lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 20));
        lblNewLabel_1.setBounds(20, 41, 342, 67);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_5 = new JLabel("kilos del residuo:");
        lblNewLabel_5.setBounds(523, 127, 126, 16);
        contentPane.add(lblNewLabel_5);

        JCheckBox vehiculoNewCheckBox = new JCheckBox("Se requiere vehiculo de carga pesada?");
        vehiculoNewCheckBox.setBounds(18, 94, 300, 23);
        contentPane.add(vehiculoNewCheckBox);

        JLabel lblNewLabel_6 = new JLabel("Observaciones:");
        lblNewLabel_6.setBounds(12, 165, 116, 14);
        contentPane.add(lblNewLabel_6);

        observacionestextField_4 = new JTextField();
        observacionestextField_4.setBounds(132, 146, 158, 54);
        contentPane.add(observacionestextField_4);
        observacionestextField_4.setColumns(10);
        JLabel label = new JLabel("");
        label.setFont(new Font("Dialog", Font.BOLD, 20));
        label.setBounds(364, 53, 104, 46);
        contentPane.add(label);
        List list = new List();
        list.setBounds(491, 178, 158, 72);
        contentPane.add(list);

        textField_1 = new JTextField();
        textField_1.setBounds(527, 96, 114, 19);
        contentPane.add(textField_1);
        textField_1.setColumns(10);

        textField = new JTextField();
        textField.setBounds(527, 148, 114, 19);
        contentPane.add(textField);
        textField.setColumns(10);
        JButton pedidoNewButton = new JButton("Realizar Pedido");
        ArrayList<ResiduoDTO>residuos=new ArrayList<>();//creo arraylist residuos
        pedidoNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try{

                    //residuos.add(list.getItem(i))



                    api.registrarPedido(vivienda,LocalDate.now(),residuos
                            ,vehiculoNewCheckBox.isSelected(), observacionestextField_4.getText());

                    JOptionPane.showMessageDialog(null, "pedido realizado con exito");
                }catch (NotNullException e){
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        pedidoNewButton.setBounds(12, 256, 168, 23);
        contentPane.add(pedidoNewButton);

        JButton cancelarNewButton_1 = new JButton("Cancelar");
        cancelarNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        cancelarNewButton_1.setBounds(207, 256, 126, 23);
        contentPane.add(cancelarNewButton_1);



        JLabel lblNewLabel_2 = new JLabel("Tipo de residuo:");
        lblNewLabel_2.setBounds(517, 58, 158, 15);
        contentPane.add(lblNewLabel_2);

        Button button = new Button("agregar ");
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ResiduoDTO residuo;
                try {
                    residuo = new ResiduoDTO(textField_1.getText(),Integer.parseInt(textField.getText()));

                    residuos.add(residuo);
                    list.add(textField_1.getText());
                    list.add(textField.getText());
                } catch (NumberFormatException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } catch (NotNullException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }


            }
        });
        String numeroVivienda=Integer.toString(viviendaDto.getNumeroVivienda());
        label.setText(numeroVivienda);
        button.setBounds(591, 256, 86, 23);
        contentPane.add(button);





    }
}
