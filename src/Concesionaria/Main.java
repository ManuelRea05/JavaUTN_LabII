package Concesionaria;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Concesionaria concesionaria = new Concesionaria();

        while (true) {
            System.out.println("******** Menú de Concesionaria ********");
            System.out.println("1. Agregar vehículo");
            System.out.println("2. Eliminar vehículo");
            System.out.println("3. Editar precio de vehículo");
            System.out.println("4. Mostrar inventario");
            System.out.println("5. Guardar concesionaria");
            System.out.println("6. Cargar concesionaria");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea

            switch (opcion) {
                case 1:
                    agregarVehiculo(scanner, concesionaria);
                    break;
                case 2:
                    eliminarVehiculo(scanner, concesionaria);
                    break;
                case 3:
                    editarPrecio(scanner, concesionaria);
                    break;
                case 4:
                    concesionaria.mostrarInventario();
                    break;
                case 5:
                    concesionaria.guardar(concesionaria.getListaVehiculos());
                    break;
                case 6:
                    concesionaria.setListaVehiculos(concesionaria.cargar());
                    break;
                case 7:
                    System.out.println("¡Hasta luego!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        }
    }

    // Método para agregar un vehículo
    private static void agregarVehiculo(Scanner scanner, Concesionaria concesionaria) {
        System.out.print("Ingrese la marca del vehículo: ");
        String marca = scanner.nextLine();
        System.out.print("Ingrese el modelo del vehículo: ");
        String modelo = scanner.nextLine();
        System.out.print("Ingrese el precio del vehículo: ");
        double precio = scanner.nextDouble();
        scanner.nextLine(); // Consumir la nueva línea

        System.out.println("Seleccione el tipo de vehículo:");
        System.out.println("1. Coche");
        System.out.println("2. Moto");
        int tipoVehiculo = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea

        if (tipoVehiculo == 1) {
            scanner.nextLine(); // Consumir la nueva línea
            concesionaria.agregarVehiculo(new Coche(marca, modelo, precio));
        } else if (tipoVehiculo == 2) {
            scanner.nextLine(); // Consumir la nueva línea
            concesionaria.agregarVehiculo(new Moto(marca, modelo, precio));
        } else {
            System.out.println("Tipo de vehículo no válido.");
        }
    }

    // Método para eliminar un vehículo
    private static void eliminarVehiculo(Scanner scanner, Concesionaria concesionaria) {
        System.out.print("Ingrese la marca del vehículo a eliminar: ");
        String marca = scanner.nextLine();
        System.out.print("Ingrese el modelo del vehículo a eliminar: ");
        String modelo = scanner.nextLine();
        concesionaria.eliminarVehiculo(marca, modelo);
    }

    // Método para editar el precio de un vehículo
    private static void editarPrecio(Scanner scanner, Concesionaria concesionaria) {
        System.out.print("Ingrese la marca del vehículo cuyo precio desea editar: ");
        String marca = scanner.nextLine();
        System.out.print("Ingrese el modelo del vehículo cuyo precio desea editar: ");
        String modelo = scanner.nextLine();
        System.out.print("Ingrese el nuevo precio: ");
        double nuevoPrecio = scanner.nextDouble();
        concesionaria.editarPrecio(marca, modelo, nuevoPrecio);
    }
}

