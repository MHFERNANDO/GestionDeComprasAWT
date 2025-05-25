package ec.edu.ups.poo.Vista.Productos;

import ec.edu.ups.poo.Vista.VentanaIni;

import java.awt.*;
import java.awt.event.*;

public class VentanaProdu extends Frame {

    private Button botonBuscar;
    private Button botonIngresar;
    private Button botonListar;
    private Button atras;

    private VentanaIni ventanaIni;

    public VentanaProdu(VentanaIni ventanaIni) {
        this.ventanaIni = ventanaIni;

        setTitle("Gestión de Productos");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(245, 245, 245));

        // ---------- Encabezado ----------
        Label textoTitulo = new Label("GESTIÓN DE PRODUCTOS");
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

        botonBuscar = new Button("Buscar Productos");
        botonIngresar = new Button("Añadir Productos");
        botonListar = new Button("Listar Productos");
        atras = new Button("Atras");

        for (Button boton : new Button[]{botonBuscar, botonIngresar, botonListar, atras}) {
            boton.setFont(new Font("Arial", Font.BOLD, 16));
            boton.setBackground(new Color(230, 240, 255));
            boton.setForeground(Color.BLACK);
            panelFuncional.add(boton);
        }

        Panel panelCentro = new Panel(new FlowLayout(FlowLayout.CENTER, 20, 30));
        panelCentro.add(panelFuncional);
        add(panelCentro, BorderLayout.CENTER);

        // ---------- Ventanas auxiliares ----------
        BuscarProducto buscarProducto = new BuscarProducto(this, ventanaIni);
        VentanaTipoProducto ventanaTipoProducto = new VentanaTipoProducto(this);
        VentanaListaProducto ventanaListaProducto = new VentanaListaProducto(this, ventanaIni);

        // ---------- Eventos de botones ----------
        botonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarProducto.setVisible(true);
                setVisible(false);
            }
        });

        botonIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaTipoProducto.setVisible(true);
                setVisible(false);
            }
        });

        botonListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaListaProducto.mostrar(); // Usa método mostrar como en Persona
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
