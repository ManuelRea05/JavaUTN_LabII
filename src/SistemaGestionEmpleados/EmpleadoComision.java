package SistemaGestionEmpleados;

class EmpleadoComision extends Empleado implements Impuesto{
    protected double ventasRealizadas;
    public EmpleadoComision(String nombre, int id, double sueldoBase, double ventasRealizadas) {
        super(nombre, id, sueldoBase);
        this.ventasRealizadas = ventasRealizadas;
    }

    // Implementando metodos abstractos
    @Override
    public String toString() {
        return super.toString() + " Sueldo neto=" + calcularSueldo() + "}";
    }
    @Override
    double calcularSueldo() {
        // Calcula el sueldo por ventas realizadas (suponemos una comisión del 20%)
        return sueldoBase * (0.2 * ventasRealizadas);
    }

    @Override
    public double calcularImpuesto() {
        return 0;
    }
}
