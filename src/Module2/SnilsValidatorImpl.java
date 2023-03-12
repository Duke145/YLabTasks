package Module2;

public class SnilsValidatorImpl implements SnilsValidator {
    @Override
    public boolean validate(String snils) {
        try {
            if (snils.length() != 11) {
                return false;
            } else {
                int[] tempMassive = new int[9];
                char[] number = snils.toCharArray();
                int controlSum = 0;
                for (int i = 0; i < 11; i++) {
                    if (Character.isDigit(number[i])) {
                        if (i < 9) {
                            tempMassive[i] = Character.digit(number[i], 10) * (9 - i);
                            controlSum += tempMassive[i];
                        }
                    } else {
                        return false;
                    }
                }
                if (controlSum > 100) {
                    controlSum = controlSum % 101;
                }
                if (controlSum == 100) {
                    controlSum = 0;
                }
                return Integer.parseInt(String.valueOf(number[9]) + String.valueOf(number[10])) == controlSum;
            }
        } catch (NullPointerException e) {
            System.out.println("Null pointer exception!");
            return false;
        }
    }
}
