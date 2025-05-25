package ec.edu.ups.poo.Controlador;

public class Servicio {
    private String id;
    private String nombre;
    private double precio;
    private int cantidad;
    private String tipo;
    private String categoria;

    public Servicio(String id, String nombre, double precio, int cantidad, String tipo, String categoria) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.tipo = tipo;
        this.categoria = categoria;
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
        return "Servicio: " + nombre + " | ID: " + id + " | Precio: $" + precio +
                " | Cantidad: " + cantidad + " | Tipo: " + tipo + " | Categoría: " + categoria;
    }
}
