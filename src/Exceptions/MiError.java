package Exceptions;
import java.io.IOException;
import java.util.Scanner;

public class MiError {
    public static void main(String[] args) {
        System.out.println("Ingresa un número: ");
        Scanner scanner = new Scanner(System.in);

        try {
            int nEvauluado;
            Rango(scanner.nextInt());
        } catch (rangoDelN e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("Fin del programa");
    }

    static void Rango(int nEvaluado) throws rangoDelN{
        if (nEvaluado < 0 || nEvaluado > 100){

            rangoDelN miException = new rangoDelN("Número fuera del rango");
            throw miException;
        }

    }
}

class rangoDelN extends IOException {

    public rangoDelN() {
    }

    public rangoDelN(String mensajeError) {
        super(mensajeError);
    }

}
