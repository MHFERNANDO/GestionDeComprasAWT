package ec.edu.ups.poo.Vista.Persona;

import java.awt.*;
import java.awt.event.*;

public class VentanaAgregarPersona extends Frame {

    private Button btnProveedor;
    private Button btnEmpleado;
    private Button atras;

    private VentanaPersona ventanaPersona;

    public VentanaAgregarPersona(VentanaPersona ventanaPersona) {
        this.ventanaPersona = ventanaPersona;

        setTitle("Agregar Persona");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setBackground(new Color(245, 245, 255));

        // Título
        Label titulo = new Label("¿Qué deseas ingresar?");
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setForeground(new Color(30, 30, 30));

        Panel panelTitulo = new Panel(new FlowLayout(FlowLayout.CENTER));
        panelTitulo.setBackground(new Color(245, 245, 255));
        panelTitulo.add(titulo);

        // Botones principales
        btnProveedor = new Button("Ingresar Proveedor");
        btnProveedor.setFont(new Font("Arial", Font.PLAIN, 16));
        btnProveedor.setBackground(new Color(245, 245, 255));
        btnProveedor.setForeground(new Color(30, 30, 30));

        btnEmpleado = new Button("Ingresar Empleado");
        btnEmpleado.setFont(new Font("Arial", Font.PLAIN, 16));
        btnEmpleado.setBackground(new Color(245, 245, 255));
        btnEmpleado.setForeground(new Color(30, 30, 30));

        Panel panelBotones = new Panel(new GridLayout(2, 1, 15, 15));
        panelBotones.setBackground(new Color(245, 245, 255));
        panelBotones.add(btnProveedor);
        panelBotones.add(btnEmpleado);

        Panel panelCentro = new Panel(new FlowLayout(FlowLayout.CENTER));
        panelCentro.setBackground(new Color(245, 245, 255));
        panelCentro.add(panelBotones);

        // Botón salir
        atras = new Button("Atrás");
        atras.setFont(new Font("Arial", Font.PLAIN, 14));
        atras.setBackground(new Color(245, 245, 255));
        atras.setForeground(new Color(30, 30, 30));

        Panel panelAbajo = new Panel(new FlowLayout(FlowLayout.CENTER));
        panelAbajo.setBackground(new Color(245, 245, 255));
        panelAbajo.add(atras);

        // Agregar paneles
        add(panelTitulo, BorderLayout.NORTH);
        add(panelCentro, BorderLayout.CENTER);
        add(panelAbajo, BorderLayout.SOUTH);

        // Acción: volver atrás
        atras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaPersona.setVisible(true);
                setVisible(false);
            }
        });

        // Acción: ir a proveedor
        btnProveedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaProveedor ventanaProveedor = new VentanaProveedor(VentanaAgregarPersona.this);
                ventanaProveedor.setVisible(true);
                setVisible(false);
            }
        });

        // Acción: ir a empleado
        btnEmpleado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaEmpleado ventanaEmpleado = new VentanaEmpleado(VentanaAgregarPersona.this);
                ventanaEmpleado.setVisible(true);
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

