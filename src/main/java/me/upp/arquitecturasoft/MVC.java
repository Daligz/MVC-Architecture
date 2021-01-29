package me.upp.arquitecturasoft;

import me.upp.arquitecturasoft.controlador.ControladorMDB;
import me.upp.arquitecturasoft.pkgModelo.MDB;
import me.upp.arquitecturasoft.vista.MainView;

public class MVC {

    public static void main(final String[] args) {
        final MDB model = new MDB();
        final MainView view = new MainView(model);
        final ControladorMDB controladorMDB = new ControladorMDB(view, model);
        controladorMDB.iniciar();
    }
}
