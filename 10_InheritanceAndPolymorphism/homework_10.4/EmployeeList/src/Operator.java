public class Operator implements Employee{

    public static final int FIXED_PART = 50000;

    @Override
    public int getMonthSalary() {
        return FIXED_PART;
    }
}

