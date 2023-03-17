package Module2;

public class SnilsValidatorTest {
    public static void main(String[] args) {
        System.out.println(new SnilsValidatorImpl().validate("01468870570")); //false
        System.out.println(new SnilsValidatorImpl().validate("90114404441")); //true
        System.out.println(new SnilsValidatorImpl().validate("91123")); //false
        System.out.println(new SnilsValidatorImpl().validate("901144044bv")); //false
        System.out.println(new SnilsValidatorImpl().validate("9011m404412")); //false
        System.out.println(new SnilsValidatorImpl().validate("9011440444111111")); //false
        System.out.println(new SnilsValidatorImpl().validate("24149680275")); //true
        System.out.println(new SnilsValidatorImpl().validate("54525342682")); //true
        System.out.println(new SnilsValidatorImpl().validate("10140268688")); //true
        System.out.println(new SnilsValidatorImpl().validate("")); //false
        System.out.println(new SnilsValidatorImpl().validate(null)); //false
        System.out.println(new SnilsValidatorImpl().validate("92000000300")); //true
        System.out.println(new SnilsValidatorImpl().validate("92000000301")); //false

    }
}
