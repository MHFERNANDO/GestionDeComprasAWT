package ec.edu.ups.poo.Vista.Productos;

import ec.edu.ups.poo.Vista.VentanaIni;
import java.awt.*;
import java.awt.event.*;

public class VentanaProdu extends Frame {

    private Button botonBuscar;
    private Button botonIngresar;
    private Button botonListar;
    private Button botonAtras;

    private VentanaIni ventanaIni;

    private BuscarProducto buscarProducto;
    private VentanaTipoProducto ventanaAgregarProducto;
    private VentanaListaProducto ventanaListaProducto;

    public VentanaProdu(VentanaIni ventanaIni) {
        this.ventanaIni = ventanaIni;

        setTitle("Gestión de Productos");
        setSize(600, 450);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(245, 250, 255));

        crearEncabezado();
        crearBotones();
        crearVentanasAuxiliares();
        agregarListeners();

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
                System.exit(0);
            }
        });

        setVisible(false);
    }

    private void crearEncabezado() {
        Label titulo = new Label("GESTIÓN DE PRODUCTOS");
        titulo.setFont(new Font("Arial", Font.BOLD, 26));
        titulo.setAlignment(Label.CENTER);
        titulo.setForeground(new Color(30, 40, 90));

        Panel encabezado = new Panel();
        encabezado.setBackground(new Color(210, 230, 250));
        encabezado.setLayout(new FlowLayout(FlowLayout.CENTER));
        encabezado.add(titulo);

        add(encabezado, BorderLayout.NORTH);
    }

    private void crearBotones() {
        botonBuscar = new Button("Buscar Productos");
        botonIngresar = new Button("Añadir Productos");
        botonListar = new Button("Listar Productos");
        botonAtras = new Button("⬅ Atrás");

        Button[] botones = {botonBuscar, botonIngresar, botonListar, botonAtras};
        Panel panelFuncional = new Panel(new GridLayout(4, 1, 20, 20));
        panelFuncional.setBackground(new Color(235, 245, 255));
        panelFuncional.setPreferredSize(new Dimension(300, 250));

        for (Button btn : botones) {
            btn.setFont(new Font("Arial", Font.BOLD, 16));
            btn.setBackground(new Color(200, 220, 255));
            btn.setForeground(new Color(20, 20, 20));
            panelFuncional.add(btn);
        }

        Panel panelCentro = new Panel(new FlowLayout(FlowLayout.CENTER, 20, 30));
        panelCentro.setBackground(new Color(245, 250, 255));
        panelCentro.add(panelFuncional);
        add(panelCentro, BorderLayout.CENTER);
    }

    private void crearVentanasAuxiliares() {
        buscarProducto = new BuscarProducto(this, ventanaIni);
        ventanaAgregarProducto = new VentanaTipoProducto(this, ventanaIni);
        ventanaListaProducto = new VentanaListaProducto(this, ventanaIni);
    }

    private void agregarListeners() {
        botonBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buscarProducto.setVisible(true);
                setVisible(false);
            }
        });

        botonIngresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ventanaAgregarProducto.setVisible(true);
                setVisible(false);
            }
        });

        botonListar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ventanaListaProducto.mostrar();
                setVisible(false);
            }
        });

        botonAtras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ventanaIni.setVisible(true);
                setVisible(false);
            }
        });
    }

    public VentanaIni getVentanaIni() {
        return ventanaIni;
    }
}
