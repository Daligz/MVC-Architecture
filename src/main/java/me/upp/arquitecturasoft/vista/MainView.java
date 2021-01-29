package me.upp.arquitecturasoft.vista;

import me.upp.arquitecturasoft.controlador.ControladorMDB;
import me.upp.arquitecturasoft.pkgModelo.MDB;

import javax.swing.*;

public class MainView extends JFrame {

    private JPanel mainJP;
    private JTextField txtNombre;
    private JLabel lblNombre;
    private JLabel lblApellidoPa;
    private JTextField txtApellidoPa;
    private JLabel lblId;
    private JTextField txtTelefono;
    private JLabel lblTelefono;
    private JButton btnGuardar;
    private JButton btnEliminar;
    private JButton btnBuscar;
    private JButton btnEditar;
    private JTextField txtApellidoMa;
    private JLabel lblApellidoMa;
    private JButton btnLimpiar;
    private JTextField txtId;

    private JFrame jFrame;

    public MainView(final MDB modelo) {
        this.getBtnGuardar().addActionListener(new ControladorMDB(this, modelo));
        this.getBtnBuscar().addActionListener(new ControladorMDB(this, modelo));
        this.getBtnEditar().addActionListener(new ControladorMDB(this, modelo));
        this.getBtnEliminar().addActionListener(new ControladorMDB(this, modelo));
        this.getBtnLimpiar().addActionListener(new ControladorMDB(this, modelo));
    }

    public void iniciar() {
        this.jFrame = new JFrame();
        this.jFrame.setContentPane(this.mainJP);
        this.jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jFrame.pack();
        this.jFrame.setVisible(true);
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public JTextField getTxtApellidoPa() {
        return txtApellidoPa;
    }

    public JTextField getTxtTelefono() {
        return txtTelefono;
    }

    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public JButton getBtnEditar() {
        return btnEditar;
    }

    public JTextField getTxtApellidoMa() {
        return txtApellidoMa;
    }

    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    public JTextField getTxtId() {
        return txtId;
    }
}
