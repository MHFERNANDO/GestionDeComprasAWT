package ec.edu.ups.poo.Controlador;

public class ProductoFisico {
    private int id;
    private String nombre;
    private double precioUnitario;
    private int cantidad;
    private String descripcion;
    private String presentacion;
    private Persona proveedor;

    public ProductoFisico(int id, String nombre, double precioUnitario, int cantidad, String descripcion, String presentacion, Persona proveedor) {
        this.id = id;
        this.nombre = nombre;
        this.precioUnitario = precioUnitario;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.presentacion = presentacion;
        this.proveedor = proveedor;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public Persona getProveedor() {
        return proveedor;
    }

    public void setProveedor(Persona proveedor) {
        this.proveedor = proveedor;
    }

    @Override
    public String toString() {
        return String.format("Producto: %-20s | ID: %d | Precio: $%.2f | Cantidad: %d | Presentación: %s | Descripción: %s",
                nombre, id, precioUnitario, cantidad, presentacion, descripcion);
    }

}
