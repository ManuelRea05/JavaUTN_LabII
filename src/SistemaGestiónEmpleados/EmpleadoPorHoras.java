package SistemaGestiónEmpleados;

class EmpleadoPorHoras extends Empleado implements Impuesto{

    protected int horasTrabajadas;

    public EmpleadoPorHoras(String nombre, int id, double sueldoBase, int horasTrabajadas) {
        super(nombre, id, sueldoBase);
        this.horasTrabajadas = horasTrabajadas;
    }

    // Implementando metodos abstractos
    @Override
    double calcularSueldo() {
        // Calcula el sueldo por horas trabajadas
        return sueldoBase * horasTrabajadas;
    }

    @Override
    public double calcularImpuesto() {
        return 0;
    }
}
