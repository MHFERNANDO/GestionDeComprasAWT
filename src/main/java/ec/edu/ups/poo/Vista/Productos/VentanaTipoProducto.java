package ec.edu.ups.poo.Vista.Productos;

import java.awt.*;
import java.awt.event.*;
import ec.edu.ups.poo.Vista.VentanaIni;

public class VentanaTipoProducto extends Frame {

    private Button btnProductoFisico;
    private Button btnServicio;
    private Button btnAtras;
    private Frame ventanaAnterior;
    private VentanaIni ventanaIni;

    private VentanaProductoFisico ventanaProductoFisico;
    private VentanaServicio ventanaServicio;

    public VentanaTipoProducto(Frame ventanaAnterior, VentanaIni ventanaIni) {
        this.ventanaAnterior = ventanaAnterior;
        this.ventanaIni = ventanaIni;

        configurarVentana();
        inicializarComponentes();
        agregarEventos();
    }

    private void configurarVentana() {
        setTitle("Tipo de Producto");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(245, 245, 245));

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });

        setVisible(false);
    }

    private void inicializarComponentes() {
        // Título
        Label labelTitulo = new Label("Seleccione el tipo de producto");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        labelTitulo.setAlignment(Label.CENTER);
        labelTitulo.setForeground(new Color(40, 40, 100));

        Panel panelTitulo = new Panel(new FlowLayout(FlowLayout.CENTER));
        panelTitulo.setBackground(new Color(210, 230, 250));
        panelTitulo.add(labelTitulo);
        add(panelTitulo, BorderLayout.NORTH);

        // Botones
        btnProductoFisico = new Button("Producto Físico");
        btnServicio = new Button("Servicio");
        btnAtras = new Button("Atrás");

        Button[] botones = {btnProductoFisico, btnServicio, btnAtras};
        for (Button btn : botones) {
            btn.setFont(new Font("Arial", Font.PLAIN, 16));
            btn.setBackground(new Color(230, 240, 255));
            btn.setPreferredSize(new Dimension(200, 40));
        }

        Panel panelBotones = new Panel(new GridLayout(3, 1, 15, 15));
        panelBotones.setBackground(new Color(245, 245, 255));
        for (Button btn : botones) {
            panelBotones.add(btn);
        }

        Panel panelCentral = new Panel(new FlowLayout(FlowLayout.CENTER, 20, 40));
        panelCentral.setBackground(new Color(245, 245, 255));
        panelCentral.add(panelBotones);

        add(panelCentral, BorderLayout.CENTER);

        // Crear ventanas hijas
        ventanaProductoFisico = new VentanaProductoFisico(this, ventanaIni);
        ventanaServicio = new VentanaServicio(this, ventanaIni);
    }

    private void agregarEventos() {
        btnProductoFisico.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ventanaProductoFisico.setVisible(true);
                setVisible(false);
            }
        });

        btnServicio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ventanaServicio.setVisible(true);
                setVisible(false);
            }
        });

        btnAtras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ventanaAnterior.setVisible(true);
                setVisible(false);
            }
        });
    }
}
