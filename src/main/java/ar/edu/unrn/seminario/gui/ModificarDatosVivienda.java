package ar.edu.unrn.seminario.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JButton;

public class ModificarDatosVivienda extends JFrame {

    private JPanel contentPane;
    private JTextField calleTextField;
    private JTextField numeroTextField_1;
    private JTextField barrioTextField_2;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ModificarDatosVivienda frame = new ModificarDatosVivienda();
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
    public ModificarDatosVivienda() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Seleccione los datos que desee modificar:");
        lblNewLabel.setBounds(10, 11, 311, 14);
        contentPane.add(lblNewLabel);

        JCheckBox calleCheckBox = new JCheckBox("Calle");
        calleCheckBox.setBounds(6, 42, 97, 23);
        contentPane.add(calleCheckBox);

        JCheckBox numeroCheckBox_1 = new JCheckBox("Numero");
        numeroCheckBox_1.setBounds(6, 110, 97, 23);
        contentPane.add(numeroCheckBox_1);

        JCheckBox barrioCheckBox_2 = new JCheckBox("Barrio");
        barrioCheckBox_2.setBounds(6, 184, 97, 23);
        contentPane.add(barrioCheckBox_2);

        calleTextField = new JTextField();
        calleTextField.setBounds(108, 70, 86, 20);
        contentPane.add(calleTextField);
        calleTextField.setColumns(10);

        numeroTextField_1 = new JTextField();
        numeroTextField_1.setBounds(108, 147, 86, 20);
        contentPane.add(numeroTextField_1);
        numeroTextField_1.setColumns(10);

        barrioTextField_2 = new JTextField();
        barrioTextField_2.setBounds(108, 214, 86, 20);
        contentPane.add(barrioTextField_2);
        barrioTextField_2.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Nueva calle:");
        lblNewLabel_1.setBounds(10, 72, 77, 14);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Nuevo numero:");
        lblNewLabel_2.setBounds(10, 150, 93, 14);
        contentPane.add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("Nuevo barrio:");
        lblNewLabel_3.setBounds(10, 217, 77, 14);
        contentPane.add(lblNewLabel_3);

        JButton modificarNewButton = new JButton("Modificar");
        modificarNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

            }
        });
        modificarNewButton.setBounds(10, 238, 290, 23);
        contentPane.add(modificarNewButton);

        JButton cancelarNewButton_1 = new JButton("Cancelar");
        cancelarNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        cancelarNewButton_1.setBounds(335, 238, 89, 23);
        contentPane.add(cancelarNewButton_1);
    }
}