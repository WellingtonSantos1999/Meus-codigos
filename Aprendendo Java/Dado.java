import java.util.Scanner;
import java . util . Random ;

public class Dado {
    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random ();
        int x = random . nextInt (10) + 1;
        int escolha = 0;
        int tentativas = 5;

        while(escolha != x && tentativas <= 5 ){
            System.out.println("Escolha um Numero: ");
            escolha = scanner.nextInt();
            if(escolha < x){
                System.out.println("Um pouco Maior!");
            }
            if(escolha > x){
                System.out.println("Um pouco Menor!");
            }
        }

        System.out.println("Voce acertou!!! O Numero Era:" + x );
    }
}
