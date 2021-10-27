package ar.edu.unrn.seminario.gui;

import ar.edu.unrn.seminario.api.IApi;
import ar.edu.unrn.seminario.dto.PropietarioDTO;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ModificarDatosPropietario extends JFrame {

    private JPanel contentPane;
    private JTextField nombreTextField;
    private JTextField apelllidoTextField_1;
    private JTextField dniTextField_2;

    /**
     * Launch the application.
     */
   /* public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ModificarDatosPropietario frame = new ModificarDatosPropietario();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }*/

    /**
     * Create the frame.
     */
    public ModificarDatosPropietario(IApi api) {
        setTitle("Modificar datos del propietario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Selecione los datos que desee modificar: ");
        lblNewLabel.setBounds(10, 11, 232, 14);
        contentPane.add(lblNewLabel);

        JCheckBox nombreCheckBox = new JCheckBox("Nombre");
        nombreCheckBox.setBounds(10, 44, 97, 23);
        contentPane.add(nombreCheckBox);

        JCheckBox apellidoCheckBox_1 = new JCheckBox("Apellido");
        apellidoCheckBox_1.setBounds(10, 104, 97, 23);
        contentPane.add(apellidoCheckBox_1);

        JCheckBox dniCheckBox_2 = new JCheckBox("DNI");
        dniCheckBox_2.setBounds(10, 174, 97, 23);
        contentPane.add(dniCheckBox_2);

        JLabel lblNewLabel_1 = new JLabel("Nuevo nombre: ");
        lblNewLabel_1.setBounds(10, 74, 97, 14);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Nuevo apellido:");
        lblNewLabel_2.setBounds(10, 134, 130, 14);
        contentPane.add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("Nuevo DNI:");
        lblNewLabel_3.setBounds(10, 204, 97, 14);
        contentPane.add(lblNewLabel_3);

        nombreTextField = new JTextField();
        nombreTextField.setBounds(105, 71, 86, 20);
        contentPane.add(nombreTextField);
        nombreTextField.setColumns(10);

        apelllidoTextField_1 = new JTextField();
        apelllidoTextField_1.setBounds(105, 131, 86, 20);
        contentPane.add(apelllidoTextField_1);
        apelllidoTextField_1.setColumns(10);

        dniTextField_2 = new JTextField();
        dniTextField_2.setBounds(105, 201, 86, 20);
        contentPane.add(dniTextField_2);
        dniTextField_2.setColumns(10);

        JButton ModificarPropietarioNewButton = new JButton("Modificar");
        ModificarPropietarioNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                //obtener el propietario a modificar

                if (nombreCheckBox.isSelected()) {
                    int confirmar = JOptionPane.showConfirmDialog(null, "Esta seguro que desea modificar el nombre del propietario '" + p.getNombre() + "'?", "Confirmar modificación.", JOptionPane.YES_NO_OPTION);
                    if (confirmar == JOptionPane.YES_OPTION) {

                        p.setNombre(nombreTextField.getText());
                    }

                }

                if (apellidoCheckBox_1.isSelected()) {
                    int confirmar = JOptionPane.showConfirmDialog(null, "Esta seguro que desea modificar el apellido del propietario '" + p.getApellido() + "'?", "Confirmar modificación.", JOptionPane.YES_NO_OPTION);
                    if (confirmar == JOptionPane.YES_OPTION) {

                        p.setApellido(apelllidoTextField_1.getText());
                    }

                }

                if (dniCheckBox_2.isSelected()) {
                    int confirmar = JOptionPane.showConfirmDialog(null, "Esta seguro que desea modificar el DNI del propietario '" + p.getDni()+ "'?", "Confirmar modificación.", JOptionPane.YES_NO_OPTION);
                    if (confirmar == JOptionPane.YES_OPTION) {

                        p.setDni(dniTextField_2.getText());
                    }

                }

                //api.ModificarPropietario(p.getNombre(), p.getApellido(), p.getDni());
            }
        });
        ModificarPropietarioNewButton.setBounds(10, 238, 292, 23);
        contentPane.add(ModificarPropietarioNewButton);

        JButton CancelarNewButton_1 = new JButton("Cancelar");
        CancelarNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        CancelarNewButton_1.setBounds(324, 238, 89, 23);
        contentPane.add(CancelarNewButton_1);
    }
}