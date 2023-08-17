package Recursion;

public class Factorial {
    public static void main(String[] args) {

        int recursivo = factorial(5);
        System.out.println("Factorial recursivo: " + recursivo);

        int iterativo = factorial(5L);
        System.out.println("Factorial iterativo: " + iterativo);

    }

    // Funciones factorial con sobre carga de métodos

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
