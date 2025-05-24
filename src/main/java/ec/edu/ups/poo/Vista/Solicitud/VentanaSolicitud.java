package ec.edu.ups.poo.Vista.Solicitud;

import ec.edu.ups.poo.Vista.Persona.VentanaPersona;
import ec.edu.ups.poo.Vista.VentanaIni;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VentanaSolicitud extends Frame{
    private Button botonBuscar;
    private Button botonIngresar;
    private Button botonListar;
    private Button atras;

    private VentanaIni ventanaIni;

    public VentanaSolicitud(VentanaIni ventanaIni){
        this.ventanaIni=ventanaIni;
        setTitle("Solicitud de Compra");
        setSize(500,500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        Label textoTitulo=new Label("Solicitud de Compra");
        textoTitulo.setFont(new Font("Arial",Font.BOLD,24));
        textoTitulo.setAlignment(Label.CENTER);

        Panel encabezado=new Panel();
        encabezado.setBackground(new Color(230, 240, 255));
        encabezado.add(textoTitulo);

        Panel panelFuncional = new Panel(new GridLayout(4,1,10,10));
        panelFuncional.setPreferredSize(new Dimension(200,200));
        panelFuncional.setFont(new Font("Arial", Font.PLAIN, 18));

        botonBuscar=new Button("Buscar Solicitud de Compra");
        botonBuscar.setBackground(new Color(230, 240, 255));
        botonIngresar=new Button("Añadir Solicitud de Compra");
        botonIngresar.setBackground(new Color(230, 240, 255));
        botonListar=new Button("Listar Solicitud de Compra");
        botonListar.setBackground(new Color(230, 240, 255));
        atras=new Button("Atras");
        atras.setBackground(new Color(230, 240, 255));
        botonBuscar.setFont(new Font("Arial", Font.PLAIN, 16));
        botonIngresar.setFont(new Font("Arial", Font.PLAIN, 16));
        botonListar.setFont(new Font("Arial", Font.PLAIN, 16));
        atras.setFont(new Font("Arial", Font.PLAIN, 16));
        panelFuncional.add(botonBuscar);
        panelFuncional.add(botonIngresar);
        panelFuncional.add(botonListar);
        panelFuncional.add(atras);

        Panel panelPrincipal = new Panel(new FlowLayout(FlowLayout.CENTER,20,20));
        panelPrincipal.add(panelFuncional);
        add(encabezado,BorderLayout.NORTH);
        add(panelPrincipal,BorderLayout.CENTER);

        BuscarSolicitud buscarSolicitud = new BuscarSolicitud();

        botonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarSolicitud.setVisible(true);
                setVisible(false);
            }
        });
        atras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaIni.setVisible(true);
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
}


