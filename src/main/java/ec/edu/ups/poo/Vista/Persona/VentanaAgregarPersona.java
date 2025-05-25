package ec.edu.ups.poo.Vista.Persona;

import ec.edu.ups.poo.Controlador.Persona;
import ec.edu.ups.poo.Vista.VentanaIni;

import java.awt.*;
import java.awt.event.*;

public class VentanaAgregarPersona extends Frame {

    private TextField campoNombre;
    private TextField campoCedula;
    private Choice tipoPersona;
    private Button botonGuardar;
    private Button botonAtras;

    private VentanaPersona ventanaPersona;

    public VentanaAgregarPersona(VentanaPersona ventanaPersona) {
        this.ventanaPersona = ventanaPersona;

        setTitle("Agregar Persona");
        setSize(400, 300);
        setLayout(new GridLayout(5, 2, 10, 10));
        setLocationRelativeTo(null);

        // Campos
        add(new Label("Nombre:"));
        campoNombre = new TextField();
        add(campoNombre);

        add(new Label("Cédula:"));
        campoCedula = new TextField();
        add(campoCedula);

        add(new Label("Tipo:"));
        tipoPersona = new Choice();
        tipoPersona.add("Empleado");
        tipoPersona.add("Proveedor");
        add(tipoPersona);

        botonGuardar = new Button("Guardar");
        botonAtras = new Button("Atras");
        add(botonGuardar);
        add(botonAtras);

        // Acción del botón Guardar
        botonGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = campoNombre.getText();
                String cedula = campoCedula.getText();
                String tipo = tipoPersona.getSelectedItem();

                if (nombre.isEmpty() || cedula.isEmpty()) {
                    System.out.println("Faltan datos");
                    return;
                }

                Persona nuevaPersona = new Persona(nombre, cedula);

                if (tipo.equals("Empleado")) {
                    ventanaPersona.getVentanaIni().listaEmpleados.add(nuevaPersona);
                    System.out.println("Empleado registrado");
                } else {
                    ventanaPersona.getVentanaIni().listaProveedores.add(nuevaPersona);
                    System.out.println("Proveedor registrado");
                }

                campoNombre.setText("");
                campoCedula.setText("");
            }
        });

        botonAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaPersona.setVisible(true);
                setVisible(false);
            }
        });

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
                System.exit(0);
            }
        });

        setVisible(false);
    }
}
