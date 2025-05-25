package ec.edu.ups.poo.Controlador;

public class Servicio {
    private int id;
    private String nombre;
    private double precioUnitario;
    private int cantidad;
    private String tipo;
    private String categoria;
    private Persona proveedor;

    public Servicio(int id, String nombre, double precioUnitario, int cantidad, String tipo, String categoria, Persona proveedor) {
        this.id = id;
        this.nombre = nombre;
        this.precioUnitario = precioUnitario;
        this.cantidad = cantidad;
        this.tipo = tipo;
        this.categoria = categoria;
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

    public String getTipo() {
        return tipo;
    }

    public String getCategoria() {
        return categoria;
    }
    public Persona getProveedor() {
        return proveedor;
    }

    public void setProveedor(Persona proveedor) {
        this.proveedor = proveedor;
    }

    @Override
    public String toString() {
        return "Servicio{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precioUnitario=" + precioUnitario +
                ", cantidad=" + cantidad +
                ", tipo='" + tipo + '\'' +
                ", categoria='" + categoria + '\'' +
                '}';
    }
}
