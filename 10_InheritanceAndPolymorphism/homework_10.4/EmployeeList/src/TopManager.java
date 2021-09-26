public class TopManager implements Employee {

    public static final int FIXED_PART = 100000;
    private int percentOfIncome;

    TopManager(int companyIncome) {
        if(companyIncome >= 10000000) {
            percentOfIncome = (int) (FIXED_PART * 1.5);
        } else {
            percentOfIncome = 0;
        }
    }

    @Override
    public int getMonthSalary() {
        return FIXED_PART + percentOfIncome;
    }
}
