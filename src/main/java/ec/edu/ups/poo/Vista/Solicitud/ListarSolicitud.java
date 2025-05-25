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

        setTitle("Lista de Solicitudes");
        setSize(500, 400);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        areaSolicitudes = new TextArea();
        areaSolicitudes.setEditable(false);
        botonAtras = new Button("Atrás");

        add(areaSolicitudes, BorderLayout.CENTER);
        add(botonAtras, BorderLayout.SOUTH);

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

    public void mostrar() {
        String texto = "";
        for (SolicitudCompra s : solicitudes) {
            texto += s.toString();
        }
        areaSolicitudes.setText(texto.isEmpty() ? "No hay solicitudes." : texto);
    }
}
