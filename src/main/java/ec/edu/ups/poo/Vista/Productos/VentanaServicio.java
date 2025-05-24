package ec.edu.ups.poo.Vista.Productos;

import java.awt.*;
import java.awt.event.*;

public class VentanaServicio extends Frame {

    private TextField txtId;
    private TextField txtNombre;
    private TextField txtPrecio;
    private TextField txtCantidad;
    private TextField txtTipo;
    private TextField txtCategoria;
    private Button btnRegistrar;
    private Button btnAtras;
    private Frame ventanaAnterior;

    public VentanaServicio(Frame ventanaAnterior) {
        this.ventanaAnterior = ventanaAnterior;

        setTitle("Registrar Servicio");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setBackground(new Color(245, 245, 255));

        // Título
        Label titulo = new Label("Registro de Servicio");
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
        txtTipo = new TextField(25);
        txtCategoria = new TextField(25);

        panelForm.add(new Label("ID del Servicio:"));
        panelForm.add(txtId);
        panelForm.add(new Label("Nombre:"));
        panelForm.add(txtNombre);
        panelForm.add(new Label("Precio Unitario:"));
        panelForm.add(txtPrecio);
        panelForm.add(new Label("Cantidad:"));
        panelForm.add(txtCantidad);
        panelForm.add(new Label("Tipo de Servicio:"));
        panelForm.add(txtTipo);
        panelForm.add(new Label("Categoría:"));
        panelForm.add(txtCategoria);

        // Botones
        btnRegistrar = new Button("Registrar");
        btnRegistrar.setFont(new Font("Arial", Font.PLAIN, 14));

        btnAtras = new Button("Atrás");
        btnAtras.setFont(new Font("Arial", Font.PLAIN, 14));

        Panel panelBotones = new Panel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelBotones.setBackground(new Color(230, 240, 255));
        panelBotones.add(btnRegistrar);
        panelBotones.add(btnAtras);

        // Agregar componentes a la ventana
        add(panelTitulo, BorderLayout.NORTH);
        add(panelForm, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        // Acción botón Registrar (de momento solo mensaje de éxito)
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí puedes capturar los datos ingresados para usarlos en el modelo
                System.out.println("Servicio registrado correctamente.");
                // Limpia los campos si deseas
                txtId.setText("");
                txtNombre.setText("");
                txtPrecio.setText("");
                txtCantidad.setText("");
                txtTipo.setText("");
                txtCategoria.setText("");
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

