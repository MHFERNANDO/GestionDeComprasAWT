package ec.edu.ups.poo.Vista.Persona;

import ec.edu.ups.poo.Vista.VentanaIni;
import ec.edu.ups.poo.Controlador.Persona;

import java.awt.*;
import java.awt.event.*;

public class VentanaProveedor extends Frame {

    private TextField txtCedula;
    private TextField txtNombre;
    private Button btnRegistrar;
    private Button btnAtras;

    public VentanaProveedor(Frame ventanaAnterior, VentanaIni ventanaIni) {
        setTitle("Registrar Proveedor");
        setSize(500, 400);
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
        Panel panelForm = new Panel(new GridLayout(2, 2, 15, 15));
        panelForm.setBackground(new Color(245, 245, 255));
        panelForm.setFont(new Font("Arial", Font.PLAIN, 16));

        Label lblCedula = new Label("Cédula:");
        txtCedula = new TextField(10);

        Label lblNombre = new Label("Nombre:");
        txtNombre = new TextField(25);

        panelForm.add(lblCedula);
        panelForm.add(txtCedula);
        panelForm.add(lblNombre);
        panelForm.add(txtNombre);

        // Panel centro
        Panel panelCentro = new Panel(new BorderLayout());
        panelCentro.setBackground(new Color(245, 245, 255));
        panelCentro.add(panelForm, BorderLayout.CENTER);

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

        // Funcionalidad del botón Registrar
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cedula = txtCedula.getText().trim();
                String nombre = txtNombre.getText().trim();

                if (!cedula.isEmpty() && !nombre.isEmpty()) {
                    Persona proveedor = new Persona(cedula, nombre);
                    ventanaIni.getListaProveedores().add(proveedor);
                    mostrarDialogo("Proveedor registrado correctamente.");

                    txtCedula.setText("");
                    txtNombre.setText("");
                } else {
                    mostrarDialogo("Por favor, complete todos los campos.");
                }
            }
        });

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
}
