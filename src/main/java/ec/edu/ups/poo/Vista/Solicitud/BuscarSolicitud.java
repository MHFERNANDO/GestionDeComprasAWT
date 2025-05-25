package ec.edu.ups.poo.Vista.Solicitud;

import ec.edu.ups.poo.Controlador.SolicitudCompra;

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

        setTitle("Buscar Solicitud de Compra");
        setSize(500, 500);
        setLayout(new GridLayout(7, 1));
        setLocationRelativeTo(null);

        campoIdBusqueda = new TextField();
        areaResultado = new TextArea();
        areaResultado.setEditable(false);

        comboEstado = new Choice();
        comboEstado.add("Pendiente");
        comboEstado.add("Aprobado");
        comboEstado.add("Rechazado");

        botonBuscar = new Button("Buscar por ID");
        botonCambiarEstado = new Button("Cambiar Estado");
        botonAtras = new Button("Atrás");

        add(new Label("Ingrese ID de Solicitud:"));
        add(campoIdBusqueda);
        add(botonBuscar);
        add(areaResultado);
        add(new Label("Nuevo Estado:"));
        add(comboEstado);
        add(botonCambiarEstado);
        add(botonAtras);

        // Acción botón Buscar
        botonBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String idTexto = campoIdBusqueda.getText().trim();
                if (!idTexto.isEmpty()) {
                    try {
                        int id = Integer.parseInt(idTexto);

                        // Ordenar primero
                        SolicitudCompra.ordenarPorId(solicitudes);

                        // Buscar
                        int indice = SolicitudCompra.buscarPorId(solicitudes, id);
                        if (indice != -1) {
                            solicitudEncontrada = solicitudes.get(indice);
                            areaResultado.setText(solicitudEncontrada.toString());
                        } else {
                            solicitudEncontrada = null;
                            areaResultado.setText(">>> Solicitud no encontrada.");
                        }
                    } catch (NumberFormatException e) {
                        areaResultado.setText(">>> ID inválido. Debe ser un número.");
                    }
                }
            }
        });

        // Acción botón Cambiar Estado
        botonCambiarEstado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                if (solicitudEncontrada != null) {
                    String nuevoEstado = comboEstado.getSelectedItem();
                    solicitudEncontrada.setEstado(nuevoEstado);
                    areaResultado.setText(">>> Estado actualizado.\n\n" + solicitudEncontrada.toString());
                } else {
                    areaResultado.setText(">>> No se ha seleccionado ninguna solicitud.");
                }
            }
        });

        // Acción botón Atrás
        botonAtras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                ventanaAnterior.setVisible(true);
                setVisible(false);
            }
        });

        // Cierre de ventana
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
                System.exit(0);
            }
        });

        setVisible(false);
    }
}
