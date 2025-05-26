package ec.edu.ups.poo.Vista.Persona;

import ec.edu.ups.poo.Controlador.Persona;
import ec.edu.ups.poo.Vista.VentanaIni;

import java.awt.*;
import java.awt.event.*;

public class VentanaProveedor extends Frame {

    private TextField campoNombre;
    private TextField campoCedula;
    private TextField campoEmpresa;
    private Button botonRegistrar;
    private Button botonAtras;

    private VentanaAgregarPersona ventanaAnterior;
    private VentanaIni ventanaIni;

    public VentanaProveedor(VentanaAgregarPersona ventanaAnterior, VentanaIni ventanaIni) {
        this.ventanaAnterior = ventanaAnterior;
        this.ventanaIni = ventanaIni;

        setTitle("Registrar Proveedor");
        setSize(500, 350);
        setLayout(new BorderLayout(10, 10));
        setLocationRelativeTo(null);
        setBackground(new Color(245, 250, 255));

        // ---------- Encabezado ----------
        Label titulo = new Label("Formulario de Proveedor", Label.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setForeground(new Color(30, 30, 30));
        add(titulo, BorderLayout.NORTH);

        // ---------- Centro: formulario ----------
        Panel panelCentro = new Panel(new GridLayout(3, 2, 10, 10));
        panelCentro.setBackground(new Color(245, 250, 255));
        panelCentro.setFont(new Font("Arial", Font.PLAIN, 14));

        panelCentro.add(new Label("Nombre:"));
        campoNombre = new TextField();
        panelCentro.add(campoNombre);

        panelCentro.add(new Label("Cédula:"));
        campoCedula = new TextField();
        panelCentro.add(campoCedula);

        panelCentro.add(new Label("Empresa:"));
        campoEmpresa = new TextField();
        panelCentro.add(campoEmpresa);

        add(panelCentro, BorderLayout.CENTER);

        // ---------- Panel botones ----------
        Panel panelBotones = new Panel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelBotones.setBackground(new Color(230, 240, 255));

        botonRegistrar = crearBoton("Registrar");
        botonAtras = crearBoton("Atrás");

        panelBotones.add(botonRegistrar);
        panelBotones.add(botonAtras);
        add(panelBotones, BorderLayout.SOUTH);

        // ---------- Eventos ----------
        botonRegistrar.addActionListener(e -> {
            String nombre = campoNombre.getText().trim();
            String cedula = campoCedula.getText().trim();
            String empresa = campoEmpresa.getText().trim();

            if (nombre.isEmpty() || cedula.isEmpty() || empresa.isEmpty()) {
                mostrarDialogo("Por favor, complete todos los campos.");
                return;
            }

            Persona proveedor = new Persona(nombre + " - Empresa: " + empresa, cedula);
            ventanaIni.getListaProveedores().add(proveedor);

            mostrarDialogo("Proveedor registrado:\n" + proveedor.toString());

            campoNombre.setText("");
            campoCedula.setText("");
            campoEmpresa.setText("");
        });

        botonAtras.addActionListener(e -> {
            ventanaAnterior.setVisible(true);
            setVisible(false);
        });

        // ---------- Cierre ----------
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
