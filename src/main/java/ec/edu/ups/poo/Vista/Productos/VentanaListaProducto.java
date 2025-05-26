package ec.edu.ups.poo.Vista.Productos;

import ec.edu.ups.poo.Controlador.ProductoFisico;
import ec.edu.ups.poo.Controlador.Servicio;
import ec.edu.ups.poo.Controlador.Persona;
import ec.edu.ups.poo.Vista.VentanaIni;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class VentanaListaProducto extends Frame {

    private TextArea areaListado;
    private Button btnAtras;
    private Frame ventanaAnterior;
    private VentanaIni ventanaIni;

    public VentanaListaProducto(Frame ventanaAnterior, VentanaIni ventanaIni) {
        this.ventanaAnterior = ventanaAnterior;
        this.ventanaIni = ventanaIni;

        setTitle("Lista de Productos y Servicios");
        setSize(700, 550);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setBackground(new Color(245, 250, 255));

        Label titulo = new Label("Productos y Servicios Registrados");
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setAlignment(Label.CENTER);
        titulo.setBackground(new Color(220, 235, 255));
        titulo.setForeground(new Color(30, 30, 60));
        add(titulo, BorderLayout.NORTH);

        areaListado = new TextArea();
        areaListado.setEditable(false);
        areaListado.setFont(new Font("Monospaced", Font.PLAIN, 14));
        areaListado.setBackground(new Color(255, 255, 255));
        areaListado.setForeground(new Color(30, 30, 30));
        areaListado.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        add(areaListado, BorderLayout.CENTER);

        btnAtras = new Button("⬅ Atrás");
        btnAtras.setFont(new Font("Arial", Font.BOLD, 14));
        btnAtras.setBackground(new Color(180, 200, 255));
        btnAtras.setForeground(Color.BLACK);
        btnAtras.setPreferredSize(new Dimension(100, 35));

        Panel panelBoton = new Panel(new FlowLayout(FlowLayout.CENTER));
        panelBoton.setBackground(new Color(230, 240, 255));
        panelBoton.add(btnAtras);
        add(panelBoton, BorderLayout.SOUTH);

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
            }
        });

        setVisible(false);
    }

    public void mostrar() {
        mostrarProductosYServicios();
        setVisible(true);
    }

    private void mostrarProductosYServicios() {
        areaListado.setText("");

        List<ProductoFisico> productos = ventanaIni.getListaProductosFisicos();
        List<Servicio> servicios = ventanaIni.getListaServicios();

        areaListado.append("===== PRODUCTOS FÍSICOS =====\n");
        if (productos.isEmpty()) {
            areaListado.append("No hay productos registrados.\n");
        } else {
            for (ProductoFisico p : productos) {
                Persona proveedor = p.getProveedor();
                String nombreProveedor = (proveedor != null) ? proveedor.getNombre() : "No asignado";
                areaListado.append("ID: " + p.getId() +
                        " | Nombre: " + p.getNombre() +
                        " | Precio: $" + String.format("%.2f", p.getPrecioUnitario()) +
                        " | Cantidad: " + p.getCantidad() +
                        " | Presentación: " + p.getPresentacion() +
                        "\nDescripción: " + p.getDescripcion() +
                        "\nProveedor: " + nombreProveedor + "\n");
                areaListado.append("---------------------------------------------\n");
            }
        }

        areaListado.append("\n===== SERVICIOS =====\n");
        if (servicios.isEmpty()) {
            areaListado.append("No hay servicios registrados.\n");
        } else {
            for (Servicio s : servicios) {
                Persona proveedor = s.getProveedor();
                String nombreProveedor = (proveedor != null) ? proveedor.getNombre() : "No asignado";
                areaListado.append("ID: " + s.getId() +
                        " | Nombre: " + s.getNombre() +
                        " | Precio: $" + String.format("%.2f", s.getPrecioUnitario()) +
                        " | Cantidad: " + s.getCantidad() +
                        " | Tipo: " + s.getTipo() +
                        " | Categoría: " + s.getCategoria() +
                        "\nProveedor: " + nombreProveedor + "\n");
                areaListado.append("---------------------------------------------\n");
            }
        }
    }
}
