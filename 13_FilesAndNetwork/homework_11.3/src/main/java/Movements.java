import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.SplittableRandom;
import java.util.logging.FileHandler;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Movements {
    public static final String DIGIT_REGEX = "[\"][0-9,]+[\"]";
    private static List<Transaction> allTransactions = new ArrayList<>();

    public Movements(String pathMovementsCsv) {
        try {
            List<String> lines = Files.readAllLines(Path.of(pathMovementsCsv));
            for (String line : lines) {
                Pattern pattern = Pattern.compile(DIGIT_REGEX);
                Matcher matcher = pattern.matcher(line);
                int start = matcher.start();
                int end = matcher.end();
                String substring = line.substring(start,end);
                substring.replaceAll("[\",]","");
                line.replaceAll(DIGIT_REGEX, substring);
                String[] fields = line.split(",");
                allTransactions.add(new Transaction(fields));

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public double getExpenseSum() {
        return 0.0;
    }

    public double getIncomeSum() {
        return 0.0;
    }

    public static String removeByIndex(String str, int startIndex, int endIndex) {
        String newString = new StringBuilder(str).deleteCharAt(endIndex).toString();
        return new StringBuilder(newString).deleteCharAt(startIndex).toString();
    }

}
