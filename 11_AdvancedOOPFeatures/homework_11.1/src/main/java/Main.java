import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static final String STAFF_TXT = "C:\\ДЗ Java\\11_AdvancedOOPFeatures\\homework_11.1\\data\\staff.txt";

    public static void main(String[] args) {
        List<Employee> staff = Employee.loadStaffFromFile(STAFF_TXT);
        System.out.println(staff);
        sortBySalaryAndAlphabet(staff);
        System.out.println(staff);
    }

    public static void sortBySalaryAndAlphabet(List<Employee> staff) {
//        Comparator<Employee> comparator = new EmpolyeeComparatorBySalary().thenComparing(new EmployeeComparatorByName());
        Collections.sort(staff, (o1, o2) -> {
            int i = o1.getSalary().compareTo(o2.getSalary());
            if (i == 0) {
                i = o1.getName().compareTo(o2.getName());
                return i;
            } else return i;
        });

        //TODO Метод должен отсортировать сотрудников по заработной плате и алфавиту.
    }
}