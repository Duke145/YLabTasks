package Module1;

import java.util.Random;
import java.util.Scanner;

public class Guess {
    public static void main(String[] args) throws Exception {
        int number = new Random().nextInt(100); // здесь загадывается число от 1 до 99
        int maxAttempts = 10; // здесь задается количество попыток
        System.out.println("Я загадал число. У тебя " + maxAttempts + " попыток угадать.");
        int attemp = 0;
        int tryNum;
        Scanner scanner = new Scanner(System.in);
        while (attemp < maxAttempts) {
            tryNum = scanner.nextInt();
            attemp++;
            if (tryNum > number) {
                System.out.println("Мое число меньше! У тебя осталось " + (maxAttempts - attemp) + " попыток");
            } else if (tryNum < number) {
                System.out.println("Мое число больше! У тебя осталось " + (maxAttempts - attemp) + " попыток");
            } else {
                System.out.println("Ты угадал с " + attemp + " попытки");
                break;
            }
        }
        if (attemp == maxAttempts) System.out.println("Ты не угадал");
    }
}
