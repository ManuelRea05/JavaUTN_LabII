package Concesionaria;

public class Moto extends Vehiculo implements java.io.Serializable{

    public Moto(String marca, String modelo, double precio) {
        super(marca, modelo, precio);
    }

    @Override
    public double calcularImpuesto() {
        return precio * 0.10;
    }

    @Override
    public void mostrarInfo() {
        System.out.println("Moto:");
        System.out.println("Marca: " + marca);
        System.out.println("Modelo: " + modelo);
        System.out.println("Precio: $" + precio);
    }

}
