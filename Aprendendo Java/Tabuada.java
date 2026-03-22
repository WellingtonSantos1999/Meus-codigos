import java.util.Scanner;

public class Tabuada {
    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escolha o Numero da Tabuada:");
        int tabuada = scanner.nextInt();
        for(int i = 0; i <= 10; i++){
            int resultado= i * tabuada;
            System.out.println( i +"x" + tabuada + "=" + resultado);
        }


    }
}
