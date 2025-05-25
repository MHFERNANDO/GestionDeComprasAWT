package ec.edu.ups.poo.Vista.Persona;

import ec.edu.ups.poo.Controlador.Persona;
import ec.edu.ups.poo.Vista.VentanaIni;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class VentanaListaPersona extends Frame {

    private TextArea textArea;
    private Button btnVolver;

    private VentanaIni ventanaIni;
    private VentanaPersona ventanaAnterior;

    public VentanaListaPersona(VentanaPersona ventanaAnterior, VentanaIni ventanaIni) {
        this.ventanaIni = ventanaIni;
        this.ventanaAnterior = ventanaAnterior;

        setTitle("Lista de Personas");
        setSize(500, 500);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        textArea = new TextArea();
        textArea.setEditable(false);
        add(textArea, BorderLayout.CENTER);

        btnVolver = new Button("Volver");
        add(btnVolver, BorderLayout.SOUTH);

        btnVolver.addActionListener(e -> {
            ventanaAnterior.setVisible(true);
            setVisible(false);
        });

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
                System.exit(0);
            }
        });

        setVisible(false);
    }

    public void mostrar() {
        mostrarListas(ventanaIni.listaEmpleados, ventanaIni.listaProveedores);
        setVisible(true);
    }

    private void mostrarListas(ArrayList<Persona> empleados, ArrayList<Persona> proveedores) {
        String texto = "=== EMPLEADOS ===\n";
        if (empleados.isEmpty()) {
            texto += "No hay empleados registrados.\n";
        } else {
            for (Persona p : empleados) {
                texto += p.toString() + "\n";
            }
        }

        texto += "\n=== PROVEEDORES ===\n";
        if (proveedores.isEmpty()) {
            texto += "No hay proveedores registrados.\n";
        } else {
            for (Persona p : proveedores) {
                texto += p.toString() + "\n";
            }
        }

        textArea.setText(texto);
    }
}
