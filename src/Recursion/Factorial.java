package Recursion;

public class Factorial {
    public static void main(String[] args) {
        int recursivo = factorial(5);
        System.out.println("Factorial recursivo1: " + recursivo);
        int recursivo2 = factorial(7);
        System.out.println("Factorial recursivo2: " + recursivo2);

        System.out.println("");
        int iterativo = factorial(5L);
        System.out.println("Factorial iterativo1: " + iterativo);
        int iterativo2 = factorial(7L);
        System.out.println("Factorial iterativo2: " + iterativo2);
    }

    // Funciones factorial con sobrecarga de métodos

    public static int factorial(int n){
        if (n <= 1){
            return 1;
        }
        return  n * factorial(n -1);
    }

    public static int factorial(long x) {
        int result = 1;
        for (long i = 1; i <= x; i++) {
            result *= i;
        }
        return result;
    }
}
