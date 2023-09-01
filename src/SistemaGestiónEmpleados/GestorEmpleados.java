package SistemaGestiónEmpleados;
import java.util.ArrayList;

public class GestorEmpleados {

    // Crea un array list de los empleados.
    ArrayList<Empleado> listaEmpleados = new ArrayList<>();
    public void agregarEmpleado(Empleado empleado) {
        listaEmpleados.add(empleado);
    }

    // Metodos para eliminar y modificar con el id
    public void modificarEmpleado(int id, Empleado nuevoEmpleado) {
        for (int i = 0; i < listaEmpleados.size(); i++) {
            if (listaEmpleados.get(i).id == id) {
                listaEmpleados.set(i, nuevoEmpleado);
                break;
            }
        }
    }
    public void eliminarEmpleado(int id) {
        for (int i = 0; i < listaEmpleados.size(); i++) {
            if (listaEmpleados.get(i).getId() == id) {
                listaEmpleados.remove(i);
                break;
            }
        }
    }

}
