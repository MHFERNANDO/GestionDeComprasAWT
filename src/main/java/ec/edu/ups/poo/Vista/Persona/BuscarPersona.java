package ec.edu.ups.poo.Vista.Persona;

import ec.edu.ups.poo.Controlador.Persona;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BuscarPersona extends Frame {

    private Checkbox chPro;
    private Checkbox chEmpleado;
    private Checkbox chPCed;
    private Checkbox chPNomb;

    private TextField ingresoInfo;
    private TextField resultadoInfo;

    private Button buscar;
    private Button salir;

    private VentanaPersona ventanaPersona;

    public BuscarPersona(VentanaPersona ventanaPersona) {
        this.ventanaPersona = ventanaPersona;
        setTitle("Buscar Persona");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setBackground(new Color(245, 250, 255));

        Label titulo = new Label("Buscar Persona");
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
        buscar = crearBoton("Buscar");
        salir = crearBoton("Atrás");

        panelBoton.setBackground(new Color(230, 240, 255));
        panelBoton.add(buscar);
        panelBoton.add(salir);

        add(panelTitulo, BorderLayout.NORTH);
        add(panelCentro, BorderLayout.CENTER);
        add(panelBoton, BorderLayout.SOUTH);

        salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaPersona.setVisible(true);
                setVisible(false);
            }
        });

        buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String criterio = ingresoInfo.getText().trim();
                if (criterio.isEmpty()) {
                    resultadoInfo.setText("Ingrese un valor.");
                    return;
                }

                List<Persona> lista = new ArrayList<>();
                if (chPro.getState()) {
                    lista.addAll(ventanaPersona.getVentanaIni().getListaProveedores());
                } else if (chEmpleado.getState()) {
                    lista.addAll(ventanaPersona.getVentanaIni().getListaEmpleados());
                } else {
                    resultadoInfo.setText("Seleccione Proveedor o Empleado.");
                    return;
                }

                if (chPCed.getState()) {
                    lista.sort(Comparator.comparing(Persona::getCedula));
                    int index = busquedaPorCedula(lista, criterio);
                    if (index >= 0) {
                        resultadoInfo.setText(lista.get(index).toString());
                    } else {
                        resultadoInfo.setText("No encontrado.");
                    }
                } else if (chPNomb.getState()) {
                    lista.sort(Comparator.comparing(Persona::getNombre));
                    int index = busquedaPorNombre(lista, criterio);
                    if (index >= 0) {
                        resultadoInfo.setText(lista.get(index).toString());
                    } else {
                        resultadoInfo.setText("No encontrado.");
                    }
                } else {
                    resultadoInfo.setText("Seleccione un tipo de búsqueda.");
                }
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

    private int busquedaPorCedula(List<Persona> lista, String cedula) {
        int bajo = 0, alto = lista.size() - 1;
        while (bajo <= alto) {
            int medio = (bajo + alto) / 2;
            int cmp = lista.get(medio).getCedula().compareTo(cedula);
            if (cmp == 0) return medio;
            if (cmp < 0) bajo = medio + 1;
            else alto = medio - 1;
        }
        return -1;
    }

    private int busquedaPorNombre(List<Persona> lista, String nombre) {
        int bajo = 0, alto = lista.size() - 1;
        while (bajo <= alto) {
            int medio = (bajo + alto) / 2;
            int cmp = lista.get(medio).getNombre().compareToIgnoreCase(nombre);
            if (cmp == 0) return medio;
            if (cmp < 0) bajo = medio + 1;
            else alto = medio - 1;
        }
        return -1;
    }

    private Button crearBoton(String texto) {
        Button boton = new Button(texto);
        boton.setFont(new Font("Arial", Font.BOLD, 16));
        boton.setBackground(new Color(180, 210, 255));
        boton.setForeground(Color.BLACK);
        boton.setPreferredSize(new Dimension(120, 40));
        return boton;
    }
}
