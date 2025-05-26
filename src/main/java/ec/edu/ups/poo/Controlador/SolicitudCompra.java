package ec.edu.ups.poo.Controlador;

import java.util.ArrayList;
import java.util.List;
import ec.edu.ups.poo.Enums.EstadoSolicitud;

public class SolicitudCompra {
    private static int contador = 1;
    private int id;
    private String nombreSolicitante;
    private String cedulaSolicitante;
    private List<DetalleCompra> detalles;
    private EstadoSolicitud estado;

    public SolicitudCompra(String nombreSolicitante, String cedulaSolicitante) {
        this.id = contador++;
        this.nombreSolicitante = nombreSolicitante;
        this.cedulaSolicitante = cedulaSolicitante;
        this.detalles = new ArrayList<>();
        this.estado = EstadoSolicitud.PENDIENTE;
    }

    public void agregarDetalle(DetalleCompra detalle) {
        detalles.add(detalle);
    }

    public double calcularTotal() {
        double total = 0;
        for (DetalleCompra d : detalles) {
            total += d.calcularTotal();
        }
        return total;
    }

    public int getId() {
        return id;
    }

    public EstadoSolicitud getEstado() {
        return estado;
    }

    public void setEstado(EstadoSolicitud nuevoEstado) {
        this.estado = nuevoEstado;
    }

    public static void ordenarPorId(List<SolicitudCompra> lista) {
        for (int i = 1; i < lista.size(); i++) {
            SolicitudCompra key = lista.get(i);
            int j = i - 1;
            while (j >= 0 && lista.get(j).id > key.id) {
                lista.set(j + 1, lista.get(j));
                j--;
            }
            lista.set(j + 1, key);
        }
    }

    public static int buscarPorId(List<SolicitudCompra> lista, int id) {
        int bajo = 0, alto = lista.size() - 1;
        while (bajo <= alto) {
            int medio = (bajo + alto) / 2;
            if (lista.get(medio).id == id) return medio;
            if (lista.get(medio).id < id) bajo = medio + 1;
            else alto = medio - 1;
        }
        return -1;
    }

    @Override
    public String toString() {
        StringBuilder texto = new StringBuilder();
        texto.append("\n========== SOLICITUD DE COMPRA ==========\n");
        texto.append(String.format("ID           : %d%n", id));
        texto.append(String.format("Solicitante  : %s%n", nombreSolicitante));
        texto.append(String.format("Cédula       : %s%n", cedulaSolicitante));
        texto.append(String.format("Estado       : %s%n", estado.name()));

        if (detalles.isEmpty()) {
            texto.append(">>> No hay productos agregados.\n");
        } else {
            texto.append(">>> Detalles de compra:\n");
            for (DetalleCompra detalle : detalles) {
                texto.append(detalle.toString());
            }
            texto.append("-----------------------------------------\n");
            texto.append(String.format("TOTAL A PAGAR: $%.2f%n", calcularTotal()));
        }

        texto.append("=========================================\n");
        return texto.toString();
    }

}
