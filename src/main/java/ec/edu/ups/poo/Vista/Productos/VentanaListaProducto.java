package ec.edu.ups.poo.Vista.Productos;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class VentanaListaProducto extends Frame {

    private TextArea areaListado;
    private Button btnAtras;
    private Frame ventanaAnterior;

    public VentanaListaProducto(Frame ventanaAnterior) {
        this.ventanaAnterior = ventanaAnterior;

        setTitle("Lista de Productos y Servicios");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setBackground(new Color(245, 245, 255));

        // Título
        Label titulo = new Label("Productos y Servicios Registrados");
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setAlignment(Label.CENTER);
        add(titulo, BorderLayout.NORTH);

        // Área de texto
        areaListado = new TextArea();
        areaListado.setEditable(false);
        areaListado.setFont(new Font("Monospaced", Font.PLAIN, 14));
        add(areaListado, BorderLayout.CENTER);

        // Botón Atrás
        btnAtras = new Button("Atrás");
        btnAtras.setFont(new Font("Arial", Font.PLAIN, 14));
        Panel panelBoton = new Panel(new FlowLayout());
        panelBoton.setBackground(new Color(230, 240, 255));
        panelBoton.add(btnAtras);
        add(panelBoton, BorderLayout.SOUTH);

        // Acción del botón Atrás
        btnAtras.addActionListener(e -> {
            ventanaAnterior.setVisible(true);
            setVisible(false);
        });

        // Al cerrar la ventana
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });

        setVisible(false); // Se mostrará cuando se llame desde otra ventana
    }

    public void mostrar() {
        mostrarProductosYServicios();
        setVisible(true);
    }

    private void mostrarProductosYServicios() {
        areaListado.setText("");

        List<String> productos = VentanaProductoFisico.getProductos();
        List<String> servicios = VentanaServicio.getServicios();

        areaListado.append("=== PRODUCTOS FÍSICOS ===\n");
        if (productos.isEmpty()) {
            areaListado.append("No hay productos registrados.\n");
        } else {
            for (String p : productos) {
                areaListado.append(p + "\n");
            }
        }

        areaListado.append("\n=== SERVICIOS ===\n");
        if (servicios.isEmpty()) {
            areaListado.append("No hay servicios registrados.\n");
        } else {
            for (String s : servicios) {
                areaListado.append(s + "\n");
            }
        }
    }
}
