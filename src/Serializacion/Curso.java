package Serializacion;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;

public class Curso implements Serializable {
    transient LinkedList<Estudiante> estudiantes; // Marcar como transient
    String nombre;

    public Curso(String nombre) {
        this.nombre = nombre;
        estudiantes = new LinkedList<>();
    }

    public void agregarEstudiante(Estudiante estudiante) {
        estudiantes.add(estudiante);
    }

    public void eliminarEstudianteLegajo(int nroLegajo) {
        for (int i = 0; i < estudiantes.size(); i++) {
            // Recorre la lista de estudiantes y verifica si el número de legajo coincide con nroLegajo
            if (estudiantes.get(i).legajo == nroLegajo) {
                estudiantes.remove(i); // Si hay coincidencia, elimina al estudiante en esa posición
            }
        }
    }


    public void modificarEstudiante(String nombre, int nroLegajo) {
        for (int i = 0; i < estudiantes.size(); i++) {
            // Recorre la lista de estudiantes y verifica si el número de legajo coincide con nroLegajo
            if (estudiantes.get(i).legajo == nroLegajo) {
                estudiantes.get(i).nombre = nombre; // Si hay coincidencia, modifica el nombre del estudiante en esa posición
            }
        }
    }


    // Agregar métodos para serializar y deserializar estudiantes
    private void writeObject(ObjectOutputStream out) throws IOException {
        /**Se utiliza `throws IOException` para manejar posibles errores
         * con la entrada/salida de datos durante la serialización de la clase `Curso`.
         */
        out.defaultWriteObject(); // Llama al método predeterminado de serialización de la superclase
        out.writeObject(estudiantes); // Escribe la lista de estudiantes en el flujo de salida
    }


    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject(); // Llama al método predeterminado de deserialización de la superclase
        estudiantes = (LinkedList<Estudiante>) in.readObject(); // Lee la lista de estudiantes desde el flujo de entrada
    }

}
