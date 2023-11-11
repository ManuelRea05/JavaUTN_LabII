package SistemaGestionEmpleados;

public abstract class Empleado {
    protected String nombre;
    protected int id;
    protected double sueldoBase;

    public Empleado(String nombre, int id, double sueldoBase) {
        this.nombre = nombre;
        this.id = id;
        this.sueldoBase = sueldoBase;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "nombre='" + nombre + '\'' +
                ", id=" + id +
                ", sueldoBase=" + sueldoBase+ ",";
    }

    //Metodo abstracto
    abstract double calcularSueldo();
}


