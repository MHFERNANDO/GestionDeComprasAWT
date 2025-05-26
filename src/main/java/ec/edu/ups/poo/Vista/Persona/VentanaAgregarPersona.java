package ec.edu.ups.poo.Vista.Persona;

import ec.edu.ups.poo.Vista.VentanaIni;

import java.awt.*;
import java.awt.event.*;

public class VentanaAgregarPersona extends Frame {

    private Button btnEmpleado;
    private Button btnProveedor;
    private Button btnAtras;

    private VentanaPersona ventanaPersona;
    private VentanaIni ventanaIni;

    public VentanaAgregarPersona(VentanaPersona ventanaPersona) {
        this.ventanaPersona = ventanaPersona;
        this.ventanaIni = ventanaPersona.getVentanaIni();

        setTitle("Agregar Persona");
        setSize(500, 300);
        setLayout(new BorderLayout(15, 15));
        setLocationRelativeTo(null);
        setBackground(new Color(245, 250, 255));

        Label lblTitulo = new Label("Seleccione el tipo de persona a registrar", Label.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(30, 30, 30));

        Panel panelTitulo = new Panel(new FlowLayout(FlowLayout.CENTER));
        panelTitulo.setBackground(new Color(230, 240, 255));
        panelTitulo.add(lblTitulo);

        btnEmpleado = crearBoton("Registrar Empleado");
        btnProveedor = crearBoton("Registrar Proveedor");
        btnAtras = crearBoton("Atrás");

        Panel panelBotones = new Panel(new FlowLayout(FlowLayout.CENTER, 40, 20));
        panelBotones.setBackground(new Color(245, 250, 255));
        panelBotones.add(btnEmpleado);
        panelBotones.add(btnProveedor);

        Panel panelInferior = new Panel(new FlowLayout(FlowLayout.CENTER));
        panelInferior.setBackground(new Color(230, 240, 255));
        panelInferior.add(btnAtras);

        add(panelTitulo, BorderLayout.NORTH);
        add(panelBotones, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);

        btnEmpleado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaEmpleado ventanaEmpleado = new VentanaEmpleado(VentanaAgregarPersona.this, ventanaIni);
                ventanaEmpleado.setVisible(true);
                setVisible(false);
            }
        });

        btnProveedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaProveedor ventanaProveedor = new VentanaProveedor(VentanaAgregarPersona.this, ventanaIni);
                ventanaProveedor.setVisible(true);
                setVisible(false);
            }
        });

        btnAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaPersona.setVisible(true);
                setVisible(false);
            }
        });

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });

        setVisible(false);
    }

    private Button crearBoton(String texto) {
        Button boton = new Button(texto);
        boton.setFont(new Font("Arial", Font.BOLD, 16));
        boton.setBackground(new Color(180, 210, 255));
        boton.setForeground(Color.BLACK);
        boton.setPreferredSize(new Dimension(180, 40));
        return boton;
    }
}
