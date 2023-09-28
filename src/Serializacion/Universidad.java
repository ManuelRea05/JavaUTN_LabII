package Serializacion;

import java.io.*;
import java.util.LinkedList;

public class Universidad {
    public static void main (String[] args){
        Curso curso = new Curso("Programacion 1");
        curso.agregarEstudiante(new Estudiante("Juan", 123));
        curso.agregarEstudiante(new Estudiante("Pedro", 456));
        curso.agregarEstudiante(new Estudiante("Maria", 789));
        curso.agregarEstudiante(new Estudiante("Jose", 101112));

        Serializador serializador = new Serializador();
        serializador.serializar(curso);

        Curso cursoDeserializado = serializador.deserializar();
        System.out.println(cursoDeserializado.estudiantes);
    }
}

class Serializador{
    public void serializar(Curso curso) {
        try {
            // Crear un flujo de salida para escribir en un archivo llamado "curso.txt"
            FileOutputStream archivoSalida = new FileOutputStream("curso.txt");
            // Crear un flujo de objeto para escribir objetos en el flujo de salida
            ObjectOutputStream flujoSalida = new ObjectOutputStream(archivoSalida);
            // Escribir el objeto "curso" en el flujo de salida
            flujoSalida.writeObject(curso);
            // Importante: cerrar el flujo de salida para liberar recursos
            flujoSalida.close();
        } catch (Exception e) {
            e.printStackTrace(); // En caso de error, imprimir información de la excepción
        }
    }


    public Curso deserializar() {
        Curso curso = null;
        try {
            // Abrir un flujo de entrada para leer desde el archivo "curso.txt"
            FileInputStream archivoEntrada = new FileInputStream("curso.txt");
            // Crear un flujo de objeto para leer objetos desde el flujo de entrada
            ObjectInputStream flujoEntrada = new ObjectInputStream(archivoEntrada);
            // Leer el objeto serializado desde el flujo de entrada y convertirlo a un objeto Curso
            curso = (Curso) flujoEntrada.readObject();
            // Importante: cerrar el flujo de entrada para liberar recursos
            flujoEntrada.close();
        } catch (Exception e) {
            e.printStackTrace(); // En caso de error, imprimir información de la excepción
        }
        return curso; // Devolver el objeto Curso deserializado
    }

}

class Estudiante implements Serializable {
    String nombre;
    int legajo;

    public Estudiante(String nombre, int legajo){
        this.nombre = nombre;
        this.legajo = legajo;
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "nombre='" + nombre + '\'' +
                ", legajo=" + legajo +
                '}';
    }
}
