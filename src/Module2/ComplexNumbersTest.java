package Module2;

public class ComplexNumbersTest {
    public static void main(String[] args) {
        ComplexNumbers firstImpl = new ComplexNumbers(2);
        ComplexNumbers secondImpl = new ComplexNumbers(2, -3);
        ComplexNumbers thirdImpl = new ComplexNumbers(8, 6);
        //проверка первого конструктора
        System.out.println("проверка первого конструктора");
        System.out.println(firstImpl);
        System.out.println();
        //проверка второго конструктора
        System.out.println("проверка второго конструктора");
        System.out.println(secondImpl);
        System.out.println();
        //проверка сложения
        System.out.println("проверка сложения");
        System.out.println(firstImpl.add(secondImpl));
        System.out.println(secondImpl.add(firstImpl));
        System.out.println();
        //проверка вычитания
        System.out.println("проверка вычитания");
        System.out.println(firstImpl.sub(secondImpl));
        System.out.println(secondImpl.sub(firstImpl));
        System.out.println();
        //проверка умножения
        System.out.println("проверка умножения");
        System.out.println(firstImpl.mul(secondImpl));
        System.out.println(secondImpl.mul(firstImpl));
        System.out.println(secondImpl.mul(thirdImpl));
        System.out.println();
        //проверка деления
        System.out.println("проверка деления");
        System.out.println(firstImpl.div(secondImpl));
        System.out.println(secondImpl.div(firstImpl));
        System.out.println(secondImpl.div(thirdImpl));
        System.out.println();
        //проверка неизменности первоначальных объектов
        System.out.println("проверка неизменности первоначальных объектов");
        System.out.println(firstImpl);
        System.out.println(secondImpl);
        System.out.println(thirdImpl);
        System.out.println();
        //проверка вычисления модуля
        System.out.println("проверка вычисления модуля");
        System.out.printf("%.3f%n", firstImpl.mod());
        System.out.printf("%.3f%n", secondImpl.mod());
        System.out.printf("%.3f%n", thirdImpl.mod());
        System.out.println();
        //проверка деления на ноль
        System.out.println("проверка деления на ноль");
        System.out.println(secondImpl.div(new ComplexNumbers(0)));
    }
}
