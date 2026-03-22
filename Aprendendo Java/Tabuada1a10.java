public class Tabuada1a10 {
    static void main(String[] args) {
        int a = 0;
        for (int i = 0;a <= 10; i++){
            int resultado= i * a;

            System.out.println( i +"x" + a + "=" + resultado);
            if( i == 10){
                a++;
                i=0;
            }
        }

    }
}
