package ec.edu.ups.poo.Vista;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VentanaIni extends Frame {

    private Button botonProveedor;
    private Button botonEmpleado;
    private Button botonProducto;
    private Button botonDetalles;
    private Button botonSalir;

    public VentanaIni() {
        setTitle("Detalles de Compra");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setBackground(new Color(245, 245, 245)); // Fondo claro
        setLayout(new BorderLayout(10, 10));

        // Encabezado
        Label labelTitulo = new Label("GESTION DE COMPRAS");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        labelTitulo.setAlignment(Label.CENTER);
        labelTitulo.setForeground(new Color(30, 30, 30));

        Panel panelEncabezado = new Panel();
        panelEncabezado.setBackground(new Color(220, 220, 220));
        panelEncabezado.add(labelTitulo);

        // Panel funcional con botones
        Panel panelFuncional = new Panel(new GridLayout(5, 1, 10, 10));
        panelFuncional.setBackground(new Color(245, 245, 245));
        panelFuncional.setPreferredSize(new Dimension(300, 300));
        panelFuncional.setFont(new Font("Arial", Font.PLAIN, 18));
        panelFuncional.setForeground(Color.BLACK);

        botonProveedor = new Button("Proveedores");
        botonEmpleado = new Button("Empleados");
        botonProducto = new Button("Productos");
        botonDetalles = new Button("Detalle de Compra");
        botonSalir = new Button("Salir");

        // Estilo uniforme para botones
        Button[] botones = {botonProveedor, botonEmpleado, botonProducto, botonDetalles, botonSalir};
        for (Button boton : botones) {
            boton.setFont(new Font("Arial", Font.PLAIN, 16));
            panelFuncional.add(boton);
        }

        // Panel central con margen
        Panel panelCentral = new Panel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        panelCentral.add(panelFuncional);

        add(panelEncabezado, BorderLayout.NORTH);
        add(panelCentral, BorderLayout.CENTER);

        // Evento para cerrar ventana correctamente
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });

        setVisible(true);
    }
}
