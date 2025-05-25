package ec.edu.ups.poo.Vista.Productos;

import ec.edu.ups.poo.Controlador.ProductoFisico;
import ec.edu.ups.poo.Controlador.Servicio;
import ec.edu.ups.poo.Vista.VentanaIni;

import java.awt.*;
import java.awt.event.*;

public class BuscarProducto extends Frame {

    private VentanaIni ventanaIni;
    private Frame ventanaAnterior;

    private Checkbox chFisico;
    private Checkbox chServicio;
    private Checkbox chCodigo;
    private Checkbox chNombre;

    private TextField ingresoInfo;
    private TextField resultadoInfo;

    private Button buscar;
    private Button salir;

    public BuscarProducto(Frame ventanaAnterior, VentanaIni ventanaIni) {
        this.ventanaAnterior = ventanaAnterior;
        this.ventanaIni = ventanaIni;

        setTitle("Buscar Productos");
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

        chFisico = new Checkbox("Producto Físico");
        chServicio = new Checkbox("Servicio");
        chCodigo = new Checkbox("Por Código");
        chNombre = new Checkbox("Por Nombre");

        panelBox.add(chFisico);
        panelBox.add(chServicio);
        panelBox.add(chCodigo);
        panelBox.add(chNombre);

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
        salir = new Button("Atrás");
        buscar.setFont(new Font("Arial", Font.PLAIN, 16));
        salir.setFont(new Font("Arial", Font.PLAIN, 16));
        panelBoton.setBackground(new Color(230, 240, 255));
        panelBoton.add(buscar);
        panelBoton.add(salir);

        add(panelTitulo, BorderLayout.NORTH);
        add(panelCentro, BorderLayout.CENTER);
        add(panelBoton, BorderLayout.SOUTH);

        salir.addActionListener(e -> {
            ventanaAnterior.setVisible(true);
            setVisible(false);
        });

        buscar.addActionListener(e -> realizarBusqueda());

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });

        setVisible(false);
    }

    private void realizarBusqueda() {
        String criterio = ingresoInfo.getText().trim();
        if (criterio.isEmpty()) {
            resultadoInfo.setText("Ingrese un valor.");
            return;
        }

        boolean buscarFisico = chFisico.getState();
        boolean buscarServicio = chServicio.getState();
        boolean buscarPorCodigo = chCodigo.getState();
        boolean buscarPorNombre = chNombre.getState();

        if (!buscarFisico && !buscarServicio) {
            resultadoInfo.setText("Seleccione tipo de producto.");
            return;
        }

        if (!buscarPorCodigo && !buscarPorNombre) {
            resultadoInfo.setText("Seleccione un criterio de búsqueda.");
            return;
        }

        // Buscar producto físico
        if (buscarFisico) {
            for (ProductoFisico pf : ventanaIni.getListaProductosFisicos()) {
                if (buscarPorCodigo && sonIguales(String.valueOf(pf.getId()), criterio)) {
                    resultadoInfo.setText(pf.toString());
                    return;
                } else if (buscarPorNombre && sonIguales(pf.getNombre(), criterio)) {
                    resultadoInfo.setText(pf.toString());
                    return;
                }
            }
        }

        // Buscar servicio
        if (buscarServicio) {
            for (Servicio s : ventanaIni.getListaServicios()) {
                if (buscarPorCodigo && sonIguales(String.valueOf(s.getId()), criterio)) {
                    resultadoInfo.setText(s.toString());
                    return;
                } else if (buscarPorNombre && sonIguales(s.getNombre(), criterio)) {
                    resultadoInfo.setText(s.toString());
                    return;
                }
            }
        }

        resultadoInfo.setText("No encontrado.");
    }

    private boolean sonIguales(String a, String b) {
        if (a == null || b == null) return false;
        return a.trim().equalsIgnoreCase(b.trim());
    }
}
