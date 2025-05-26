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
        setSize(600, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(245, 250, 255));

        crearTitulo();
        crearFormulario();
        crearBotones();
        configurarEventos();

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });

        setVisible(false);
    }

    private void crearTitulo() {
        Label titulo = new Label(" Registro de Producto Físico");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setAlignment(Label.CENTER);
        titulo.setForeground(new Color(30, 40, 90));

        Panel panelTitulo = new Panel(new FlowLayout(FlowLayout.CENTER));
        panelTitulo.setBackground(new Color(210, 230, 250));
        panelTitulo.add(titulo);
        add(panelTitulo, BorderLayout.NORTH);
    }

    private void crearFormulario() {
        Panel panelForm = new Panel(new GridLayout(7, 2, 12, 15));
        panelForm.setBackground(new Color(245, 250, 255));
        panelForm.setFont(new Font("Arial", Font.PLAIN, 16));
        panelForm.setPreferredSize(new Dimension(400, 300));

        txtId = new TextField(25);
        txtNombre = new TextField(25);
        txtPrecio = new TextField(25);
        txtCantidad = new TextField(25);
        txtDescripcion = new TextField(25);
        txtPresentacion = new TextField(25);
        choiceProveedor = new Choice();

        panelForm.add(new Label(" ID del Producto:"));
        panelForm.add(txtId);
        panelForm.add(new Label(" Nombre:"));
        panelForm.add(txtNombre);
        panelForm.add(new Label(" Precio Unitario:"));
        panelForm.add(txtPrecio);
        panelForm.add(new Label(" Cantidad:"));
        panelForm.add(txtCantidad);
        panelForm.add(new Label(" Descripción:"));
        panelForm.add(txtDescripcion);
        panelForm.add(new Label(" Presentación:"));
        panelForm.add(txtPresentacion);
        panelForm.add(new Label(" Proveedor:"));
        panelForm.add(choiceProveedor);

        Panel centro = new Panel(new FlowLayout(FlowLayout.CENTER));
        centro.add(panelForm);
        add(centro, BorderLayout.CENTER);
    }

    private void crearBotones() {
        btnRegistrar = new Button(" Registrar");
        btnRegistrar.setFont(new Font("Arial", Font.BOLD, 14));
        btnRegistrar.setBackground(new Color(200, 230, 255));

        btnAtras = new Button("⬅ Atrás");
        btnAtras.setFont(new Font("Arial", Font.BOLD, 14));
        btnAtras.setBackground(new Color(220, 220, 220));

        Panel panelBotones = new Panel(new FlowLayout(FlowLayout.CENTER, 30, 15));
        panelBotones.setBackground(new Color(230, 240, 255));
        panelBotones.add(btnRegistrar);
        panelBotones.add(btnAtras);

        add(panelBotones, BorderLayout.SOUTH);
    }

    private void configurarEventos() {
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

                    mostrarDialogo(" Producto registrado correctamente.");

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
                    mostrarDialogo(" Error: Verifica que los campos numéricos tengan valores válidos.");
                } catch (IndexOutOfBoundsException ex) {
                    mostrarDialogo(" Error: Debes seleccionar un proveedor.");
                }
            }
        });

        btnAtras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ventanaAnterior.setVisible(true);
                setVisible(false);
            }
        });
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
            actualizarChoiceProveedores();
        }
        super.setVisible(visible);
    }

    private void mostrarDialogo(String mensaje) {
        Dialog dialogo = new Dialog(this, "Mensaje", true);
        dialogo.setLayout(new BorderLayout());
        dialogo.setSize(350, 120);
        dialogo.setLocationRelativeTo(this);

        Label lbl = new Label(mensaje, Label.CENTER);
        lbl.setFont(new Font("Arial", Font.PLAIN, 14));

        Button btnCerrar = new Button("Cerrar");
        btnCerrar.setFont(new Font("Arial", Font.BOLD, 14));
        btnCerrar.setBackground(new Color(220, 220, 250));
        btnCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialogo.setVisible(false);
            }
        });

        Panel panelBoton = new Panel();
        panelBoton.add(btnCerrar);

        dialogo.add(lbl, BorderLayout.CENTER);
        dialogo.add(panelBoton, BorderLayout.SOUTH);
        dialogo.setVisible(true);
    }
}
