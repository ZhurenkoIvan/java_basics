public class ArithmeticCalculator {

    private static int firstNumber;
    private static int secondNumber;

    public ArithmeticCalculator (int firstNumber, int secondNumber) {
        ArithmeticCalculator.firstNumber = firstNumber;
        ArithmeticCalculator.secondNumber = secondNumber;
    }

    public double calculate (Operation operation) {
        if (operation == Operation.ADD) {
            return firstNumber + secondNumber;
        } else if (operation == Operation.SUBTRACT) {
            return firstNumber - secondNumber;
        } else {
            return firstNumber * secondNumber;
        }
    }
}
