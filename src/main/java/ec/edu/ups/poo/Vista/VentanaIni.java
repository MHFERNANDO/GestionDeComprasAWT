package ec.edu.ups.poo.Vista;

import ec.edu.ups.poo.Controlador.Persona;
import ec.edu.ups.poo.Controlador.ProductoFisico;
import ec.edu.ups.poo.Controlador.Servicio;
import ec.edu.ups.poo.Vista.Persona.VentanaPersona;
import ec.edu.ups.poo.Vista.Productos.VentanaProdu;
import ec.edu.ups.poo.Vista.Solicitud.VentanaSolicitud;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class VentanaIni extends Frame {

    public ArrayList<Persona> listaEmpleados = new ArrayList<>();
    public ArrayList<Persona> listaProveedores = new ArrayList<>();
    public ArrayList<ProductoFisico> listaProductosFisicos = new ArrayList<>();
    public ArrayList<Servicio> listaServicios = new ArrayList<>();

    private Button botonProveedor;
    private Button botonProducto;
    private Button botonSolicitud;
    private Button botonSalir;

    private VentanaPersona ventanaPersona;
    private VentanaProdu ventanaProducto;
    private VentanaSolicitud ventanaSolicitud;

    public VentanaIni() {
        setTitle("Gestión de Compras");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(245, 245, 245));

        inicializarComponentes();
        inicializarVentanas();
        configurarEventos();

        setVisible(true);
    }

    private void inicializarComponentes() {
        // Encabezado
        Label labelTitulo = new Label("GESTIÓN DE COMPRAS");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        labelTitulo.setAlignment(Label.CENTER);
        labelTitulo.setForeground(new Color(40, 40, 100));

        Panel panelEncabezado = new Panel();
        panelEncabezado.setBackground(new Color(210, 230, 250));
        panelEncabezado.add(labelTitulo);
        add(panelEncabezado, BorderLayout.NORTH);

        // Panel botones
        Panel panelBotones = new Panel(new GridLayout(4, 1, 10, 10));
        panelBotones.setPreferredSize(new Dimension(300, 300));
        panelBotones.setBackground(new Color(245, 245, 255));

        botonProveedor = crearBoton("Proveedores o Empleados");
        botonProducto = crearBoton("Productos");
        botonSolicitud = crearBoton("Solicitud de Compra");
        botonSalir = crearBoton("Salir");

        panelBotones.add(botonProveedor);
        panelBotones.add(botonProducto);
        panelBotones.add(botonSolicitud);
        panelBotones.add(botonSalir);

        Panel panelCentro = new Panel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        panelCentro.add(panelBotones);
        add(panelCentro, BorderLayout.CENTER);
    }

    private Button crearBoton(String texto) {
        Button boton = new Button(texto);
        boton.setFont(new Font("Arial", Font.PLAIN, 16));
        boton.setBackground(new Color(230, 240, 255));
        return boton;
    }

    private void inicializarVentanas() {
        ventanaPersona = new VentanaPersona(this);
        ventanaProducto = new VentanaProdu(this);
        ventanaSolicitud = new VentanaSolicitud(this);
    }

    private void configurarEventos() {
        botonProveedor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ventanaPersona.setVisible(true);
                setVisible(false);
            }
        });

        botonProducto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ventanaProducto.setVisible(true);
                setVisible(false);
            }
        });

        botonSolicitud.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ventanaSolicitud.setVisible(true);
                setVisible(false);
            }
        });

        botonSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose(); // Solo cierra esta ventana
            }
        });
    }

    // Métodos públicos para acceder a las listas
    public ArrayList<Persona> getListaEmpleados() {
        return listaEmpleados;
    }

    public ArrayList<Persona> getListaProveedores() {
        return listaProveedores;
    }

    public ArrayList<ProductoFisico> getListaProductosFisicos() {
        return listaProductosFisicos;
    }

    public ArrayList<Servicio> getListaServicios() {
        return listaServicios;
    }
}
