import java.util.*;
import java.util.stream.Stream;

public class Main {

    private static final String STAFF_TXT = "data/staff.txt";

    public static void main(String[] args) {
        List<Employee> staff = Employee.loadStaffFromFile(STAFF_TXT);
        Employee employeeMaxSalary = findEmployeeWithHighestSalary(staff, 2017);
        System.out.println(employeeMaxSalary);
    }

    public static Employee findEmployeeWithHighestSalary(List<Employee> staff, int year) {
        Date afterDate = new Date(year - 1900, Calendar.JANUARY, 1);
        Date beforeDate = new Date(year - 1899, Calendar.JANUARY, 1);
        Optional<Employee> max = staff.stream()
                .filter(employee -> employee.getWorkStart().after(afterDate) && employee.getWorkStart().before(beforeDate))
                .max(Comparator.comparing(Employee::getSalary));
        return max.orElse(null);
        //TODO Метод должен вернуть сотрудника с максимальной зарплатой среди тех,
        // кто пришёл в году, указанном в переменной year
    }
}