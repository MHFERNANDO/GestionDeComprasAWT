package ec.edu.ups.poo.Vista.Solicitud;

import ec.edu.ups.poo.Controlador.SolicitudCompra;
import ec.edu.ups.poo.Enums.EstadoSolicitud;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class BuscarSolicitud extends Frame {

    private TextField campoIdBusqueda;
    private TextArea areaResultado;
    private Choice comboEstado;
    private Button botonBuscar;
    private Button botonCambiarEstado;
    private Button botonAtras;

    private Frame ventanaAnterior;
    private List<SolicitudCompra> solicitudes;
    private SolicitudCompra solicitudEncontrada;

    public BuscarSolicitud(Frame ventanaAnterior, List<SolicitudCompra> solicitudes) {
        this.ventanaAnterior = ventanaAnterior;
        this.solicitudes = solicitudes;

        configurarVentana();
        inicializarComponentes();
        agregarEventos();
    }

    private void configurarVentana() {
        setTitle("Buscar Solicitud de Compra");
        setSize(550, 500);
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
        // Panel campos
        Panel panelSuperior = new Panel(new GridLayout(3, 2, 10, 10));
        panelSuperior.setBackground(new Color(250, 250, 250));
        panelSuperior.setFont(new Font("Arial", Font.PLAIN, 14));

        campoIdBusqueda = new TextField();
        comboEstado = new Choice();
        for (EstadoSolicitud estado : EstadoSolicitud.values()) {
            comboEstado.add(estado.name());
        }

        botonBuscar = new Button("Buscar por ID");
        botonCambiarEstado = new Button("Cambiar Estado");
        botonAtras = new Button("Atrás");

        botonBuscar.setBackground(new Color(200, 230, 255));
        botonCambiarEstado.setBackground(new Color(200, 255, 200));
        botonAtras.setBackground(new Color(255, 200, 200));

        botonBuscar.setFont(new Font("Arial", Font.PLAIN, 14));
        botonCambiarEstado.setFont(new Font("Arial", Font.PLAIN, 14));
        botonAtras.setFont(new Font("Arial", Font.PLAIN, 14));

        panelSuperior.add(new Label("Ingrese ID de Solicitud:"));
        panelSuperior.add(campoIdBusqueda);
        panelSuperior.add(new Label("Nuevo Estado:"));
        panelSuperior.add(comboEstado);
        panelSuperior.add(botonBuscar);
        panelSuperior.add(botonCambiarEstado);

        // Panel resultado
        areaResultado = new TextArea();
        areaResultado.setEditable(false);
        areaResultado.setFont(new Font("Monospaced", Font.PLAIN, 13));
        areaResultado.setBackground(new Color(255, 255, 255));

        // Panel inferior
        Panel panelInferior = new Panel(new FlowLayout(FlowLayout.CENTER));
        panelInferior.add(botonAtras);

        // Agregar a la ventana
        add(panelSuperior, BorderLayout.NORTH);
        add(areaResultado, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);
    }

    private void agregarEventos() {
        botonBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String idTexto = campoIdBusqueda.getText().trim();
                if (idTexto.isEmpty()) {
                    mostrarMensaje(">>> Ingrese un ID para buscar.");
                    return;
                }

                try {
                    int id = Integer.parseInt(idTexto);
                    SolicitudCompra.ordenarPorId(solicitudes);
                    int indice = SolicitudCompra.buscarPorId(solicitudes, id);

                    if (indice != -1) {
                        solicitudEncontrada = solicitudes.get(indice);
                        areaResultado.setText(">>> Solicitud encontrada:\n\n" + solicitudEncontrada.toString());
                    } else {
                        solicitudEncontrada = null;
                        mostrarMensaje(">>> Solicitud no encontrada.");
                    }

                } catch (NumberFormatException e) {
                    mostrarMensaje(">>> ID inválido. Debe ser un número.");
                }
            }
        });

        botonCambiarEstado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                if (solicitudEncontrada == null) {
                    mostrarMensaje(">>> No hay ninguna solicitud seleccionada.");
                    return;
                }

                try {
                    EstadoSolicitud nuevoEstado = EstadoSolicitud.valueOf(comboEstado.getSelectedItem());
                    solicitudEncontrada.setEstado(nuevoEstado);
                    areaResultado.setText(">>> Estado actualizado correctamente:\n\n" + solicitudEncontrada.toString());
                } catch (IllegalArgumentException e) {
                    mostrarMensaje(">>> Estado seleccionado no válido.");
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

    private void mostrarMensaje(String mensaje) {
        areaResultado.setText(mensaje);
    }
}
