package ec.edu.ups.poo.Vista.Productos;

import ec.edu.ups.poo.Vista.VentanaIni;
import ec.edu.ups.poo.Controlador.ProductoFisico;
import ec.edu.ups.poo.Controlador.Persona;

import java.awt.*;
import java.awt.event.*;

public class VentanaProductoFisico extends Frame {

    private TextField txtId;
    private TextField txtNombre;
    private TextField txtPrecio;
    private TextField txtCantidad;
    private TextField txtDescripcion;
    private TextField txtPresentacion;
    private Choice choiceProveedor;
    private Button btnRegistrar;
    private Button btnAtras;

    private Frame ventanaAnterior;
    private VentanaIni ventanaIni;

    public VentanaProductoFisico(Frame ventanaAnterior, VentanaIni ventanaIni) {
        this.ventanaAnterior = ventanaAnterior;
        this.ventanaIni = ventanaIni;

        setTitle("Registrar Producto Físico");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setBackground(new Color(245, 245, 255));

        Label titulo = new Label("Registro de Producto Físico");
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setForeground(new Color(30, 30, 30));
        titulo.setAlignment(Label.CENTER);

        Panel panelTitulo = new Panel(new FlowLayout(FlowLayout.CENTER));
        panelTitulo.setBackground(new Color(230, 240, 255));
        panelTitulo.add(titulo);

        Panel panelForm = new Panel(new GridLayout(7, 2, 10, 15));
        panelForm.setBackground(new Color(245, 245, 255));
        panelForm.setFont(new Font("Arial", Font.PLAIN, 16));

        txtId = new TextField(25);
        txtNombre = new TextField(25);
        txtPrecio = new TextField(25);
        txtCantidad = new TextField(25);
        txtDescripcion = new TextField(25);
        txtPresentacion = new TextField(25);
        choiceProveedor = new Choice();

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
        panelForm.add(new Label("Proveedor:"));
        panelForm.add(choiceProveedor);

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
                try {
                    int id = Integer.parseInt(txtId.getText().trim());
                    String nombre = txtNombre.getText().trim();
                    double precio = Double.parseDouble(txtPrecio.getText().trim());
                    int cantidad = Integer.parseInt(txtCantidad.getText().trim());
                    String descripcion = txtDescripcion.getText().trim();
                    String presentacion = txtPresentacion.getText().trim();

                    int indexProveedor = choiceProveedor.getSelectedIndex();
                    Persona proveedorSeleccionado = ventanaIni.getListaProveedores().get(indexProveedor);

                    ProductoFisico producto = new ProductoFisico(id, nombre, precio, cantidad, descripcion, presentacion, proveedorSeleccionado);
                    ventanaIni.getListaProductosFisicos().add(producto);

                    mostrarDialogo("Producto registrado correctamente.");

                    txtId.setText("");
                    txtNombre.setText("");
                    txtPrecio.setText("");
                    txtCantidad.setText("");
                    txtDescripcion.setText("");
                    txtPresentacion.setText("");
                    if (choiceProveedor.getItemCount() > 0) {
                        choiceProveedor.select(0);
                    }
                } catch (NumberFormatException ex) {
                    mostrarDialogo("Error: Verifica que los campos numéricos tengan valores válidos.");
                } catch (IndexOutOfBoundsException ex) {
                    mostrarDialogo("Error: Debes seleccionar un proveedor.");
                }
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

    public void actualizarChoiceProveedores() {
        choiceProveedor.removeAll();
        for (Persona p : ventanaIni.getListaProveedores()) {
            choiceProveedor.add(p.getNombre() + " - " + p.getCedula());
        }
    }

    @Override
    public void setVisible(boolean visible) {
        if (visible) {
            actualizarChoiceProveedores(); // <-- Aquí actualiza cada vez que se muestra
        }
        super.setVisible(visible);
    }

    private void mostrarDialogo(String mensaje) {
        Dialog dialogo = new Dialog(this, "Mensaje", true);
        dialogo.setLayout(new FlowLayout());
        dialogo.setSize(300, 100);
        dialogo.setLocationRelativeTo(this);

        Label lbl = new Label(mensaje);
        Button btnCerrar = new Button("OK");

        btnCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialogo.setVisible(false);
            }
        });

        dialogo.add(lbl);
        dialogo.add(btnCerrar);
        dialogo.setVisible(true);
    }
}
