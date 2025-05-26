package ec.edu.ups.poo.Controlador;

public class DetalleCompra {
    private String nombreItem;
    private double precioUnitario;
    private int cantidad;

    public DetalleCompra(String nombreItem, double precioUnitario, int cantidad) {
        this.nombreItem = nombreItem;
        this.precioUnitario = precioUnitario;
        this.cantidad = cantidad;
    }

    public double calcularTotal() {
        return precioUnitario * cantidad;
    }

    @Override
    public String toString() {
        return String.format("  - Item: %-20s | Precio: $%.2f | Cantidad: %d | Subtotal: $%.2f%n",
                nombreItem, precioUnitario, cantidad, calcularTotal());
    }

}

