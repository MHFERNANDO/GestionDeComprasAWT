package ec.edu.ups.poo.Controlador;

import ec.edu.ups.poo.Enums.Departamento;

public class Empleado extends Persona {
    private String cargo;
    private Departamento departamento;

    public Empleado(String nombre, String cedula, String cargo, Departamento departamento) {
        super(nombre, cedula);
        this.cargo = cargo;
        this.departamento = departamento;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    @Override
    public String toString() {
        return String.format("Empleado: %-20s | Cédula: %s | Cargo: %-15s | Departamento: %s",
                getNombre(), getCedula(), cargo, departamento.name());
    }

}
