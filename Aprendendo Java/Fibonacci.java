import java.util.Scanner;

public class Fibonacci {
    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Informe o Numero Desejado:");
        int Fibo =scanner.nextInt();
        int num0 = 0;
        int num1 = 1;
         for ( int i = 0; i <= Fibo; i++){
             int resultado = num0 + num1;
             System.out.println(num0 + "+" + num1 + "=" + resultado);
             num0 = num1;
             num1 = resultado;


         }
    }
}
