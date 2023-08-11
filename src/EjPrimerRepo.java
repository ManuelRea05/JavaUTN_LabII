import java.util.Scanner;

public class EjPrimerRepo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num1, num2, num3 , aux = 0;
        System.out.println("Ingrese un número: ");
        num1 = scanner.nextInt();
        System.out.println("Ingrese otro número: ");
        num2 = scanner.nextInt();
        System.out.println("Ingrese otro número: ");
        num3 = scanner.nextInt();

        if (num2 > num1){
            num2 = aux;
            num1 = num2;
            num2 = aux;
        } else if (num3 > num2){
            num3 = aux;
            num3 = num2;
            num3 = aux;
        }
        
    }
}
