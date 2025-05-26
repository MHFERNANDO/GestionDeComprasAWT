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

    public AñadirSolicitud(VentanaSolicitud ventanaAnterior, List<SolicitudCompra> solicitudes,
                           List<ProductoFisico> listaProductosFisicos, List<Servicio> listaServicios) {
        this.ventanaAnterior = ventanaAnterior;
        this.solicitudes = solicitudes;
        this.listaProductosFisicos = listaProductosFisicos;
        this.listaServicios = listaServicios;

        configurarVentana();
        inicializarComponentes();
        agregarEventos();
    }

    private void configurarVentana() {
        setTitle("Añadir Solicitud de Compra");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(245, 245, 245));

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });

        setVisible(false);
    }

    private void inicializarComponentes() {
        // Panel principal
        Panel panelCampos = new Panel(new GridLayout(6, 2, 10, 10));
        panelCampos.setBackground(new Color(250, 250, 250));
        panelCampos.setFont(new Font("Arial", Font.PLAIN, 14));

        campoNombre = new TextField();
        campoCedula = new TextField();
        campoProductoSeleccionado = new TextField();
        campoCantidad = new TextField();

        areaProductos = new TextArea();
        areaProductos.setEditable(false);
        areaProductos.setFont(new Font("Monospaced", Font.PLAIN, 13));

        panelCampos.add(new Label("Nombre Solicitante:"));
        panelCampos.add(campoNombre);
        panelCampos.add(new Label("Cédula Solicitante:"));
        panelCampos.add(campoCedula);
        panelCampos.add(new Label("Producto o Servicio a Solicitar:"));
        panelCampos.add(campoProductoSeleccionado);
        panelCampos.add(new Label("Cantidad:"));
        panelCampos.add(campoCantidad);
        panelCampos.add(new Label("Productos/Servicios Disponibles:"));

        Panel panelArea = new Panel(new BorderLayout());
        panelArea.add(areaProductos, BorderLayout.CENTER);
        panelCampos.add(panelArea);

        // Panel de botones
        botonGuardar = new Button("Guardar");
        botonAtras = new Button("Atrás");

        botonGuardar.setFont(new Font("Arial", Font.BOLD, 14));
        botonGuardar.setBackground(new Color(200, 230, 200));

        botonAtras.setFont(new Font("Arial", Font.PLAIN, 14));
        botonAtras.setBackground(new Color(230, 200, 200));

        Panel panelBotones = new Panel(new FlowLayout());
        panelBotones.add(botonGuardar);
        panelBotones.add(botonAtras);

        add(panelCampos, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void agregarEventos() {
        botonGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String nombre = campoNombre.getText().trim();
                String cedula = campoCedula.getText().trim();
                String producto = campoProductoSeleccionado.getText().trim();
                int cantidad = 0;

                try {
                    cantidad = Integer.parseInt(campoCantidad.getText().trim());
                } catch (NumberFormatException e) {
                    mostrarMensaje("Cantidad inválida. Debe ser un número entero.");
                    return;
                }

                if (nombre.isEmpty() || cedula.isEmpty() || producto.isEmpty() || cantidad <= 0) {
                    mostrarMensaje("Por favor, complete todos los campos correctamente.");
                    return;
                }

                SolicitudCompra nueva = new SolicitudCompra(nombre, cedula);
                boolean encontrado = false;

                for (ProductoFisico p : listaProductosFisicos) {
                    if (p.getNombre().equalsIgnoreCase(producto)) {
                        nueva.agregarDetalle(new DetalleCompra(p.getNombre(), p.getPrecioUnitario(), cantidad));
                        encontrado = true;
                        break;
                    }
                }

                if (!encontrado) {
                    for (Servicio s : listaServicios) {
                        if (s.getNombre().equalsIgnoreCase(producto)) {
                            nueva.agregarDetalle(new DetalleCompra(s.getNombre(), s.getPrecioUnitario(), cantidad));
                            encontrado = true;
                            break;
                        }
                    }
                }

                if (encontrado) {
                    solicitudes.add(nueva);
                    limpiarCampos();
                    mostrarMensaje("Solicitud añadida correctamente.");
                    System.out.println("Solicitud añadida:\n" + nueva);
                } else {
                    mostrarMensaje("Producto o servicio no encontrado.");
                }
            }
        });

        botonAtras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                ventanaAnterior.setVisible(true);
                setVisible(false);
            }
        });
    }

    private void limpiarCampos() {
        campoNombre.setText("");
        campoCedula.setText("");
        campoProductoSeleccionado.setText("");
        campoCantidad.setText("");
    }

    private void mostrarMensaje(String mensaje) {
        Dialog dialogo = new Dialog(this, "Mensaje", true);
        dialogo.setLayout(new FlowLayout());
        dialogo.setSize(300, 100);
        dialogo.setLocationRelativeTo(this);

        Label lbl = new Label(mensaje);
        lbl.setFont(new Font("Arial", Font.PLAIN, 14));
        dialogo.add(lbl);

        Button btnCerrar = new Button("Cerrar");
        btnCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialogo.setVisible(false);
            }
        });
        dialogo.add(btnCerrar);

        dialogo.setVisible(true);
    }

    public void actualizarLista() {
        areaProductos.setText(""); // Limpiar
        for (ProductoFisico p : listaProductosFisicos) {
            areaProductos.append("Producto: " + p.getNombre() + " - $" + p.getPrecioUnitario() + "\n");
        }
        for (Servicio s : listaServicios) {
            areaProductos.append("Servicio: " + s.getNombre() + " - $" + s.getPrecioUnitario() + "\n");
        }
    }

    @Override
    public void setVisible(boolean visible) {
        if (visible) {
            actualizarLista();
        }
        super.setVisible(visible);
    }
}
