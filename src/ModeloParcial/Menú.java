package ModeloParcial;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Menú {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hotel hotel = new Hotel("Mi Hotel");

        hotel.agregarHabitacion(new Habitacion(100, 2, 2));
        hotel.agregarHabitacion(new Habitacion(101, 1, 1));
        hotel.agregarHabitacion(new Habitacion(102, 2, 2));

        while (true) {
            System.out.println("******** Menú de Concesionaria ********");
            System.out.println("1. Ver la lista de habitaciones.");
            System.out.println("2. Reservar una habitación");
            System.out.println("3. Cancelar una reserva");
            System.out.println("4. Guardar reservas en un archivo");
            System.out.println("5. Cargar reservas desde un archivo");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextInt();

            switch (opcion){
                case 1:
                    // mostrar lista
            }
            switch (opcion){
                case 2:
                    System.out.println("Habitaciones disponibles: 100 \n 101 \n 102");
                    System.out.println("Ingrese el número de la habitación que desea reservar: ");
                    int numeroHabitacion = scanner.nextInt();
                    scanner.nextLine(); // Consumir el salto de línea
                    Habitacion habitacionReservada = hotel.buscarHabitacion(numeroHabitacion);
                    if (habitacionReservada != null) {
                        if (habitacionReservada.estaDisponible()) {
                            ArrayList<Huesped> huespedes = new ArrayList<>();

            }
        }
    }
}
interface MostrarInformacion {
    void mostrarDetalles();
}

class Habitacion implements MostrarInformacion {
    private int num;
    private int cantCamas;
    private int capHuespedes;
    private boolean ocupada;
    private ArrayList <String> listaHuespedes;

    public Habitacion(int num, int cantCamas, int capHuespedes) {
        this.num = num;
        this.cantCamas = cantCamas;
        this.capHuespedes = capHuespedes;
        this.ocupada = false;
        this.listaHuespedes = listaHuespedes;
    }

    public int getNum() {
        return num;
    }

    public int getCantCamas() {
        return cantCamas;
    }

    public int getCapHuespedes() {
        return capHuespedes;
    }

    public boolean isOcupada() {
        return ocupada;
    }


    public ArrayList <String> getListaHuespedes() {
        return listaHuespedes;
    }

    public void setListaHuespedes(ArrayList <String> listaHuespedes) {
        this.listaHuespedes = listaHuespedes;
    }

    @Override
    public void mostrarDetalles() {
        System.out.println("Cantidad de camas: " + cantCamas);
        System.out.println("Capacidad de huéspedes: " + capHuespedes);
        System.out.println("Estado: " + (ocupada ? "Ocupada" : "Disponible"));
        if (ocupada) {
            System.out.println("Huéspedes: " + listaHuespedes);
        }
    }

    public boolean estaDisponible() {
        return !ocupada;
    }

    public void reservar(String huesped) {
        if (!ocupada) {
            ocupada = true;
            listaHuespedes.add(huesped);
            System.out.println("La habitación se ha reservado para " + huesped);
        } else {
            System.out.println("La habitación ya está ocupada.");
        }
    }

    public void cancelarReserva() {
        if (ocupada) {
            ocupada = false;
            listaHuespedes.clear();
            System.out.println("La reserva se ha cancelado.");
        } else {
            System.out.println("La habitación no está ocupada.");
        }
    }
}

abstract class Persona implements MostrarInformacion{
    private String nombreYApe;
    private String nacionalidad;

    public Persona(String nombre, int dni) {
        this.nombreYApe = nombre;
        this.nacionalidad = nacionalidad;
    }
}

class Huespedes extends Persona{
    private int id;

    public Huespedes(String nombreYApe, int dni, int id) {
        super(nombreYApe, dni);
        this.id = id;
    }

    @Override
    public void mostrarDetalles() {

    }
}

class Hotel {
    private String nombre;
    private ArrayList<Habitacion> habitaciones;

    public Hotel(String nombre) {
        this.nombre = nombre;
        this.habitaciones = new ArrayList<>();
    }

    public void agregarHabitacion(Habitacion habitacion) {
        habitaciones.add(habitacion);
    }

    public void mostrarListaHabitaciones() {
        System.out.println("Lista de Habitaciones en " + nombre + ":");
        for (Habitacion habitacion : habitaciones) {
            habitacion.mostrarDetalles();
        }
    }

    public Habitacion buscarHabitacion(int numeroHabitacion) {
        for (Habitacion habitacion : habitaciones) {
            if (habitacion.getNum() == numeroHabitacion) {
                return habitacion;
            }
        }
        return null;
    }

    public void guardarReservasEnArchivo(String nombreArchivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("reservas.txt"))) {
            oos.writeObject(habitaciones);
            System.out.println("Reservas guardadas en el archivo: " + nombreArchivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cargarReservasDesdeArchivo(String nombreArchivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("reservas.txt"))) {
            habitaciones = (ArrayList<Habitacion>) ois.readObject();
            System.out.println("Reservas cargadas desde el archivo: " + nombreArchivo);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

