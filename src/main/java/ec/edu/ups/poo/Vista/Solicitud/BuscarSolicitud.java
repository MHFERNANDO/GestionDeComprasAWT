package ec.edu.ups.poo.Vista.Solicitud;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class BuscarSolicitud extends Frame{
    private Checkbox chPro;
    private Checkbox chEmpleado;
    private Checkbox chPCed;
    private Checkbox chPNomb;

    private TextField ingresoInfo;
    private TextField resultadoInfo;

    private Button buscar;
    private Button salir;

    public BuscarSolicitud (){
        setTitle("Solicitud de Compra");
        setSize(500,500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        Label titulo = new Label("Buscar Solicitud de Compra");
        titulo.setFont(new Font("Arial",Font.BOLD,20));
        titulo.setAlignment(Label.CENTER);
        titulo.setForeground(new Color(30,30,30));

        Panel panelTitulo= new Panel();
        panelTitulo.setBackground(new Color(220,220,220));
        panelTitulo.add(titulo);

        Panel panelBox = new Panel(new GridLayout(1,1,10,10));
        panelBox.setBackground(new Color(245,245,245));
        panelBox.setFont(new Font("Arial", Font.PLAIN, 16));
        chPCed=new Checkbox("Por id");
        panelBox.add(chPCed);

        Panel panelIngres = new Panel(new FlowLayout(FlowLayout.LEFT));
        Label labelIngreso = new Label("Buscar: ");
        labelIngreso.setAlignment(Label.LEFT);
        ingresoInfo= new TextField(30);
        panelIngres.add(labelIngreso);
        panelIngres.add(ingresoInfo);

        Panel panelRes = new Panel(new FlowLayout((FlowLayout.LEFT)));
        Label labelResultado=new Label("Resultado: ");
        labelResultado.setAlignment(Label.LEFT);
        resultadoInfo= new TextField(30);
        resultadoInfo.setEditable(false);
        panelRes.add(labelResultado);
        panelRes.add(resultadoInfo);

        Panel panelCentro = new Panel(new GridLayout(3,1,10,10));
        panelCentro.add(panelBox);
        panelCentro.add(panelIngres);
        panelCentro.add(panelRes);

        Panel panelBoton = new Panel(new FlowLayout(FlowLayout.CENTER,20,10));
        buscar = new Button("Buscar");
        salir=new Button("Salir");
        buscar.setFont(new Font("Arial",Font.PLAIN,16));
        salir.setFont(new Font("Arial",Font.PLAIN,16));
        panelBoton.add(buscar);
        panelBoton.add(salir);

        add(panelTitulo, BorderLayout.NORTH);
        add(panelCentro,BorderLayout.CENTER);
        add(panelBoton,BorderLayout.SOUTH);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
                System.exit(0);
            }
        });
        setVisible(false);
    }

}
