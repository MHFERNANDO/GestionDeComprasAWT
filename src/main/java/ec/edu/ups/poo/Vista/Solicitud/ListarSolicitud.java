package ec.edu.ups.poo.Vista.Solicitud;

import ec.edu.ups.poo.Controlador.SolicitudCompra;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class ListarSolicitud extends Frame {

    private TextArea areaSolicitudes;
    private Button botonAtras;
    private Frame ventanaAnterior;
    private List<SolicitudCompra> solicitudes;

    public ListarSolicitud(Frame ventanaAnterior, List<SolicitudCompra> solicitudes) {
        this.ventanaAnterior = ventanaAnterior;
        this.solicitudes = solicitudes;

        configurarVentana();
        inicializarComponentes();
        agregarEventos();
    }

    private void configurarVentana() {
        setTitle("Lista de Solicitudes");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(245, 245, 245));

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose(); // Solo cierra esta ventana
            }
        });

        setVisible(false);
    }

    private void inicializarComponentes() {
        areaSolicitudes = new TextArea();
        areaSolicitudes.setEditable(false);
        areaSolicitudes.setFont(new Font("Monospaced", Font.PLAIN, 13));
        areaSolicitudes.setBackground(new Color(255, 255, 255));

        botonAtras = new Button("Atrás");
        botonAtras.setBackground(new Color(255, 200, 200));
        botonAtras.setFont(new Font("Arial", Font.PLAIN, 14));

        Panel panelInferior = new Panel(new FlowLayout(FlowLayout.CENTER));
        panelInferior.add(botonAtras);

        add(areaSolicitudes, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);
    }

    private void agregarEventos() {
        botonAtras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                ventanaAnterior.setVisible(true);
                setVisible(false);
            }
        });
    }

    public void mostrar() {
        StringBuilder texto = new StringBuilder();

        if (solicitudes.isEmpty()) {
            texto.append(">>> No hay solicitudes registradas.");
        } else {
            for (SolicitudCompra s : solicitudes) {
                texto.append(s.toString()).append("\n-----------------------------------\n");
            }
        }

        areaSolicitudes.setText(texto.toString());
    }
}
