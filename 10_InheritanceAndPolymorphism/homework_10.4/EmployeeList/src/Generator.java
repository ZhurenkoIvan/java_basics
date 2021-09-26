import java.util.ArrayList;

public class Generator {

    public static int generateCompanyIncome() {
        int companyIncome = (int) (Math.random() * 5000000.0) + 8000000;
        return companyIncome;
    }

    public static Operator generateOperator(int count) {
        return new Operator();
    }

    public static Manager generateManager(int count) {
            return new Manager();

    }

    public static TopManager generateManager(int count, int companyIncome) {
            return new TopManager(companyIncome);
    }

    public static ArrayList<Employee> generateListOfEmployee(int operatorsCount, int managersCount, int topManagersCount, int companyIncome) {
        ArrayList<Employee> employeeArrayList = new ArrayList<>();
        for (int i = 0; i < operatorsCount; i++) {
            employeeArrayList.add(new Operator());
        }
        for (int i = 0; i < managersCount; i++) {
            employeeArrayList.add(new Manager());
        }
        for (int i = 0; i < topManagersCount; i++) {
            employeeArrayList.add(new TopManager(companyIncome));
        }
        return employeeArrayList;

    }
}
