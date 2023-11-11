package SistemaGestionEmpleados;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GestorEmpleados gestor = new GestorEmpleados();

        while (true) {
            System.out.println("\n1. Agregar empleado\n2. Modificar empleado\n3. Eliminar empleado\n4. Mostrar empleados\n5. Salir");
            System.out.print("Elige una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            //Switch para cargar los datos
            switch (opcion) {
                case 1:
                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("ID: ");
                    int id = scanner.nextInt();
                    System.out.print("Sueldo base: ");
                    double sueldoBase = scanner.nextDouble();
                    System.out.println("Tipo de empleado:\n1. Por horas\n2. Asalariado\n3. Comisión");
                    int tipoEmpleado = scanner.nextInt();
                    scanner.nextLine();

                    Empleado empleado;

                    // Un switch dentro de un try para elegir el tipo de empleado
                    try {
                        switch (tipoEmpleado) {
                            case 1:
                                System.out.print("Horas trabajadas: ");
                                int horasTrabajadas = scanner.nextInt();
                                empleado = new EmpleadoPorHoras(nombre, id, sueldoBase, horasTrabajadas);
                                break;
                            case 2:
                                empleado = new EmpleadoAsalariado(nombre, id, sueldoBase);
                                break;
                            case 3:
                                System.out.print("Ventas realizadas: ");
                                double ventasRealizadas = scanner.nextDouble();
                                empleado = new EmpleadoComision(nombre, id, sueldoBase, ventasRealizadas);
                                break;
                            default:
                                System.out.println("Opción inválida.");
                                continue;
                        }

                        gestor.agregarEmpleado(empleado);
                        System.out.println("Empleado agregado.");
                    } catch (InputMismatchException e) {
                        System.out.println("Error en la entrada de datos. Inténtalo de nuevo.");
                    }
                    break;

                case 2:
                    System.out.print("ID del empleado a modificar: ");
                    int idModificar = scanner.nextInt();
                    scanner.nextLine();

                    Empleado empleadoModificar = null;
                    for (Empleado emp : gestor.listaEmpleados) {
                        if (emp.id == idModificar) {
                            empleadoModificar = emp;
                            break;
                        }
                    }

                    if (empleadoModificar != null) {
                        System.out.println("Modificando empleado con ID: " + idModificar);

                        // Obtengo nuevos datos para el empleado modificado
                        System.out.print("Nuevo nombre: ");
                        String nuevoNombre = scanner.nextLine();

                        System.out.print("Nuevo sueldo base: ");
                        double nuevoSueldoBase = scanner.nextDouble();
                        scanner.nextLine();

                        // Creo un nuevo objeto Empleado con los nuevos datos
                        Empleado nuevoEmpleado = new EmpleadoAsalariado(nuevoNombre, empleadoModificar.id, nuevoSueldoBase);

                        // Modifico el empleado existente con el nuevo objeto
                        gestor.modificarEmpleado(idModificar, nuevoEmpleado);

                        System.out.println("Empleado modificado.");
                    } else {
                        System.out.println("No se encontró un empleado con ese ID.");
                    }
                    break;
                case 3:
                    System.out.print("ID del empleado a eliminar: ");
                    int idEliminar = scanner.nextInt();
                    scanner.nextLine();

                    boolean empleadoEncontrado = false;
                    for (Empleado emp : gestor.listaEmpleados) {
                        if (emp.id == idEliminar) {
                            gestor.eliminarEmpleado(emp.getId());
                            empleadoEncontrado = true;
                            System.out.println("Empleado eliminado.");
                            break;
                        }
                    }

                    if (!empleadoEncontrado) {
                        System.out.println("No se encontró un empleado con ese ID.");
                    }
                    break;

                case 4:
                    System.out.println("Lista de empleados:");
                    for (Empleado emp : gestor.listaEmpleados) {
                        System.out.println(emp.toString());
                    }
                    break;

                case 5:
                    System.out.println("Saliendo...");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opción inválida.");
            }
        }
    }
}

