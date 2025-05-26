package ec.edu.ups.poo.Controlador;

public class Proveedor extends Persona {

    public Proveedor(String nombre, String cedula) {
        super(nombre, cedula);
    }

    @Override
    public String toString() {
        return "Proveedor -> " + super.toString();
    }
}