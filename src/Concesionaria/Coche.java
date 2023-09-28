package Concesionaria;

import java.util.ArrayList;

public class Coche extends Vehiculo implements java.io.Serializable{
    public Coche(String marca, String modelo, double precio) {
        super(marca, modelo, precio);
    }

    @Override
    public double calcularImpuesto() {
        return precio * 0.15;
    }

    @Override
    public void mostrarInfo() {
        System.out.println("Coche:");
        System.out.println("Marca: " + marca);
        System.out.println("Modelo: " + modelo);
        System.out.println("Precio: $" + precio);
    }

}
