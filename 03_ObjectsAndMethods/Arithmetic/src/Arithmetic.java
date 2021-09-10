public class Arithmetic {

    private double firstNumber = 0;
    private double secondNumber = 0;

    public Arithmetic(double firstNumber, double secondNumber){
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
    }

    public double sum () {
        return firstNumber + secondNumber;
    }

    public double multi() {
        return firstNumber * secondNumber;
    }

    public  double min() {
        return firstNumber <= secondNumber ? firstNumber : secondNumber;
    }

    public double max() {
        return firstNumber >= secondNumber ? firstNumber : secondNumber;
    }

    public double getFirstNumber() {
        return firstNumber;
    }

    public double getSecondNumber() {
        return secondNumber;
    }
}
