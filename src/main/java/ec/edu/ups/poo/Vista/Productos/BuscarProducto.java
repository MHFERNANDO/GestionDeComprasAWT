package ec.edu.ups.poo.Vista.Productos;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class BuscarProducto extends Frame {

    private Checkbox chPro;
    private Checkbox chEmpleado;
    private Checkbox chPCed;
    private Checkbox chPNomb;

    private TextField ingresoInfo;
    private TextField resultadoInfo;

    private Button buscar;
    private Button salir;

    public BuscarProducto() {
        setTitle("Productos");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setBackground(new Color(245, 250, 255));


        Label titulo = new Label("Buscar Productos");
        titulo.setFont(new Font("Arial", Font.BOLD, 25));
        titulo.setAlignment(Label.CENTER);
        titulo.setForeground(new Color(30, 30, 30));

        Panel panelTitulo = new Panel(new FlowLayout(FlowLayout.CENTER));
        panelTitulo.setBackground(new Color(230, 240, 255));
        panelTitulo.add(titulo);


        Panel panelBox = new Panel(new GridLayout(2, 2, 15, 15));
        panelBox.setBackground(new Color(230, 240, 255));
        panelBox.setFont(new Font("Arial", Font.PLAIN, 16));

        chPro = new Checkbox("Proveedor");
        chEmpleado = new Checkbox("Empleado");
        chPCed = new Checkbox("Por Cédula");
        chPNomb = new Checkbox("Por Nombre");

        panelBox.add(chPro);
        panelBox.add(chEmpleado);
        panelBox.add(chPCed);
        panelBox.add(chPNomb);

        Panel panelBoxCentrado = new Panel(new FlowLayout(FlowLayout.CENTER));
        panelBoxCentrado.setBackground(new Color(245, 250, 255));
        panelBoxCentrado.add(panelBox);


        Panel panelIngreso = new Panel(new FlowLayout(FlowLayout.CENTER));
        panelIngreso.setBackground(new Color(245, 250, 255));
        Label labelIngreso = new Label("Buscar:");
        ingresoInfo = new TextField(30);
        panelIngreso.add(labelIngreso);
        panelIngreso.add(ingresoInfo);


        Panel panelRes = new Panel(new FlowLayout(FlowLayout.CENTER));
        panelRes.setBackground(new Color(245, 250, 255));
        Label labelResultado = new Label("Resultado:");
        resultadoInfo = new TextField(30);
        resultadoInfo.setEditable(false);
        panelRes.add(labelResultado);
        panelRes.add(resultadoInfo);


        Panel panelCentro = new Panel(new GridLayout(3, 1, 15, 15));
        panelCentro.setBackground(new Color(245, 250, 255));
        panelCentro.add(panelBoxCentrado);
        panelCentro.add(panelIngreso);
        panelCentro.add(panelRes);


        Panel panelBoton = new Panel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buscar = new Button("Buscar");
        salir = new Button("Atras");
        buscar.setFont(new Font("Arial", Font.PLAIN, 16));
        salir.setFont(new Font("Arial", Font.PLAIN, 16));
        panelBoton.setBackground(new Color(230, 240, 255));
        panelBoton.add(buscar);
        panelBoton.add(salir);

        add(panelTitulo, BorderLayout.NORTH);
        add(panelCentro, BorderLayout.CENTER);
        add(panelBoton, BorderLayout.SOUTH);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
                System.exit(0);
            }
        });

        setVisible(false);
    }
}

