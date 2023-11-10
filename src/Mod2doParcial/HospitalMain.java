package Mod2doParcial;
import java.sql.*;
import java.util.*;
import java.util.Date;

public class HospitalMain {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Hospital hospital = new Hospital();

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
                    agregarPaciente();
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

    }

    abstract class Persona {
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

    class Paciente extends Persona {
        private String historialMedico;
        private int doctorCabecera;
        private Date fechaIngreso;

        public Paciente(String nombre, int edad, String historialMedico, int doctorCabecera, Date fechaIngreso) {
            super(nombre, edad);
            this.historialMedico = historialMedico;
            this.doctorCabecera = doctorCabecera;
            this.fechaIngreso = fechaIngreso;
        }

        public String getHistorialMedico() {
            return historialMedico;
        }

        public int getDoctorCabecera() {
            return doctorCabecera;
        }

        public Date getFechaIngreso() {
            return fechaIngreso;
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
        public void agregarPaciente(Paciente paciente) {
            // Agregar el paciente a la base de datos
            String consulta = "INSERT INTO pacientes (nombre, edad, historial_medico, doctor, fecha_ingreso) VALUES ('" + paciente.getNombre() + "', " + paciente.getEdad() + ", '" + paciente.getHistorialMedico() + "', " + paciente.getDoctorCabecera() + ", '" + paciente.getFechaIngreso() + "')";
            DBHelper.ejecutarConsulta(consulta);
        }

        class DBHelper {
            private static final String URL = "jdbc:mysql://localhost:33061/hospital_db";
            private static final String USER = "root";
            private static final String PASSWORD = "";

            // Método para ejecutar una consulta sin devolver resultados
            public static void ejecutarConsulta(String consulta) {
                try {
                    // Establecer la conexión con la base de datos
                    Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

                    // Crear la declaración
                    try (PreparedStatement statement = connection.prepareStatement(consulta)) {
                        // Ejecutar la consulta
                        statement.executeUpdate();
                    }

                    // Cerrar la conexión
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            // Método para ejecutar una consulta y devolver un conjunto de resultados
            public static ResultSet ejecutarConsultaConResultado(String consulta) {
                try {
                    // Establecer la conexión con la base de datos
                    Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

                    // Crear la declaración
                    PreparedStatement statement = connection.prepareStatement(consulta);

                    // Ejecutar la consulta y devolver el conjunto de resultados
                    return statement.executeQuery();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
    }
}