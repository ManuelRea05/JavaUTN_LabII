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
    private List<Paciente> listaPacientes;
    private List<Doctor> listaDoctores;

    public Hospital() {
        this.listaPacientes = new ArrayList<>();
        this.listaDoctores = new ArrayList<>();
    }

    public void agregarPaciente(Paciente paciente) {
        listaPacientes.add(paciente);
    }

    }

}

