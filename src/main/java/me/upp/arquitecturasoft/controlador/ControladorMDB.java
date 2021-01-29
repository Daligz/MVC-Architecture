package me.upp.arquitecturasoft.controlador;

import me.upp.arquitecturasoft.pkgModelo.MDB;
import me.upp.arquitecturasoft.vista.MainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ControladorMDB extends JFrame implements ActionListener {

    private MainView view;
    private MDB model;

    public ControladorMDB(final MainView view, final MDB model) {
        this.view = view;
        this.model = model;
    }

    public void actionPerformed(final ActionEvent event) {
        final Object source = event.getSource();
        if (source == this.view.getBtnGuardar()) {
            this.guardar();
        } else if (source == this.view.getBtnBuscar()) {
            this.buscar();
        } else if (source == this.view.getBtnEditar()) {
            this.editar();
        } else if (source == this.view.getBtnEliminar()) {
            this.eliminar();
        } else if (source == this.view.getBtnLimpiar()) {
            limpiar();
        }
    }

    private void guardar() {
        if (this.view.getTxtNombre().getText().equals("") ||
                this.view.getTxtApellidoPa().getText().equals("") ||
                this.view.getTxtApellidoMa().getText().equals("") ||
                this.view.getTxtTelefono().getText().equals("")) {
            this.windowMessage("No se pueden dejar campos vacios");
            return;
        }
        final String leyenda = this.model.registrarAlta(
                MDB.TABLE_NAME,
                "null, '".concat(this.view.getTxtNombre().getText())
                        + "', '".concat(this.view.getTxtApellidoPa().getText())
                        + "', '".concat(this.view.getTxtApellidoMa().getText())
                        + "', '".concat(this.view.getTxtTelefono().getText()) + "'"
        );
        this.windowMessage(leyenda);
        this.limpiar();
    }

    private void buscar() {
        final String id = this.view.getTxtId().getText();
        if (id == null || id.equals("")) {
            this.windowMessage("Se debe de especificar un ID");
            return;
        }
        try {
            final List<ArrayList<String>> contactos = this.model.consultarDatos(
                    MDB.TABLE_NAME,
                    "*", "idContacto = ".concat(id)
            );
            for (final ArrayList<String> contacto : contactos) {
                this.view.getTxtId().setText(contacto.get(0));
                this.view.getTxtNombre().setText(contacto.get(1));
                this.view.getTxtApellidoPa().setText(contacto.get(2));
                this.view.getTxtApellidoMa().setText(contacto.get(3));
                this.view.getTxtTelefono().setText(contacto.get(4));
            }
        } catch (final Exception ex) {
            this.windowMessage("No se encontraron datos con el ID ".concat(id));
        }
    }

    private void editar() {
        final String id = this.view.getTxtId().getText();
        if (id.equals("") ||
                this.view.getTxtNombre().getText().equals("") ||
                this.view.getTxtApellidoPa().getText().equals("") ||
                this.view.getTxtApellidoMa().getText().equals("") ||
                this.view.getTxtTelefono().getText().equals("")) {
            this.windowMessage("No se pueden dejar campos vacios");
            return;
        }
        try {
            final String leyenda = this.model.modificarRegistro(
                    MDB.TABLE_NAME,
                    "nombre = '".concat(this.view.getTxtNombre().getText())
                            + "', apellidoPa = '".concat(this.view.getTxtApellidoPa().getText())
                            + "', apellidoMa = '".concat(this.view.getTxtApellidoMa().getText())
                            + "', telefono = '".concat(this.view.getTxtTelefono().getText()) + "'",
                    "idContacto = ".concat(id)
                    );
        } catch (final Exception ex) {
            this.windowMessage("No se pudo editar el contacto con el ID ".concat(id));
        }
        this.limpiar();
        this.windowMessage("Contacto editado");
    }

    private void eliminar() {
        final String id = this.view.getTxtId().getText();
        if (id == null || id.equals("")) {
            this.windowMessage("Se debe de especificar un ID");
            return;
        }
        final int warning = JOptionPane.showConfirmDialog(
                null, "Seguro que quiere eliminar el contacto ".concat(id),
                "Warning", JOptionPane.YES_NO_OPTION);
        if (warning != JOptionPane.YES_OPTION) return;
        try {
            final String leyenda = this.model.borrarRegistro(
                    MDB.TABLE_NAME,
                    "idContacto = ".concat(id)
            );
            this.windowMessage(leyenda);
        } catch (final Exception ex) {
            this.windowMessage("No se pudo eliminar el contacto con el ID ".concat(id));
        }
        this.limpiar();
    }

    private void windowMessage(final String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    public void iniciar() {
        this.view.setTitle("Contactos (MVC)");
        this.view.iniciar();
    }

    private void limpiar() {
        this.view.getTxtId().setText("");
        this.view.getTxtNombre().setText("");
        this.view.getTxtApellidoPa().setText("");
        this.view.getTxtApellidoMa().setText("");
        this.view.getTxtTelefono().setText("");
    }
}
