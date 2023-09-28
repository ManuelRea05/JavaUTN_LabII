package Concesionaria;

import java.io.*;
import java.util.ArrayList;
class Concesionaria implements Serializable {
    private ArrayList<Vehiculo> listaVehiculo = new ArrayList<Vehiculo>();

    public ArrayList<Vehiculo> getListaVehiculos() {
        return this.listaVehiculo;
    }

    public void setListaVehiculos(ArrayList<Vehiculo> listaVehiculos) {
        this.listaVehiculo = listaVehiculos;
    }

    public void agregarVehiculo(Vehiculo vehiculo) {
        listaVehiculo.add(vehiculo);
    }

    public void eliminarVehiculo(String marca, String modelo) {
        for (int i = 0; i < listaVehiculo.size(); i++) {
            Vehiculo vehiculo = listaVehiculo.get(i);
            if (vehiculo.getMarca().equals(marca) && vehiculo.getModelo().equals(modelo)) {
                listaVehiculo.remove(i);
                System.out.println("Vehiculo eliminado: " + marca + " " + modelo);
            }
        }
        System.out.println("No se encontró un vehículo con la marca " + marca + " y modelo " + modelo);
    }

    public void editarPrecio(String marca, String modelo, double nuevoPrecio) {
        for (int i = 0; i < listaVehiculo.size(); i++) {
            Vehiculo vehiculo = listaVehiculo.get(i);
            if (vehiculo.getMarca().equals(marca) && vehiculo.getModelo().equals(modelo)) {
                vehiculo.setPrecio(nuevoPrecio);
                System.out.println("Precio del vehículo actualizado. Nuevo precio: " + nuevoPrecio);
            }
        }
    }

    public void mostrarInventario() {
        System.out.println("Vehículos de la Concesionaria: ");
        for (Vehiculo vehiculo : listaVehiculo) {
            vehiculo.mostrarInfo();
            System.out.println("-------------");
        }
    }


    @Override
    public void guardar(ArrayList<Vehiculo> listaVehiculos) {
        try {
            // Crear un flujo de salida para escribir en un archivo llamado "curso.txt"
            FileOutputStream archivoSalida = new FileOutputStream("concesionaria.txt");
            // Crear un flujo de objeto para escribir objetos en el flujo de salida
            ObjectOutputStream flujoSalida = new ObjectOutputStream(archivoSalida);

            flujoSalida.writeObject(listaVehiculo);

            flujoSalida.close();
        } catch (Exception e) {
            e.printStackTrace(); // En caso de error, imprimir información de la excepción
        }
    }

    @Override
    public ArrayList<Vehiculo> cargar() {
        ArrayList<Vehiculo> listaVehiculo = null;
        try {
            // Abrir un flujo de entrada para leer desde el archivo "curso.txt"
            FileInputStream archivoEntrada = new FileInputStream("concesionaria.txt");
            // Crear un flujo de objeto para leer objetos desde el flujo de entrada
            ObjectInputStream flujoEntrada = new ObjectInputStream(archivoEntrada);
            // Leer el objeto serializado desde el flujo de entrada y convertirlo a un objeto Curso
            listaVehiculo = (ArrayList<Vehiculo>) flujoEntrada.readObject();

            flujoEntrada.close();

        } catch (Exception e) {
            e.printStackTrace(); // En caso de error, imprimir información de la excepción
        }
        return listaVehiculo;
    }
}