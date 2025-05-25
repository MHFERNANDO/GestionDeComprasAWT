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

    public VentanaTipoProducto(Frame ventanaAnterior, VentanaIni ventanaIni) {
        this.ventanaAnterior = ventanaAnterior;
        this.ventanaIni = ventanaIni;

        setTitle("Tipo de Producto");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setBackground(new Color(245, 245, 245));
        setLayout(new BorderLayout(10, 10));

        // Encabezado
        Label labelTitulo = new Label("Seleccione el tipo de producto");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        labelTitulo.setAlignment(Label.CENTER);
        labelTitulo.setForeground(new Color(40, 40, 100));

        Panel panelEncabezado = new Panel();
        panelEncabezado.setBackground(new Color(210, 230, 250));
        panelEncabezado.add(labelTitulo);

        // Panel botones
        Panel panelBotones = new Panel(new GridLayout(3, 1, 10, 10));
        panelBotones.setBackground(new Color(245, 245, 255));
        panelBotones.setPreferredSize(new Dimension(250, 150));

        btnProductoFisico = new Button("Producto Físico");
        btnServicio = new Button("Servicio");
        btnAtras = new Button("Atrás");

        Button[] botones = {btnProductoFisico, btnServicio, btnAtras};
        for (Button btn : botones) {
            btn.setFont(new Font("Arial", Font.PLAIN, 16));
            btn.setBackground(new Color(230, 240, 255));
            panelBotones.add(btn);
        }

        Panel panelCentral = new Panel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        panelCentral.add(panelBotones);

        add(panelEncabezado, BorderLayout.NORTH);
        add(panelCentral, BorderLayout.CENTER);

        // Crear ventanas de registro pasando ventanaIni
        VentanaProductoFisico ventanaProductoFisico = new VentanaProductoFisico(this, ventanaIni);
        VentanaServicio ventanaServicio = new VentanaServicio(this, ventanaIni);

        btnProductoFisico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaProductoFisico.setVisible(true);
                setVisible(false);
            }
        });

        btnServicio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaServicio.setVisible(true);
                setVisible(false);
            }
        });

        btnAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaAnterior.setVisible(true);
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
}
