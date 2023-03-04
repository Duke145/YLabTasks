package Module1;

import java.util.Scanner;

public class Stars {
    public static void main(String[] args) throws Exception {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            String template = scanner.next();
            if (n > 0 && m > 0) {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        System.out.print((j == (m - 1)) ? template : template + " ");
                    }
                    System.out.println();
                }
            } else System.out.println("Некорректно введены данные");
        }
    }
}
