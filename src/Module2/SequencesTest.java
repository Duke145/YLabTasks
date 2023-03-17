package Module2;

public class SequencesTest {
    public static void main(String[] args) {
        Sequences sequences = new SequencesImpl();
        int[] checkList = new int[]{-256, 0, 1, 2, 3, 4, 10, 20};

        // проверка метода a
        for (int i : checkList) {
            sequences.a(i);
        }

        // проверка метода b
        for (int i : checkList) {
            sequences.b(i);
        }

        // проверка метода c
        for (int i : checkList) {
            sequences.c(i);
        }

        // проверка метода d
        for (int i : checkList) {
            sequences.d(i);
        }

        // проверка метода e
        for (int i : checkList) {
            sequences.e(i);
        }

        // проверка метода f
        for (int i : checkList) {
            sequences.f(i);
        }

        // проверка метода g
        for (int i : checkList) {
            sequences.g(i);
        }

        // проверка метода h
        for (int i : checkList) {
            sequences.h(i);
        }

        // проверка метода i
        for (int i : checkList) {
            sequences.i(i);
        }

        // проверка метода j
        for (int i : checkList) {
            sequences.j(i);
        }
    }
}
