package ec.edu.ups.poo.Vista.Persona;

import ec.edu.ups.poo.Vista.VentanaIni;

import java.awt.*;
import java.awt.event.*;

public class VentanaProveedor extends Frame {

    private TextField txtCedula;
    private TextField txtNombre;
    private TextField txtEmpresa;
    private Button btnRegistrar;
    private Button btnAtras;

    public VentanaProveedor(Frame ventanaAnterior, VentanaIni ventanaIni) {
        setTitle("Registrar Proveedor");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setBackground(new Color(245, 245, 255));

        // Título
        Label titulo = new Label("Registro de Proveedor");
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setForeground(new Color(30, 30, 30));

        Panel panelTitulo = new Panel(new FlowLayout(FlowLayout.CENTER));
        panelTitulo.setBackground(new Color(230, 240, 255));
        panelTitulo.add(titulo);

        // Formulario
        Panel panelForm = new Panel(new GridLayout(3, 2, 15, 15));
        panelForm.setBackground(new Color(245, 245, 255));
        panelForm.setFont(new Font("Arial", Font.PLAIN, 16));

        Label lblCedula = new Label("Cédula:");
        txtCedula = new TextField(10);

        Label lblNombre = new Label("Nombre:");
        txtNombre = new TextField(25);

        Label lblEmpresa = new Label("Empresa:");
        txtEmpresa = new TextField(25);

        panelForm.add(lblCedula);
        panelForm.add(txtCedula);
        panelForm.add(lblNombre);
        panelForm.add(txtNombre);
        panelForm.add(lblEmpresa);
        panelForm.add(txtEmpresa);

        // Panel centro con margen y alineación
        Panel panelCentro = new Panel(new BorderLayout());
        panelCentro.setBackground(new Color(245, 245, 255));
        panelCentro.add(panelForm, BorderLayout.CENTER);
        //panelCentro.setPreferredSize(new Dimension(400, 250));

        // Botones
        btnRegistrar = new Button("Registrar");
        btnRegistrar.setFont(new Font("Arial", Font.PLAIN, 14));

        btnAtras = new Button("Atrás");
        btnAtras.setFont(new Font("Arial", Font.PLAIN, 14));

        Panel panelBotones = new Panel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelBotones.setBackground(new Color(230, 240, 255));
        panelBotones.add(btnRegistrar);
        panelBotones.add(btnAtras);

        // Agregar a la ventana
        add(panelTitulo, BorderLayout.NORTH);
        add(panelCentro, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        // Funcionalidad del botón Atrás
        btnAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaAnterior.setVisible(true);
                setVisible(false);
            }
        });

        // Cerrar ventana
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
                System.exit(0);
            }
        });

        setVisible(false);
    }
}

