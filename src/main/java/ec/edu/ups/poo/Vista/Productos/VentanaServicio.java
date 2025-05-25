package ec.edu.ups.poo.Vista.Productos;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class VentanaServicio extends Frame {

    private TextField txtId;
    private TextField txtNombre;
    private TextField txtPrecio;
    private TextField txtCantidad;
    private TextField txtTipo;
    private TextField txtCategoria;
    private Button btnRegistrar;
    private Button btnAtras;
    private static List<String> servicios = new ArrayList<>();

    public VentanaServicio(Frame ventanaAnterior) {
        setTitle("Registrar Servicio");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setBackground(new Color(245, 245, 255));

        Label titulo = new Label("Registro de Servicio");
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setForeground(new Color(30, 30, 30));
        titulo.setAlignment(Label.CENTER);

        Panel panelTitulo = new Panel(new FlowLayout(FlowLayout.CENTER));
        panelTitulo.setBackground(new Color(230, 240, 255));
        panelTitulo.add(titulo);

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

        btnRegistrar = new Button("Registrar");
        btnRegistrar.setFont(new Font("Arial", Font.PLAIN, 14));

        btnAtras = new Button("Atrás");
        btnAtras.setFont(new Font("Arial", Font.PLAIN, 14));

        Panel panelBotones = new Panel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelBotones.setBackground(new Color(230, 240, 255));
        panelBotones.add(btnRegistrar);
        panelBotones.add(btnAtras);

        add(panelTitulo, BorderLayout.NORTH);
        add(panelForm, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String servicio = "ID: " + txtId.getText() + ", Nombre: " + txtNombre.getText()
                        + ", Precio: " + txtPrecio.getText() + ", Cantidad: " + txtCantidad.getText()
                        + ", Tipo: " + txtTipo.getText() + ", Categoría: " + txtCategoria.getText();

                servicios.add(servicio);
                System.out.println("Servicio registrado:");
                System.out.println(servicio);

                mostrarDialogo("Servicio registrado correctamente.");

                txtId.setText("");
                txtNombre.setText("");
                txtPrecio.setText("");
                txtCantidad.setText("");
                txtTipo.setText("");
                txtCategoria.setText("");
            }
        });


        btnAtras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ventanaAnterior.setVisible(true);
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
    private void mostrarDialogo(String mensaje) {
        Dialog dialogo = new Dialog(this, "Mensaje", true);
        dialogo.setLayout(new FlowLayout());
        dialogo.setSize(300, 100);
        dialogo.setLocationRelativeTo(this);

        Label lbl = new Label(mensaje);
        Button btnCerrar = new Button("OK");
        btnCerrar.addActionListener(e -> dialogo.setVisible(false));

        dialogo.add(lbl);
        dialogo.add(btnCerrar);
        dialogo.setVisible(true);
    }


    public static List<String> getServicios() {
        return servicios;
    }
}
