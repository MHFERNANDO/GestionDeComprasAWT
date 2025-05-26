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
        setSize(600, 500);
        setLayout(new BorderLayout(10, 10));
        setLocationRelativeTo(null);
        setBackground(new Color(245, 250, 255));

        // Título
        Label lblTitulo = new Label("Personas Registradas", Label.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        add(lblTitulo, BorderLayout.NORTH);

        // Área de texto
        textArea = new TextArea();
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        textArea.setEditable(false);
        add(textArea, BorderLayout.CENTER);

        // Botón de volver
        Panel panelBoton = new Panel(new FlowLayout(FlowLayout.CENTER));
        btnVolver = new Button("Volver");
        btnVolver.setFont(new Font("Arial", Font.BOLD, 16));
        btnVolver.setBackground(new Color(180, 210, 255));
        btnVolver.setPreferredSize(new Dimension(120, 35));
        panelBoton.setBackground(new Color(230, 240, 255));
        panelBoton.add(btnVolver);
        add(panelBoton, BorderLayout.SOUTH);

        btnVolver.addActionListener(e -> {
            ventanaAnterior.setVisible(true);
            setVisible(false);
        });

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });

        setVisible(false);
    }

    public void mostrar() {
        mostrarListas(ventanaIni.listaEmpleados, ventanaIni.listaProveedores);
        setVisible(true);
    }

    private void mostrarListas(ArrayList<Persona> empleados, ArrayList<Persona> proveedores) {
        StringBuilder texto = new StringBuilder();
        texto.append("=== EMPLEADOS ===\n");
        if (empleados.isEmpty()) {
            texto.append("No hay empleados registrados.\n");
        } else {
            for (Persona p : empleados) {
                texto.append(p).append("\n");
            }
        }

        texto.append("\n=== PROVEEDORES ===\n");
        if (proveedores.isEmpty()) {
            texto.append("No hay proveedores registrados.\n");
        } else {
            for (Persona p : proveedores) {
                texto.append(p).append("\n");
            }
        }

        textArea.setText(texto.toString());
    }
}
