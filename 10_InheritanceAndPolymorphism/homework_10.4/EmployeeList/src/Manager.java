public class Manager implements Employee{

    public static final int FIXED_PART = 70000;
    private int percentOfIncome;

    Manager() {
        int percentOfIncome = (int)((Math.random() * 25000.0 + 115000.0) * 0.05);
        this.percentOfIncome = percentOfIncome;
    }

    @Override
    public int getMonthSalary() {
        return FIXED_PART + percentOfIncome;
    }
}
