package Mod2doParcial;
import java.sql.*;
import java.util.*;

public class HospitalMain {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:33061/hospital_db";
        String usuario = "root";
        String pass = "";

        Scanner scanner = new Scanner(System.in);

        try {
            // Establecer la conexión a la base de datos
            Connection conexion = DriverManager.getConnection(url, usuario, pass);

            while (true) {
                System.out.println("Menú:");
                System.out.println("1. Mostrar listas");
                System.out.println("2. Agregar paciente");
                System.out.println("3. Eliminar estudiante");
                System.out.println("4. Mostrar listas");
                System.out.println("6. Salir");
                System.out.print("Selecciona una opción: ");

                int opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir la nueva línea

                switch (opcion) {
                    case 1:
                        // mostrarEstudiantes(conexion);
                        break;
                    case 2:
                       agregarPaciente(conexion, scanner);
                        break;
                    case 3:
                        // editarEstudiante(conexion, scanner);
                        break;
                    case 4:
                        // eliminarEstudiante(conexion, scanner);
                        break;
                    case 5:
                        //
                        break;
                    case 6:
                        // Cerrar la conexion y salir del programa
                        conexion.close();
                        scanner.close();
                        System.exit(0);
                    default:
                        System.out.println("Opción no válida. Por favor, elige una opción válida.");
                        break;
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

abstract class Persona{
    protected String nombre;
    protected int edad;

    public Persona() {
    }

    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}

class Paciente extends Persona{
    private String historialMedico;
    private String fechaIngreso;

    public Paciente(String nombre, int edad, String historialMedico, String fechaIngreso) {
        super(nombre, edad);
        this.historialMedico = historialMedico;
        this.fechaIngreso = fechaIngreso;
    }

    public String getHistorialMedico() {
        return historialMedico;
    }

    public void setHistorialMedico(String historialMedico) {
        this.historialMedico = historialMedico;
    }
}
class Doctor extends Persona {
    private String especialidad;

    public Doctor(String especialidad) {
        this.especialidad = especialidad;
    }

    public Doctor(String nombre, int edad, String especialidad) {
        super(nombre, edad);
        this.especialidad = especialidad;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
}
class Hospital {
    private List <Paciente> listaPacientes;
    private List <Doctor> listaDoctores;

    public Hospital() {
        this.listaPacientes = new ArrayList <>();
        this.listaDoctores = new ArrayList <>();
    }

    public void agregarPaciente(Paciente paciente) {
        listaPacientes.add(paciente);
    }

    public void agregarDoctor(Doctor doctor) {
        listaDoctores.add(doctor);
    }
}

    private static void agregarPaciente(Connection conexion, Scanner scanner) throws SQLException {
        // Solicita al usuario que ingrese los detalles del paciente
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Edad: ");
        String edad = scanner.nextLine();
        System.out.print("Historial médico: ");
        String historialMedico = scanner.nextLine();
        System.out.print("Fecha de ingreso (YYYY-MM-DD): ");
        String fechaIngreso = scanner.nextLine();

        // Consulta SQL
        String consulta = "INSERT INTO estudiantes (nombre, apellido, legajo, dni, fecha_nacimiento, direccion, telefono, email) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        // Crea un PreparedStatement para ejecutar la consulta SQL con valores reales.
        PreparedStatement preparedStatement = conexion.prepareStatement(consulta);
        preparedStatement.setString(1, nombre);
        preparedStatement.setString(2, edad);
        preparedStatement.setString(3, historialMedico);
        preparedStatement.setString(5, fechaIngreso);

        // Ejecuta la consulta y obtiene el n�mero de filas afectadas.
        int filasAfectadas = preparedStatement.executeUpdate();

        // Verifica si la inserci�n fue exitosa y muestra un mensaje apropiado.
        if (filasAfectadas > 0) {
            System.out.println("Paciente agregado exitosamente.");
        } else {
            System.out.println("No se pudo agregar el paciente.");
        }

        // Cierra el PreparedStatement para liberar recursos.
        preparedStatement.close();
    }



}

