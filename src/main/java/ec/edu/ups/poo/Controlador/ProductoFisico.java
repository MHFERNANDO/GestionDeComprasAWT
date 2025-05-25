package ec.edu.ups.poo.Controlador;

public class ProductoFisico {
    private String id;
    private String nombre;
    private double precio;
    private int cantidad;
    private String descripcion;
    private String presentacion;

    public ProductoFisico(String id, String nombre, double precio, int cantidad, String descripcion, String presentacion) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.presentacion = presentacion;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    @Override
    public String toString() {
        return "Producto: " + nombre + " | ID: " + id + " | Precio: $" + precio +
                " | Cantidad: " + cantidad + " | Descripción: " + descripcion +
                " | Presentación: " + presentacion;
    }
}



