package ec.edu.ups.poo.Vista.Solicitud;

import ec.edu.ups.poo.Controlador.SolicitudCompra;
import ec.edu.ups.poo.Controlador.ProductoFisico;
import ec.edu.ups.poo.Controlador.Servicio;
import ec.edu.ups.poo.Vista.VentanaIni;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class VentanaSolicitud extends Frame {

    private Button botonBuscar;
    private Button botonIngresar;
    private Button botonListar;
    private Button atras;

    VentanaIni ventanaIni;

    private List<SolicitudCompra> listaSolicitudes = new ArrayList<>();

    public AñadirSolicitud ventanaAñadir;
    private ListarSolicitud ventanaListar;
    private BuscarSolicitud ventanaBuscar;

    public VentanaSolicitud(VentanaIni ventanaIni) {
        this.ventanaIni = ventanaIni;

        setTitle("Solicitud de Compra");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        Label textoTitulo = new Label("Solicitud de Compra");
        textoTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        textoTitulo.setAlignment(Label.CENTER);

        Panel encabezado = new Panel();
        encabezado.setBackground(new Color(230, 240, 255));
        encabezado.add(textoTitulo);

        Panel panelFuncional = new Panel(new GridLayout(4, 1, 10, 10));
        panelFuncional.setPreferredSize(new Dimension(200, 200));
        panelFuncional.setFont(new Font("Arial", Font.PLAIN, 18));

        botonBuscar = new Button("Buscar Solicitud de Compra");
        botonBuscar.setBackground(new Color(230, 240, 255));
        botonIngresar = new Button("Añadir Solicitud de Compra");
        botonIngresar.setBackground(new Color(230, 240, 255));
        botonListar = new Button("Listar Solicitud de Compra");
        botonListar.setBackground(new Color(230, 240, 255));
        atras = new Button("Atrás");
        atras.setBackground(new Color(230, 240, 255));

        botonBuscar.setFont(new Font("Arial", Font.PLAIN, 16));
        botonIngresar.setFont(new Font("Arial", Font.PLAIN, 16));
        botonListar.setFont(new Font("Arial", Font.PLAIN, 16));
        atras.setFont(new Font("Arial", Font.PLAIN, 16));

        panelFuncional.add(botonBuscar);
        panelFuncional.add(botonIngresar);
        panelFuncional.add(botonListar);
        panelFuncional.add(atras);

        Panel panelPrincipal = new Panel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        panelPrincipal.add(panelFuncional);

        add(encabezado, BorderLayout.NORTH);
        add(panelPrincipal, BorderLayout.CENTER);

        ventanaAñadir = new AñadirSolicitud(this, listaSolicitudes, ventanaIni.getListaProductosFisicos(), ventanaIni.getListaServicios());
        ventanaListar = new ListarSolicitud(this, listaSolicitudes);
        ventanaBuscar = new BuscarSolicitud(this, listaSolicitudes);

        // Acción botón Añadir
        botonIngresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ventanaAñadir.actualizarLista(); // <- Actualiza antes de mostrar
                ventanaAñadir.setVisible(true);
                setVisible(false);
            }
        });

        // Acción botón Listar
        botonListar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ventanaListar.mostrar();
                ventanaListar.setVisible(true);
                setVisible(false);
            }
        });

        // Acción botón Buscar
        botonBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ventanaBuscar.setVisible(true);
                setVisible(false);
            }
        });

        // Acción botón Atrás
        atras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ventanaIni.setVisible(true);
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

    public String mostrarProductosYServiciosDisponibles() {
        StringBuilder contenido = new StringBuilder();

        List<ProductoFisico> productos = ventanaIni.listaProductosFisicos;
        List<Servicio> servicios = ventanaIni.listaServicios;

        contenido.append("=== PRODUCTOS DISPONIBLES ===\n");
        if (productos.isEmpty()) {
            contenido.append("No hay productos registrados.\n");
        } else {
            for (ProductoFisico p : productos) {
                contenido.append(p.toString()).append("\n");
            }
        }

        contenido.append("\n=== SERVICIOS DISPONIBLES ===\n");
        if (servicios.isEmpty()) {
            contenido.append("No hay servicios registrados.\n");
        } else {
            for (Servicio s : servicios) {
                contenido.append(s.toString()).append("\n");
            }
        }

        return contenido.toString();
    }

}
