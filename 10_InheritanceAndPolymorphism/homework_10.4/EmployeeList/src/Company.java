import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Company {
    private int companyIncome;
    private ArrayList<Employee> employeeList = new ArrayList<>();

    Company(int companyIncome) {
        this.companyIncome = companyIncome;
    }

    public void hire(Employee employee) {
        employeeList.add(employee);
    }

    public void hireAll(ArrayList<Employee> arrayList) {
        for (Employee employee : arrayList){
            employeeList.add(employee);
        }
    }

    public void fire(Employee employee) {
        employeeList.remove(employee);
    }
    public void fireSeveralEmployees(int count) {
        if (count <= employeeList.size() && count > 0) {
            int employeeRemaining = employeeList.size();
            int index;
            for (int i = 0; i < count; i++) {
                index = (int) (Math.random() * employeeRemaining);
                employeeList.remove(index);
                employeeRemaining--;
            }
        }
    }

    public int getCompanyIncome() {
        return companyIncome;
    }

    public ArrayList<Employee> getLowestSalaryStaff(int listSize) {
        if (listSize <= employeeList.size() && listSize > 0) {
            Collections.sort(employeeList, new Comparator<Employee>() {
                @Override
                public int compare(Employee o1, Employee o2) {
                    if (o1.getMonthSalary() > o2.getMonthSalary()) {
                        return 1;
                    }
                    if (o1.getMonthSalary() == o2.getMonthSalary()) {
                        return 0;
                    } else {
                        return -1;
                    }
                }
            });
            ArrayList<Employee> finalList = new ArrayList<>();
            for (int i = 0; i < listSize; i++) {
                finalList.add(employeeList.get(i));
            }
            return finalList;
        }
        return null;
    }

    public ArrayList<Employee> getTopSalaryStaff(int listSize) {
        if (listSize <= employeeList.size() && listSize > 0) {
            Collections.sort(employeeList, new Comparator<Employee>() {
                @Override
                public int compare(Employee o1, Employee o2) {
                    if (o1.getMonthSalary() > o2.getMonthSalary()) {
                        return -1;
                    }
                    if (o1.getMonthSalary() == o2.getMonthSalary()) {
                        return 0;
                    } else {
                        return 1;
                    }
                }
            });
            ArrayList<Employee> finalList = new ArrayList<>();
            for (int i = 0; i < listSize; i++) {
                finalList.add(employeeList.get(i));
            }
            return finalList;
        }
        return null;
    }

    public int size() {
        return employeeList.size();
    }

    public boolean containEmployee(Employee employee) {
        return employeeList.contains(employee);
    }

    public void fireHalfOfOperators() {
        ArrayList<Operator> operators = getOperators();
        for (int i = 0; i < operators.size() / 2; i++) {
            int index = (int) (Math.random() * operators.size());
            employeeList.remove(operators.get(index));
        }
    }

    public ArrayList<Operator> getOperators () {
        ArrayList<Operator> operators = new ArrayList<Operator>();
        for (Employee employee : employeeList) {
            if (employee instanceof Operator) operators.add((Operator) employee);
        }
        return operators;
    }
}
