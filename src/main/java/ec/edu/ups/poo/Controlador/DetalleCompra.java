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
        return "Item: " + nombreItem + ", Precio: $" + precioUnitario +
                ", Cantidad: " + cantidad + ", Total: $" + calcularTotal() + "\n";
    }
}

