package ec.edu.ups.poo.Controlador;

public class Persona {
    private String nombre;
    private String cedula;

    public Persona(String nombre, String cedula) {
        this.nombre = nombre;
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCedula() {
        return cedula;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + ", Cédula: " + cedula;
    }
}

