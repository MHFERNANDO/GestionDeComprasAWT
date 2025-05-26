package ec.edu.ups.poo.Vista.Persona;

import ec.edu.ups.poo.Controlador.Empleado;
import ec.edu.ups.poo.Vista.VentanaIni;
import ec.edu.ups.poo.Enums.Departamento;

import java.awt.*;
import java.awt.event.*;

public class VentanaEmpleado extends Frame {

    private TextField campoNombre;
    private TextField campoCedula;
    private TextField campoCargo;
    private Choice choiceDepartamento;
    private Button botonRegistrar;
    private Button botonAtras;

    private VentanaIni ventanaIni;
    private VentanaAgregarPersona ventanaAgregarPersona;

    public VentanaEmpleado(VentanaAgregarPersona ventanaAgregarPersona, VentanaIni ventanaIni) {
        this.ventanaAgregarPersona = ventanaAgregarPersona;
        this.ventanaIni = ventanaIni;

        setTitle("Registrar Empleado");
        setSize(500, 350);
        setLayout(new BorderLayout(10, 10));
        setLocationRelativeTo(null);
        setBackground(new Color(245, 250, 255));

        Label lblTitulo = new Label("Formulario de Empleado", Label.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(30, 30, 30));
        add(lblTitulo, BorderLayout.NORTH);

        Panel panelCentro = new Panel(new GridLayout(4, 2, 10, 10));
        panelCentro.setBackground(new Color(245, 250, 255));
        panelCentro.setFont(new Font("Arial", Font.PLAIN, 14));

        panelCentro.add(new Label("Nombre:"));
        campoNombre = new TextField();
        panelCentro.add(campoNombre);

        panelCentro.add(new Label("Cédula:"));
        campoCedula = new TextField();
        panelCentro.add(campoCedula);

        panelCentro.add(new Label("Cargo:"));
        campoCargo = new TextField();
        panelCentro.add(campoCargo);

        panelCentro.add(new Label("Departamento:"));
        choiceDepartamento = new Choice();
        for (Departamento dep : Departamento.values()) {
            choiceDepartamento.add(dep.name());
        }
        panelCentro.add(choiceDepartamento);

        add(panelCentro, BorderLayout.CENTER);

        Panel panelBotones = new Panel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelBotones.setBackground(new Color(230, 240, 255));

        botonRegistrar = crearBoton("Registrar");
        botonAtras = crearBoton("Atrás");

        panelBotones.add(botonRegistrar);
        panelBotones.add(botonAtras);
        add(panelBotones, BorderLayout.SOUTH);

        botonRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = campoNombre.getText();
                String cedula = campoCedula.getText();
                String cargo = campoCargo.getText();
                String depSeleccionado = choiceDepartamento.getSelectedItem();

                if (nombre.isEmpty() || cedula.isEmpty() || cargo.isEmpty()) {
                    mostrarDialogo("Por favor, complete todos los campos.");
                    return;
                }

                Departamento departamento = Departamento.valueOf(depSeleccionado);
                Empleado empleado = new Empleado(nombre, cedula, cargo, departamento);
                ventanaIni.getListaEmpleados().add(empleado);

                mostrarDialogo("Empleado registrado:\n" + empleado.toString());

                campoNombre.setText("");
                campoCedula.setText("");
                campoCargo.setText("");
            }
        });

        botonAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                ventanaAgregarPersona.setVisible(true);
            }
        });

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });

        setVisible(false);
    }

    private Button crearBoton(String texto) {
        Button boton = new Button(texto);
        boton.setFont(new Font("Arial", Font.BOLD, 16));
        boton.setBackground(new Color(180, 210, 255));
        boton.setForeground(Color.BLACK);
        boton.setPreferredSize(new Dimension(160, 35));
        return boton;
    }

    private void mostrarDialogo(String mensaje) {
        Dialog dialogo = new Dialog(this, "Mensaje", true);
        dialogo.setLayout(new BorderLayout());
        Label lblMensaje = new Label(mensaje, Label.CENTER);
        lblMensaje.setFont(new Font("Arial", Font.PLAIN, 14));
        dialogo.add(lblMensaje, BorderLayout.CENTER);
        Button btnCerrar = new Button("Cerrar");
        btnCerrar.addActionListener(e -> dialogo.dispose());
        Panel panelBoton = new Panel();
        panelBoton.add(btnCerrar);
        dialogo.add(panelBoton, BorderLayout.SOUTH);
        dialogo.setSize(300, 150);
        dialogo.setLocationRelativeTo(this);
        dialogo.setVisible(true);
    }
}
