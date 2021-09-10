public class Main {

    public static void main(String[] args) {

        Arithmetic arithmetic = new Arithmetic(25, 30);
        Arithmetic arithmetic1 = new Arithmetic(10, 20);
        System.out.print("Первое число: " + arithmetic.getFirstNumber() + ". ");
        System.out.println("Второе число: " + arithmetic.getSecondNumber());
        System.out.println("Сумма чисел: " + arithmetic.sum());
        System.out.println("Произведение чисел: " + arithmetic.multi());
        System.out.println("Минимальное из чисел: " + arithmetic.min());
        System.out.println("Максимальное из чисел: " + arithmetic.max());
        System.out.println();
        System.out.print("Первое число: " + arithmetic1.getFirstNumber() + ". ");
        System.out.println("Второе число: " + arithmetic1.getSecondNumber());

    }
}
