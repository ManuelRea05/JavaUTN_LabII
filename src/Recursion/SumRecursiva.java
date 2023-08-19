package Recursion;

public class SumRecursiva {

    public static class SumatoriaRecursiva {
        public static void main(String[] args) {
            int num1 = 5;
            int num2 = 7;
            int resultado1 = calcularSumatoria(num1);
            System.out.println("La sumatoria de los números enteros desde 1 hasta " + num1 + " es: " + resultado1);

            int resultado2 = calcularSumatoria(num2);
            System.out.println("La sumatoria de los números enteros desde 1 hasta " + num2 + " es: " + resultado2);
        }

        public static int calcularSumatoria(int n) {
            if (n == 1) {
                return 1;
            } else {
                return n + calcularSumatoria(n - 1);
            }
        }
    }
}
