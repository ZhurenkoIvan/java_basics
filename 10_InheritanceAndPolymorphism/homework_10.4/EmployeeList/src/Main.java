import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Company company = new Company(Generator.generateCompanyIncome());
        company.hireAll(Generator.generateListOfEmployee(180,80,10, company.getCompanyIncome()));
        company.hire(new Manager());
        company.hire(new TopManager(company.getCompanyIncome()));
        System.out.println("Список по возрастанию: ");
        getCompanyLowestSalaryStaff(company, 30);
        System.out.println("Список по убыванию: ");
        getCompanyTopSalaryStaff(company, 15);
        System.out.println("-------------------------------------------------------------");
        company.fireSeveralEmployees(company.size()/2);
        System.out.println("Список по возрастанию: ");
        getCompanyLowestSalaryStaff(company, 30);
        System.out.println("Список по убыванию: ");
        getCompanyTopSalaryStaff(company, 15);



    }

    private static void getCompanyTopSalaryStaff(Company company, int count) {
        ArrayList<Employee> employeeArrayList = company.getTopSalaryStaff(count);
        for (Employee employee :
                employeeArrayList) {
            System.out.println(employee.getMonthSalary() + " руб");
        }

    }

    private static void getCompanyLowestSalaryStaff(Company company, int count) {
        ArrayList<Employee> employeeArrayList = company.getLowestSalaryStaff(count);
        for (Employee employee :
                employeeArrayList) {
            System.out.println(employee.getMonthSalary() + " руб");
        }

    }
}
