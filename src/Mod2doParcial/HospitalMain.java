package Mod2doParcial;

import java.sql.*;


abstract class Persona{
    protected String nombre;
    protected int edad;

    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }
}

class Paciente extends Persona{
    private String historialMedico;
    private int doctorC;
    private Date fechaIngreso;

    public Paciente(String nombre, int edad, String historialMedico, int doctorC, Date fechaIngreso) {
        super(nombre, edad);
        this.historialMedico = historialMedico;
        this.doctorC = doctorC;
        this.fechaIngreso = fechaIngreso;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }


    public String getHistorialMedico() {
        return historialMedico;
    }

    public int getDoctorC() {
        return doctorC;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }
}

class Doctor extends Persona{
    private String especialidad;

    public Doctor(String nombre, int edad, String especialidad) {
        super(nombre, edad);
        this.especialidad = especialidad;
    }

    public String getEspecialidad() {
        return especialidad;
    }
}

class Hospital {
    public void agregarPaciente(Paciente paciente) {
        // Agregar el paciente a la base de datos
        String consulta = "INSERT INTO pacientes (nombre, edad, historial_medico, doctor, fecha_ingreso) VALUES ('" + paciente.getNombre() + "', " + paciente.getEdad() + ", '" + paciente.getHistorialMedico() + "', " + paciente.getDoctorC() + ", '" + paciente.getFechaIngreso() + "')";
        DBHelper.ejecutarConsulta(consulta);
        System.out.println("Paciente agregado correctamente");
    }

    public void eliminarPaciente(String nombrePaciente) {
        String consulta = "DELETE FROM pacientes WHERE `pacientes`.`nombre` = '"+nombrePaciente+"'";
        DBHelper.ejecutarConsulta(consulta);
        System.out.println("Paciente "+nombrePaciente+" eliminado correctamente");
    }

    public void mostrarListaPacientes() throws SQLException {
        String consulta = "SELECT * FROM `pacientes`";
        ResultSet resultado = DBHelper.ejecutarConsultaConResultado(consulta);

        // Imprime una cabecera de columnas para los datos de los estudiantes.
        System.out.println("ID\tNombre\tEdad\tHistorial Medico\tFecha Ingreso\tDoctor");

        listarPacientes(resultado);
        resultado.close();
    }

    public void asignarDoctorCabecera(String nombreDoctor, String nombrePaciente) throws SQLException {
        String consulta = "UPDATE pacientes SET doctor = (SELECT id FROM doctores WHERE nombre = '"+nombreDoctor+"') WHERE nombre = '"+nombrePaciente+"'";
        DBHelper.ejecutarConsulta(consulta);

    }

    public void mostrarPacientesEntreFechas(String fechaInicio, String fechaFin) {
        String consulta = "SELECT * FROM `pacientes` WHERE `fecha_ingreso` BETWEEN '"+fechaInicio+"' AND '"+fechaFin +"'";
        ResultSet resultado = DBHelper.ejecutarConsultaConResultado(consulta);
        listarPacientes(resultado);
    }

    public void listarPacientes(ResultSet resultado){
        if (resultado != null) {
            try {
                System.out.println("Lista de Pacientes:");
                System.out.printf("%-10s %-15s %-5s %-20s %-12s %-10s\n", "ID", "Nombre", "Edad", "Historial Médico", "Fecha Ingreso", "Doctor");

                while (resultado.next()) {
                    int id = resultado.getInt("id");
                    String nombre = resultado.getString("nombre");
                    int edad = resultado.getInt("edad");
                    String historialMedico = resultado.getString("historial_medico");
                    Date fechaIngreso = resultado.getDate("fecha_ingreso");
                    int idDoctor = resultado.getInt("doctor");

                    System.out.printf("%-10d %-15s %-5d %-20s %-12s %-10d\n", id, nombre, edad, historialMedico, fechaIngreso, idDoctor);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}


class DBHelper {
        private static final String URL = "jdbc:mysql://localhost:3306/hospital_db";
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

class HospitalMain{
    public static void main(String[] args) throws SQLException {
        Hospital hospital = new Hospital();

        Date fechaActual = new Date(2023 - 1900, 1 - 1, 10);

        Paciente paciente1 = new Paciente("Manuel", 45, "Ninguno", 1, fechaActual);
        Paciente paciente2 = new Paciente("Santiago", 45, "Sufrio de desamor", 1, fechaActual);
        // hospital.agregarPaciente(paciente1);
        // hospital.mostrarListaPacientes();
       // hospital.mostrarPacientesEntreFechas("2023-01-10", "2023-03-10")
        //hospital.eliminarPaciente("Santiago");
        //hospital.agregarPaciente(paciente2);
        hospital.asignarDoctorCabecera("Doctor2", "Santiago");
    }
}

