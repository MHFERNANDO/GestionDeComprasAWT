package ec.edu.ups.poo.Vista.Productos;

import ec.edu.ups.poo.Controlador.ProductoFisico;
import ec.edu.ups.poo.Controlador.Servicio;
import ec.edu.ups.poo.Vista.VentanaIni;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

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
        String criterio = ingresoInfo.getText();
        if (criterio == null || criterio.length() == 0) {
            resultadoInfo.setText("Ingrese un valor.");
            return;
        }

        List<Object> lista = new ArrayList<>();
        if (chFisico.getState()) {
            lista.addAll(ventanaIni.getListaProductosFisicos());
        } else if (chServicio.getState()) {
            lista.addAll(ventanaIni.getListaServicios());
        } else {
            resultadoInfo.setText("Seleccione tipo de producto.");
            return;
        }

        if (chCodigo.getState()) {
            for (int i = 0; i < lista.size(); i++) {
                String id = lista.get(i) instanceof ProductoFisico
                        ? ((ProductoFisico) lista.get(i)).getId()
                        : ((Servicio) lista.get(i)).getId();
                if (sonIguales(id, criterio)) {
                    resultadoInfo.setText(lista.get(i).toString());
                    return;
                }
            }
            resultadoInfo.setText("No encontrado.");
        } else if (chNombre.getState()) {
            for (int i = 0; i < lista.size(); i++) {
                String nombre = lista.get(i) instanceof ProductoFisico
                        ? ((ProductoFisico) lista.get(i)).getNombre()
                        : ((Servicio) lista.get(i)).getNombre();
                if (sonIguales(nombre, criterio)) {
                    resultadoInfo.setText(lista.get(i).toString());
                    return;
                }
            }
            resultadoInfo.setText("No encontrado.");
        } else {
            resultadoInfo.setText("Seleccione un criterio de búsqueda.");
        }
    }

    // Comparación carácter por carácter sin equals, compareTo, ni trim
    private boolean sonIguales(String a, String b) {
        if (a == null || b == null) return false;
        if (a.length() != b.length()) return false;

        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) return false;
        }

        return true;
    }
}
