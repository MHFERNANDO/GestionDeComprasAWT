package ec.edu.ups.poo.Controlador;

import java.util.ArrayList;
import java.util.List;

public class SolicitudCompra {
    private static int contador = 1;
    private int id;
    private String nombreSolicitante;
    private String cedulaSolicitante;
    private List<DetalleCompra> detalles;
    private String estado;

    public SolicitudCompra(String nombreSolicitante, String cedulaSolicitante) {
        this.id = contador++;
        this.nombreSolicitante = nombreSolicitante;
        this.cedulaSolicitante = cedulaSolicitante;
        this.detalles = new ArrayList<>();
        this.estado = "Pendiente";
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String nuevoEstado) {
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
        String texto = "\n====== SOLICITUD DE COMPRA ======\n";
        texto += "ID           : " + id + "\n";
        texto += "Solicitante  : " + nombreSolicitante + "\n";
        texto += "Cédula       : " + cedulaSolicitante + "\n";
        texto += "Estado       : " + estado + "\n";

        if (detalles.isEmpty()) {
            texto += ">>> No hay productos agregados.\n";
        } else {
            texto += ">>> Detalles:\n";
            for (DetalleCompra detalle : detalles) {
                texto += detalle.toString();
            }
            texto += "TOTAL: $" + String.format("%.2f", calcularTotal()) + "\n";
        }

        texto += "=================================\n";
        return texto;
    }
}
