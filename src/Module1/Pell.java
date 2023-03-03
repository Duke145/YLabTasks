package Module1;

import java.util.Scanner;

public class Pell {
    public static void main(String[] args) throws Exception {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            int i=2;
            int P=0;
            int P1=0;
            int P2=1;
            if (n==0||n==1) P=n;
            else {
               while(i<=n) {
                   P = 2*P2+P1;
                   P1=P2;
                   P2=P;
                   i++;
               }
            }
            System.out.println(P);
        }
    }
}
