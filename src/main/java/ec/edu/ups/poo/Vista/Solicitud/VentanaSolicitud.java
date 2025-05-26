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
    private Button botonAtras;

    private VentanaIni ventanaIni;
    private List<SolicitudCompra> listaSolicitudes = new ArrayList<>();

    private AñadirSolicitud ventanaAñadir;
    private ListarSolicitud ventanaListar;
    private BuscarSolicitud ventanaBuscar;

    public VentanaSolicitud(VentanaIni ventanaIni) {
        this.ventanaIni = ventanaIni;

        setTitle("Gestión de Solicitudes de Compra");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(245, 245, 245));

        inicializarComponentes();
        agregarEventos();
    }

    private void inicializarComponentes() {
        Label titulo = new Label("Solicitud de Compra");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setAlignment(Label.CENTER);

        Panel panelTitulo = new Panel();
        panelTitulo.setBackground(new Color(200, 220, 255));
        panelTitulo.add(titulo);
        add(panelTitulo, BorderLayout.NORTH);

        botonBuscar = crearBoton("Buscar Solicitud de Compra");
        botonIngresar = crearBoton("Añadir Solicitud de Compra");
        botonListar = crearBoton("Listar Solicitud de Compra");
        botonAtras = crearBoton("Atrás");

        Panel panelBotones = new Panel(new GridLayout(4, 1, 10, 10));
        panelBotones.setPreferredSize(new Dimension(250, 200));
        panelBotones.add(botonBuscar);
        panelBotones.add(botonIngresar);
        panelBotones.add(botonListar);
        panelBotones.add(botonAtras);

        Panel panelCentro = new Panel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        panelCentro.add(panelBotones);
        add(panelCentro, BorderLayout.CENTER);

        // Instancias de ventanas hijas
        ventanaAñadir = new AñadirSolicitud(this, listaSolicitudes,
                ventanaIni.getListaProductosFisicos(), ventanaIni.getListaServicios());
        ventanaListar = new ListarSolicitud(this, listaSolicitudes);
        ventanaBuscar = new BuscarSolicitud(this, listaSolicitudes);

        setVisible(false);
    }

    private Button crearBoton(String texto) {
        Button boton = new Button(texto);
        boton.setFont(new Font("Arial", Font.PLAIN, 16));
        boton.setBackground(new Color(220, 235, 255));
        return boton;
    }

    private void agregarEventos() {
        botonIngresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ventanaAñadir.actualizarLista();
                ventanaAñadir.setVisible(true);
                setVisible(false);
            }
        });

        botonListar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ventanaListar.mostrar();
                ventanaListar.setVisible(true);
                setVisible(false);
            }
        });

        botonBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ventanaBuscar.setVisible(true);
                setVisible(false);
            }
        });

        botonAtras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ventanaIni.setVisible(true);
                setVisible(false);
            }
        });

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose(); // solo cierra esta ventana, no todo el sistema
            }
        });
    }

    public String mostrarProductosYServiciosDisponibles() {
        StringBuilder contenido = new StringBuilder();

        List<ProductoFisico> productos = ventanaIni.getListaProductosFisicos();
        List<Servicio> servicios = ventanaIni.getListaServicios();

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
