import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args){

        Movements movements = new Movements("src/test/resources/movementList.csv");
        System.out.println("Сумма доходов за период: " + movements.getIncomeSum() + " руб.");
        System.out.println("Сумма расходов за период: " + movements.getExpenseSum() + " руб." + System.lineSeparator());


        HashMap<String, Double> organizationExpense = movements.getOrganizationExpense();
        System.out.println("Сумма расходов по организациям:");
        for (String organization : organizationExpense.keySet()) {
            System.out.println(organization + " - " + organizationExpense.get(organization) + " руб.");
        }
    }
}
