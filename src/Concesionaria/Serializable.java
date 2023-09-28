package Concesionaria;

import java.util.ArrayList;

public interface Serializable {
    public void guardar(ArrayList<Vehiculo> listaVehiculos);

    public ArrayList<Vehiculo>  cargar();
}
