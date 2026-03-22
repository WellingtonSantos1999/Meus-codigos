import java.util.Scanner;

public class Nota {
    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Calculador de Média");
        System.out.println("Quantas Notas teve no Semestre?");
        int totnotas = scanner.nextInt();
        int soma = 0;
        for (int i =1;i <= totnotas; i++){
            System.out.println("Informe sua Nota:");
            int nota = scanner.nextInt();
            soma += nota;
        }
        int resultado=soma/totnotas;
        System.out.println("Suma Média é:" + resultado);
    }
}
