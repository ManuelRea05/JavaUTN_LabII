import java.util.Scanner;

public class EjPrimerRepo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingresa el primer número: ");
        int num1 = scanner.nextInt();

        System.out.print("Ingresa el segundo número: ");
        int num2 = scanner.nextInt();

        System.out.print("Ingresa el tercer número: ");
        int num3 = scanner.nextInt();

        int mayor = Math.max(Math.max(num1, num2), num3);

        int menor = Math.min(Math.min(num1, num2), num3);

        int medio = num1 + num2 + num3 - mayor - menor;

        System.out.println("Números ordenados: " + mayor + ", " + medio + ", " + menor);
    }
}
