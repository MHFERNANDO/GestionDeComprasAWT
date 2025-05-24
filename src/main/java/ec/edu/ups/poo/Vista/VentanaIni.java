package ec.edu.ups.poo.Vista;

import ec.edu.ups.poo.Vista.Persona.VentanaPersona;
import ec.edu.ups.poo.Vista.Productos.VentanaProdu;
import ec.edu.ups.poo.Vista.Solicitud.VentanaSolicitud;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VentanaIni extends Frame {

    private Button botonProveedor;
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
        labelTitulo.setForeground(new Color(40, 40, 100));

        Panel panelEncabezado = new Panel();
        panelEncabezado.setBackground(new Color(210, 230, 250));
        panelEncabezado.add(labelTitulo);

        // Panel funcional con botones
        Panel panelFuncional = new Panel(new GridLayout(5, 1, 10, 10));
        panelFuncional.setBackground(new Color(245, 245, 255));
        panelFuncional.setPreferredSize(new Dimension(300, 300));
        panelFuncional.setFont(new Font("Arial", Font.PLAIN, 18));
        panelFuncional.setForeground(Color.BLACK);

        botonProveedor = new Button("Proveedores o Empleados");
        botonProducto = new Button("Productos");
        botonDetalles = new Button("Detalle de Compra");
        botonSalir = new Button("Salir");

        // Estilo uniforme para botones
        Button[] botones = {botonProveedor, botonProducto, botonDetalles, botonSalir};
        for (Button boton : botones) {
            boton.setFont(new Font("Arial", Font.PLAIN, 16));
            boton.setBackground(new Color(230,240,255));
            panelFuncional.add(boton);
        }


        Panel panelCentral = new Panel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        panelCentral.add(panelFuncional);

        add(panelEncabezado, BorderLayout.NORTH);
        add(panelCentral, BorderLayout.CENTER);

        VentanaPersona ventanaPer = new VentanaPersona(this);
        VentanaProdu ventanaProdu=new VentanaProdu(this);
        VentanaSolicitud ventanaDetalle=new VentanaSolicitud(this);

        botonProveedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaPer.setVisible(true);
                setVisible(false);
            }
        });
        botonProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaProdu.setVisible(true);
                setVisible(false);
            }
        });
        botonDetalles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaDetalle.setVisible(true);
                setVisible(false);
            }
        });
        botonSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Evento para cerrar ventana correctamente
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });

        setVisible(true);
    }
}
