package ec.edu.ups.poo.Vista.Persona;

import ec.edu.ups.poo.Vista.VentanaIni;

import java.awt.*;
import java.awt.event.*;

public class VentanaPersona extends Frame {

    private Button botonBusqueda;
    private Button botonIngreso;
    private Button botonListar;
    private Button botonAtras;

    private VentanaIni ventanaIni;

    public VentanaPersona(VentanaIni ventanaIni) {
        this.ventanaIni = ventanaIni;

        setTitle("Gestión de Personas");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(245, 250, 255));

        // ---------- Encabezado ----------
        Label textoTitulo = new Label("GESTIÓN DE PERSONAS", Label.CENTER);
        textoTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        textoTitulo.setForeground(new Color(30, 30, 80));
        add(textoTitulo, BorderLayout.NORTH);

        // ---------- Panel funcional ----------
        Panel panelBotones = new Panel(new GridLayout(4, 1, 15, 15));
        panelBotones.setBackground(new Color(235, 245, 255));
        panelBotones.setFont(new Font("Arial", Font.PLAIN, 18));
        panelBotones.setPreferredSize(new Dimension(300, 200));

        botonBusqueda = crearBoton("Buscar Personas");
        botonIngreso = crearBoton("Añadir Persona");
        botonListar = crearBoton("Listar Personas");
        botonAtras = crearBoton("Atrás");

        panelBotones.add(botonBusqueda);
        panelBotones.add(botonIngreso);
        panelBotones.add(botonListar);
        panelBotones.add(botonAtras);

        Panel panelCentro = new Panel(new FlowLayout(FlowLayout.CENTER, 20, 40));
        panelCentro.setBackground(new Color(245, 250, 255));
        panelCentro.add(panelBotones);
        add(panelCentro, BorderLayout.CENTER);

        // ---------- Ventanas auxiliares ----------
        BuscarPersona buscarPersona = new BuscarPersona(this);
        VentanaAgregarPersona ventanaAgregarPersona = new VentanaAgregarPersona(this);
        VentanaListaPersona ventanaLista = new VentanaListaPersona(this, ventanaIni);

        // ---------- Eventos ----------
        botonBusqueda.addActionListener(e -> {
            buscarPersona.setVisible(true);
            setVisible(false);
        });

        botonIngreso.addActionListener(e -> {
            ventanaAgregarPersona.setVisible(true);
            setVisible(false);
        });

        botonListar.addActionListener(e -> {
            ventanaLista.mostrar();
            setVisible(false);
        });

        botonAtras.addActionListener(e -> {
            ventanaIni.setVisible(true);
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
        boton.setPreferredSize(new Dimension(180, 40));
        return boton;
    }

    public VentanaIni getVentanaIni() {
        return ventanaIni;
    }
}
