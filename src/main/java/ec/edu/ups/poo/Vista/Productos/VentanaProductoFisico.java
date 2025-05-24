package ec.edu.ups.poo.Vista.Productos;

import java.awt.*;
import java.awt.event.*;

public class VentanaProductoFisico extends Frame {

    private TextField txtId;
    private TextField txtNombre;
    private TextField txtPrecio;
    private TextField txtCantidad;
    private TextField txtDescripcion;
    private TextField txtPresentacion;
    private Button btnRegistrar;
    private Button btnAtras;

    public VentanaProductoFisico(Frame ventanaAnterior) {
        setTitle("Registrar Producto Físico");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setBackground(new Color(245, 245, 255));

        // Título
        Label titulo = new Label("Registro de Producto Físico");
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setForeground(new Color(30, 30, 30));
        titulo.setAlignment(Label.CENTER);

        Panel panelTitulo = new Panel(new FlowLayout(FlowLayout.CENTER));
        panelTitulo.setBackground(new Color(230, 240, 255));
        panelTitulo.add(titulo);

        // Formulario
        Panel panelForm = new Panel(new GridLayout(6, 2, 10, 15));
        panelForm.setBackground(new Color(245, 245, 255));
        panelForm.setFont(new Font("Arial", Font.PLAIN, 16));

        txtId = new TextField(25);
        txtNombre = new TextField(25);
        txtPrecio = new TextField(25);
        txtCantidad = new TextField(25);
        txtDescripcion = new TextField(25);
        txtPresentacion = new TextField(25);

        panelForm.add(new Label("ID del Producto:"));
        panelForm.add(txtId);
        panelForm.add(new Label("Nombre:"));
        panelForm.add(txtNombre);
        panelForm.add(new Label("Precio Unitario:"));
        panelForm.add(txtPrecio);
        panelForm.add(new Label("Cantidad:"));
        panelForm.add(txtCantidad);
        panelForm.add(new Label("Descripción:"));
        panelForm.add(txtDescripcion);
        panelForm.add(new Label("Presentación:"));
        panelForm.add(txtPresentacion);

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
        add(panelForm, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        // Acción botón Atrás
        btnAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaAnterior.setVisible(true);
                setVisible(false);
            }
        });

        // Cierre de ventana
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });

        setVisible(false);
    }
}

