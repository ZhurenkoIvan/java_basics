import org.checkerframework.checker.units.qual.A;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Movements {
    public static final String DIGIT_REGEX = "[\"][0-9,]+[\"]";
    private List<Transaction> allTransactions = new ArrayList<>();

    public Movements(String pathMovementsCsv) {
        try {
            List<String> lines = Files.readAllLines(Path.of(pathMovementsCsv));
            lines.remove(0);
            for (String line : lines) {
                Pattern pattern = Pattern.compile(DIGIT_REGEX);
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    int start = matcher.start();
                    int end = matcher.end();
                    String substring = line.substring(start, end);
                    substring = substring.replaceAll("[\"]", "");
                    substring = substring.replaceAll("[,]", ".");
                    line = line.replaceAll(DIGIT_REGEX, substring);
                    String[] transactionInfo = line.split(",");
                    allTransactions.add(new Transaction(transactionInfo));
                }else{
                    String[] transactionInfo = line.split(",");
                    allTransactions.add(new Transaction(transactionInfo));
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public double getExpenseSum() {
        double expenseSum = 0.0;
        for (Transaction transaction : allTransactions) {
            expenseSum += transaction.getExpenses();
        }

        return expenseSum;
    }

    public double getIncomeSum() {
        double incomeSum = 0.0;
        for (Transaction transaction : allTransactions) {
            incomeSum += transaction.getIncome();
        }
        return incomeSum;
    }

    public HashMap<String, Double> getOrganizationExpense () {
        HashMap<String, Double> organizationExpense = new HashMap<>();
        for (Transaction transaction : allTransactions) {
            if (organizationExpense.containsKey(transaction.getOrganization())) {
                organizationExpense.put(transaction.getOrganization(), organizationExpense.get(transaction.getOrganization()) + transaction.getExpenses());
            } else {
                organizationExpense.put(transaction.getOrganization(),transaction.getExpenses());
            }
        }
        return organizationExpense;
    }
}
