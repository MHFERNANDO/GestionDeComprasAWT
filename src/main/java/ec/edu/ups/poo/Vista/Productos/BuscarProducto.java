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

    private Button buscar;
    private Button salir;

    public BuscarProducto(Frame ventanaAnterior, VentanaIni ventanaIni) {
        this.ventanaAnterior = ventanaAnterior;
        this.ventanaIni = ventanaIni;

        setTitle("Buscar Productos");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(245, 250, 255));

        // ---------- Título ----------
        Label titulo = new Label("Buscar Productos", Label.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setForeground(new Color(30, 30, 30));
        add(titulo, BorderLayout.NORTH);

        // ---------- Opciones ----------
        Panel panelOpciones = new Panel(new GridLayout(2, 2, 10, 10));
        panelOpciones.setBackground(new Color(230, 240, 255));
        panelOpciones.setFont(new Font("Arial", Font.PLAIN, 14));

        chFisico = new Checkbox("Producto Físico");
        chServicio = new Checkbox("Servicio");
        chCodigo = new Checkbox("Por Código");
        chNombre = new Checkbox("Por Nombre");

        panelOpciones.add(chFisico);
        panelOpciones.add(chServicio);
        panelOpciones.add(chCodigo);
        panelOpciones.add(chNombre);

        Panel panelIngreso = new Panel(new FlowLayout(FlowLayout.CENTER));
        panelIngreso.setBackground(new Color(245, 250, 255));
        Label labelIngreso = new Label("Buscar:");
        labelIngreso.setFont(new Font("Arial", Font.PLAIN, 14));
        ingresoInfo = new TextField(30);
        panelIngreso.add(labelIngreso);
        panelIngreso.add(ingresoInfo);

        Panel panelCentro = new Panel(new GridLayout(2, 1, 15, 15));
        panelCentro.setBackground(new Color(245, 250, 255));
        panelCentro.add(panelOpciones);
        panelCentro.add(panelIngreso);
        add(panelCentro, BorderLayout.CENTER);

        // ---------- Botones ----------
        Panel panelBotones = new Panel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelBotones.setBackground(new Color(230, 240, 255));

        buscar = crearBoton("Buscar");
        salir = crearBoton("Atrás");

        panelBotones.add(buscar);
        panelBotones.add(salir);
        add(panelBotones, BorderLayout.SOUTH);

        // ---------- Eventos ----------
        buscar.addActionListener(e -> realizarBusqueda());
        salir.addActionListener(e -> {
            ventanaAnterior.setVisible(true);
            setVisible(false);
        });

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
            mostrarDialogo("Ingrese un valor para buscar.");
            return;
        }

        boolean buscarFisico = chFisico.getState();
        boolean buscarServicio = chServicio.getState();
        boolean buscarPorCodigo = chCodigo.getState();
        boolean buscarPorNombre = chNombre.getState();

        if (!buscarFisico && !buscarServicio) {
            mostrarDialogo("Seleccione tipo de producto.");
            return;
        }

        if (!buscarPorCodigo && !buscarPorNombre) {
            mostrarDialogo("Seleccione un criterio de búsqueda.");
            return;
        }

        // ---------- Buscar Producto Físico ----------
        if (buscarFisico) {
            for (ProductoFisico pf : ventanaIni.getListaProductosFisicos()) {
                if (buscarPorCodigo && sonIguales(String.valueOf(pf.getId()), criterio)) {
                    mostrarDialogo("Resultado encontrado:\n" + pf.toString());
                    return;
                } else if (buscarPorNombre && sonIguales(pf.getNombre(), criterio)) {
                    mostrarDialogo("Resultado encontrado:\n" + pf.toString());
                    return;
                }
            }
        }

        // ---------- Buscar Servicio ----------
        if (buscarServicio) {
            for (Servicio s : ventanaIni.getListaServicios()) {
                if (buscarPorCodigo && sonIguales(String.valueOf(s.getId()), criterio)) {
                    mostrarDialogo("Resultado encontrado:\n" + s.toString());
                    return;
                } else if (buscarPorNombre && sonIguales(s.getNombre(), criterio)) {
                    mostrarDialogo("Resultado encontrado:\n" + s.toString());
                    return;
                }
            }
        }

        mostrarDialogo("No se encontró ningún producto o servicio con ese criterio.");
    }

    private boolean sonIguales(String a, String b) {
        if (a == null || b == null) return false;
        return a.trim().equalsIgnoreCase(b.trim());
    }

    private Button crearBoton(String texto) {
        Button boton = new Button(texto);
        boton.setFont(new Font("Arial", Font.BOLD, 16));
        boton.setBackground(new Color(180, 210, 255));
        boton.setForeground(Color.BLACK);
        boton.setPreferredSize(new Dimension(160, 35));
        return boton;
    }

    private void mostrarDialogo(String mensaje) {
        Dialog dialogo = new Dialog(this, "Resultado", true);
        dialogo.setLayout(new BorderLayout());
        Label lblMensaje = new Label(mensaje, Label.CENTER);
        lblMensaje.setFont(new Font("Arial", Font.PLAIN, 14));
        dialogo.add(lblMensaje, BorderLayout.CENTER);
        Button btnCerrar = new Button("Cerrar");
        btnCerrar.addActionListener(e -> dialogo.dispose());
        Panel panelBoton = new Panel();
        panelBoton.add(btnCerrar);
        dialogo.add(panelBoton, BorderLayout.SOUTH);
        dialogo.setSize(350, 150);
        dialogo.setLocationRelativeTo(this);
        dialogo.setVisible(true);
    }
}
