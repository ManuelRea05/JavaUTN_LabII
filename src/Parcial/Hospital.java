package Parcial;

import java.io.*;
import java.util.*;
import java.io.Serializable;

public class Hospital {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Hospitall hospital = new Hospitall();

        List<Doctor> doctores = new ArrayList<>();
        // Cargar doctores al inicio
        doctores.add(new Doctor("Dr. Pedro Sanchez", 12345678, "15/05/1977", "Cardiología"));
        doctores.add(new Doctor("Dra. Sonia Vallejos", 23456789, "20/08/1984", "Pediatría"));

        Paciente paciente = new Paciente("Nombre", 123456, "01/01/1990", 1234567890                                                                , 1);
        paciente.agregarEventoHistorial("12/09/2012 - Inicio como paciente del Perrando");
        paciente.agregarEventoHistorial("13/10/2012 - Sus estudios le dieron alto en cosas no tan buenas");
        paciente.agregarEventoHistorial("18/05/2014 - Tiene los glóbulos rojos inflamados");


        int opcion;
        do {
            System.out.println("HHospital Julio C. Perrando - Av. 9 de Julio 1100 · 0362 444-2399");
            System.out.println("Menú:");
            System.out.println("1. Listar Doctores.");
            System.out.println("2. Registrar un nuevo paciente.");
            System.out.println("3. Actualizar información personal de un paciente.");
            System.out.println("4. Consultar el historial médico de un paciente.");
            System.out.println("5. Nuevo historial para un paciente.");
            System.out.println("6. Guardar Historial de pacientes en archivo.");
            System.out.println("7. Cargar Historial de pacientes desde archivo.");
            System.out.println("8. Salir.");
            System.out.print("Elija una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("Lista de Doctores:");
                    for (Doctor doctor : doctores) {
                        System.out.println(doctor);
                    }
                    break;
                case 2:
                    System.out.print("Ingrese el nombre del paciente: ");
                    String nombrePaciente = scanner.nextLine();

                    System.out.print("Ingrese el DNI del paciente: ");
                    int dniPaciente = scanner.nextInt();

                    System.out.print("Ingrese la fecha de nacimiento del paciente (dd/MM/yyyy): ");
                    String fechaNacimiento = scanner.next();

                    System.out.print("Ingrese el número de teléfono del paciente: ");
                    int numeroTelefono = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Ingrese el tipo de sangre del paciente: ");
                    int tipoSangre = scanner.nextInt();
                    scanner.nextLine();

                    Paciente nuevoPaciente = new Paciente(nombrePaciente, dniPaciente, fechaNacimiento, numeroTelefono, tipoSangre);
                    hospital.registrarPaciente(nuevoPaciente);
                    System.out.println("Paciente registrado con éxito.");
                    break;
                case 3:
                    System.out.print("Ingrese el DNI del paciente a actualizar: ");
                    int dniActualizar = scanner.nextInt();

                    Paciente pacienteActualizar = hospital.buscarPacientePorDNI(dniActualizar);

                    if (pacienteActualizar != null) {
                        System.out.print("Ingrese el nuevo número de teléfono del paciente: ");
                        int nuevoNumeroTelefono = scanner.nextInt();

                        System.out.print("Ingrese el nuevo tipo de sangre del paciente: ");
                        int nuevoTipoSangre = scanner.nextInt();
                        scanner.nextLine(); //

                        pacienteActualizar.setNroTelefono(nuevoNumeroTelefono);
                        pacienteActualizar.setTipoSangre(nuevoTipoSangre);

                        System.out.println("Información del paciente actualizada con éxito.");
                    } else {
                        System.out.println("Paciente no encontrado.");
                    }
                    break;
                case 4:
                    System.out.print("Indique el DNI del paciente a buscar: ");
                    int dniBuscar = scanner.nextInt();
                    scanner.nextLine();

                    Paciente pacienteEncontrado = hospital.buscarPacientePorDNI(dniBuscar);

                    if (pacienteEncontrado != null) {
                        pacienteEncontrado.verHistorialDeEventos();
                    } else {
                        System.out.println("Paciente no encontrado.");
                    }
                    break;
                case 5:
                    System.out.print("Ingrese el DNI del paciente para agregar un nuevo evento al historial médico: ");
                    int dniEvento = scanner.nextInt();

                    Paciente pacienteEvento = hospital.buscarPacientePorDNI(dniEvento);

                    if (pacienteEvento != null) {
                        System.out.print("Ingrese la fecha del evento (dd/MM/yyyy): ");
                        String fechaEvento = scanner.next();

                        System.out.print("Ingrese las observaciones: ");
                        String observaciones = scanner.nextLine();

                        pacienteEvento.agregarEventoHistorial(fechaEvento + " - " + observaciones);
                        System.out.println("Evento agregado al historial médico del paciente.");
                    } else {
                        System.out.println("Paciente no encontrado.");
                    }
                    break;
                case 6:
                    hospital.guardarDatosEnArchivo("datos.txt");
                    break;
                case 7:
                    hospital.cargarInformacionDesdeArchivo("datos.txt");
                    break;
                case 8:
                    System.out.println("Saliendo del sistema.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 8);
        scanner.close();
    }
}

interface Informacion {
    void verHistorialDeEventos();
}

abstract class Persona implements Serializable{
    private String nombre;
    private int dni;
    private String fechaNac;

    public Persona() {
    }

    public Persona(String nombre, int dni, String fechaNac) {
        this.nombre = nombre;
        this.dni = dni;
        this.fechaNac = fechaNac;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getDni() {
        return dni;
    }
    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getFechaNac() {
        return fechaNac;
    }
    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }
}

class Doctor extends Persona implements Serializable{
    private String especialidad;

    public Doctor() {
    }

    public Doctor(String nombre, int dni, String fechaNac, String especialidad) {
        super(nombre, dni, fechaNac);
        this.especialidad = especialidad;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    @Override
    public String toString() {
        return "Doctor: " + getNombre() + ", DNI: " + getDni() + ", Especialidad: " + especialidad;
    }
}

class Paciente extends Persona implements Informacion, Serializable{
    private int nroTelefono;
    private int tipoSangre;
    private List<String> historialMedico;

    public Paciente(String nombrePaciente, int dniPaciente, String fechaNacimiento, int numeroTelefono, int tipoSangre) {
        super(nombrePaciente, dniPaciente, fechaNacimiento);
        this.nroTelefono = nroTelefono;
        this.tipoSangre = this.tipoSangre;
        this.historialMedico = new ArrayList<>();
    }

    public void agregarEventoHistorial(String evento) {
        historialMedico.add(evento);
    }

    @Override
    public void verHistorialDeEventos() {
        if (historialMedico != null) {
            System.out.println("HISTORIAL MÉDICO:");
            for (String evento : historialMedico) {
                System.out.println(evento);
            }
        } else {
            System.out.println("El historial médico está vacío.");
        }
    }

    public int getNroTelefono() {
        return nroTelefono;
    }

    public int getTipoSangre() {
        return tipoSangre;
    }

    public List <String> getHistorialMedico() {
        return historialMedico;
    }

    public void setNroTelefono(int nroTelefono) {
        this.nroTelefono = nroTelefono;
    }

    public void setTipoSangre(int tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

}

class Hospitall implements Serializable {
    private List<Paciente> pacientes;

    public Hospitall() {
        this.pacientes = new ArrayList<>();
    }

    public void registrarPaciente(Paciente paciente) {
        pacientes.add(paciente);
    }

    public Paciente buscarPacientePorDNI(int dni) {
        for (Paciente paciente : pacientes) {
            if (paciente.getDni() == dni) {
                return paciente;
            }
        }
        return null;
    }

    public void guardarDatosEnArchivo(String nombreArchivo){
        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(nombreArchivo))) {
            salida.writeObject(this);
            System.out.println("Información de pacientes guardada en archivo exitosamente.");
        } catch (IOException e) {
            System.err.println("Error al guardar la información de pacientes en el archivo " + e.getMessage());
        }
    }

    public static Hospitall cargarInformacionDesdeArchivo(String nombreArchivo) {
        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(nombreArchivo))) {
            return (Hospitall) entrada.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al recuperar la información de pacientes desde el archivo: " + e.getMessage());
            return null;
        }
    }
}


