package ec.edu.ups.poo.Vista.Persona;

import ec.edu.ups.poo.Vista.VentanaIni;

import java.awt.*;
import java.awt.event.*;

public class VentanaPersona extends Frame {

    private Button botonBusqueda;
    private Button botonIngreso;
    private Button botonListar;
    private Button atras;

    private VentanaIni ventanaIni;

    public VentanaPersona(VentanaIni ventanaIni) {
        this.ventanaIni = ventanaIni;

        setTitle("Gestión de Proveedores o Empleado");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(245, 245, 245));

        // ---------- Encabezado ----------
        Label textoTitulo = new Label("GESTIÓN DE PROVEEDORES O EMPLEADO");
        textoTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        textoTitulo.setAlignment(Label.CENTER);
        textoTitulo.setForeground(new Color(40, 40, 100));

        Panel encabezado = new Panel();
        encabezado.setBackground(new Color(210, 230, 250));
        encabezado.setLayout(new FlowLayout(FlowLayout.CENTER));
        encabezado.add(textoTitulo);
        add(encabezado, BorderLayout.NORTH);

        // ---------- Panel funcional ----------
        Panel panelFuncional = new Panel(new GridLayout(4, 1, 15, 15));
        panelFuncional.setFont(new Font("Arial", Font.PLAIN, 18));
        panelFuncional.setBackground(new Color(245, 245, 255));
        panelFuncional.setPreferredSize(new Dimension(300, 200));

        botonBusqueda = new Button("Buscar Personas");
        botonIngreso = new Button("Añadir Persona");
        botonListar = new Button("Listar Personas");
        atras = new Button("Atras");

        for (Button boton : new Button[]{botonBusqueda, botonIngreso, botonListar, atras}) {
            boton.setFont(new Font("Arial", Font.BOLD, 16));
            boton.setBackground(new Color(230, 240, 255));
            boton.setForeground(Color.BLACK);
            panelFuncional.add(boton);
        }

        Panel panelCentro = new Panel(new FlowLayout(FlowLayout.CENTER, 20, 30));
        panelCentro.add(panelFuncional);
        add(panelCentro, BorderLayout.CENTER);

        // ---------- Ventanas auxiliares ----------
        BuscarPersona buscarProveedor = new BuscarPersona(this);
        VentanaAgregarPersona ventanaAgregarPersona = new VentanaAgregarPersona(this);
        VentanaListaPersona ventanaLista = new VentanaListaPersona(this, ventanaIni);


        // ---------- Eventos de botones ----------
        botonBusqueda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarProveedor.setVisible(true);
                setVisible(false);
            }
        });

        botonIngreso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaAgregarPersona.setVisible(true);
                setVisible(false);
            }
        });

        botonListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaLista.mostrar();
                setVisible(false);
            }
        });


        atras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaIni.setVisible(true);
                setVisible(false);
            }
        });

        // ---------- Cierre de ventana ----------
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
                System.exit(0);
            }
        });

        setVisible(false);
    }

    public VentanaIni getVentanaIni() {
        return ventanaIni;
    }
}
