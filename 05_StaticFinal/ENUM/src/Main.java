public class Main {
    public static void main(String[] args) {
        ArithmeticCalculator arithmeticCalculator = new ArithmeticCalculator(10, 20);
        System.out.println(arithmeticCalculator.calculate(Operation.ADD));
        System.out.println(arithmeticCalculator.calculate(Operation.MULTIPLY));
        System.out.println(arithmeticCalculator.calculate(Operation.SUBTRACT));
    }
}
