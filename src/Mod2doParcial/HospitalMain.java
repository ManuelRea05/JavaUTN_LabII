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
    public static void main(String[] args) {
        Hospital hospital = new Hospital();

        Date fechaActual = new Date(2023 - 1900, 1 - 1, 10);

        Paciente paciente1 = new Paciente("Manuel", 45, "Ninguno", 1, fechaActual);

        hospital.agregarPaciente(paciente1);
    }
}

