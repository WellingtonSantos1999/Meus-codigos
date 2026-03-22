import java.util.Scanner;

public class menos1 {
    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int certo = -1;
        int i = 0;
        while(i != certo){
            System.out.println("Digite um Numero:");
            i = scanner.nextInt();

            if ( i > certo){
                System.out.println("Um pouco Menor!");
            }
            else{
                System.out.println("Um pouco Maior!");
            }
        }
        System.out.println("Voce acertou o Numero!");
    }
}
