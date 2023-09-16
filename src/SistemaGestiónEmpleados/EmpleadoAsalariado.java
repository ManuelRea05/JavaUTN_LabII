package SistemaGestiónEmpleados;

class EmpleadoAsalariado extends Empleado implements Impuesto{
    public EmpleadoAsalariado(String nombre, int id, double sueldoBase) {
        super(nombre, id, sueldoBase);
    }

    // Implementando metodos abstractos
    @Override
    public String toString() {
        return super.toString() + " Sueldo neto=" + calcularSueldo() + "}";
    }
    @Override
    double calcularSueldo() {
        return sueldoBase;
    }

    @Override
    public double calcularImpuesto() {
        return 0;
    }
}
