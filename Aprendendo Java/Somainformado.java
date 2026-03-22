import java.util.Scanner;

public class Somainformado {
    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int soma = 0;

        System.out.println("Digite um Numero: ");
        int n = scanner.nextInt();

        for (int i = 0;i <= n; i++){
            soma += i;

        }
        System.out.println("A Soma Total é:" + soma);
    }
}
