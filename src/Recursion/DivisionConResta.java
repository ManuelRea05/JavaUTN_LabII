package Recursion;

public class DivisionConResta {
    public static void main(String[] args) {
        // Division iterativa
        DivConResta(20, 4);
        DivConResta(45, 7);
        DivConResta(16, 4);
        System.out.println("");

        // Division recursiva
        System.out.println("El resultado es: "+DivConResta(20L, 4L));
        System.out.println("El resultado es: "+DivConResta(45L, 7L));
        System.out.println("El resultado es: "+DivConResta(16L, 4L));
    }

    public static void DivConResta(int n, int x){
        int i;
        int div = n;
        for (i = 0; n >= x; i++){
            n -= x;
        }
        System.out.println(div+" divido "+x+" es " +i+" y el resto es "+n);
    }

    public static int DivConResta(long n, long x) {
        if (n < x) {
            return 0;
        } else {
            return 1 + DivConResta(n - x, x);
        }
    }
}


