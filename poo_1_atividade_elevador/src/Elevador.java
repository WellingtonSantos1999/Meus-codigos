import java.util.Scanner;

public class Elevador {
    public void ElevadorON(){
        Scanner scanner = new Scanner(System.in);
        boolean aberta, fechada ;
        int andar=1;
        aberta = false;
        fechada = false;
        boolean i = false;
        while(i == false){
            aberta= true;
            System.out.println((aberta ? "A Porta Abriu" : "Defeito"));
            System.out.println("Andar Atual:" + andar);
            System.out.println("1-Andar");
            System.out.println("2-Andar");
            System.out.println("3-Andar");

            System.out.println("Selecione o Andar que deseja:");
            int escolha = scanner.nextInt();

            if(escolha== 1 && escolha != andar){
                System.out.println((fechada ? "Defeito" : "A Porta se Fecha"));
                andar = escolha;
                System.out.println(">---------------------------------------<");
            }

            else if(escolha== 2 && andar !=2){
                System.out.println((fechada ? "Defeito" : "A Porta se Fecha"));
                andar = escolha;
                System.out.println(">---------------------------------------<");
            }

            else if(escolha== 3 && andar !=3){
                System.out.println((fechada ? "Defeito" : "A Porta se Fecha"));
                andar = escolha;
                System.out.println(">---------------------------------------<");
            }
            else {
                System.out.println(">-----------------------------------------------------------<");
                System.out.println("!!!Digite o Numero de andar Valido e que não seja o atual!!! ");
                System.out.println(">-----------------------------------------------------------<");
            }


        }
    }
}
