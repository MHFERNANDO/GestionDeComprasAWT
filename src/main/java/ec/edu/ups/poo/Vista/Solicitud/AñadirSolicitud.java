package ec.edu.ups.poo.Vista.Solicitud;

import ec.edu.ups.poo.Controlador.DetalleCompra;
import ec.edu.ups.poo.Controlador.Servicio;
import ec.edu.ups.poo.Controlador.SolicitudCompra;
import ec.edu.ups.poo.Controlador.ProductoFisico;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class AñadirSolicitud extends Frame {

    private TextField campoNombre;
    private TextField campoCedula;
    private TextArea areaProductos;
    private TextField campoProductoSeleccionado;
    private TextField campoCantidad;

    private Button botonGuardar;
    private Button botonAtras;

    private List<SolicitudCompra> solicitudes;
    private VentanaSolicitud ventanaAnterior;
    private List<ProductoFisico> listaProductosFisicos;
    private List<Servicio> listaServicios;

    public AñadirSolicitud(VentanaSolicitud ventanaAnterior, List<SolicitudCompra> solicitudes, List<ProductoFisico> listaProductosFisicos, List<Servicio> listaServicios) {
        this.ventanaAnterior = ventanaAnterior;
        this.solicitudes = solicitudes;
        this.listaProductosFisicos = listaProductosFisicos;
        this.listaServicios = listaServicios;

        setTitle("Añadir Solicitud de Compra");
        setSize(600, 500);
        setLayout(new GridLayout(8, 2));
        setLocationRelativeTo(null);

        campoNombre = new TextField();
        campoCedula = new TextField();
        areaProductos = new TextArea();
        areaProductos.setEditable(false);

        campoProductoSeleccionado = new TextField();
        campoCantidad = new TextField();

        botonGuardar = new Button("Guardar");
        botonAtras = new Button("Atrás");

        add(new Label("Nombre Solicitante:"));
        add(campoNombre);
        add(new Label("Cédula Solicitante:"));
        add(campoCedula);

        add(new Label("Productos disponibles:"));
        add(areaProductos);

        add(new Label("Producto/Servicio a Solicitar:"));
        add(campoProductoSeleccionado);
        add(new Label("Cantidad:"));
        add(campoCantidad);

        add(botonGuardar);
        add(botonAtras);

        botonGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String nombre = campoNombre.getText().trim();
                String cedula = campoCedula.getText().trim();
                String producto = campoProductoSeleccionado.getText().trim();
                int cantidad = 0;

                try {
                    cantidad = Integer.parseInt(campoCantidad.getText().trim());
                } catch (NumberFormatException e) {
                    System.out.println("Cantidad inválida.");
                    return;
                }

                if (!nombre.isEmpty() && !cedula.isEmpty() && !producto.isEmpty() && cantidad > 0) {
                    SolicitudCompra nueva = new SolicitudCompra(nombre, cedula);

                    // Buscar en productos
                    boolean encontrado = false;
                    for (ProductoFisico p : listaProductosFisicos) {
                        if (p.getNombre().equalsIgnoreCase(producto)) {
                            DetalleCompra detalle = new DetalleCompra(p.getNombre(), p.getPrecio(), cantidad);
                            nueva.agregarDetalle(detalle);
                            encontrado = true;
                            break;
                        }
                    }

                    // Buscar en servicios si no se encontró en productos
                    if (!encontrado) {
                        for (Servicio s : listaServicios) {
                            if (s.getNombre().equalsIgnoreCase(producto)) {
                                DetalleCompra detalle = new DetalleCompra(s.getNombre(), s.getPrecio(), cantidad);
                                nueva.agregarDetalle(detalle);
                                encontrado = true;
                                break;
                            }
                        }
                    }

                    if (encontrado) {
                        solicitudes.add(nueva);
                        campoNombre.setText("");
                        campoCedula.setText("");
                        campoProductoSeleccionado.setText("");
                        campoCantidad.setText("");
                        System.out.println("Solicitud añadida:\n" + nueva);
                    } else {
                        System.out.println("Producto o servicio no encontrado.");
                    }
                }
            }
        });


        botonAtras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
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
    public void actualizarLista() {
        areaProductos.setText(""); // Limpiar

        for (ProductoFisico p : listaProductosFisicos) {
            areaProductos.append("Producto: " + p.getNombre() + " - $" + p.getPrecio() + "\n");
        }
        for (Servicio s : listaServicios) {
            areaProductos.append("Servicio: " + s.getNombre() + " - $" + s.getPrecio() + "\n");
        }
    }

    @Override
    public void setVisible(boolean visible) {
        if (visible) {
            actualizarLista(); // siempre actualiza antes de mostrarse
        }
        super.setVisible(visible);
    }

}
