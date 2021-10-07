import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static final String DIGIT_REGEX = "[\"][0-9,]+[\"]";
    public static void main(String[] args) throws ParseException {
        String line = "Текущий счёт,40817813206170024534,RUR,27.03.17,CRD_5F46FY,548673++++++1028    10502864\\RUS\\PUSHKINO\\105\\ZOOMAGAZIN 4            27.03.17 24.03.17      2176.50  RUR (Apple Pay-7666) MCC5995,0,\"2176,5\"";
        Pattern pattern = Pattern.compile(DIGIT_REGEX);
        Matcher matcher = pattern.matcher(line);
        String wfwe = "\"2176,5\"";
        if (wfwe.matches(DIGIT_REGEX)) {
            System.out.println("ZHOPA");
        }
        if (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            String substring = line.substring(start, end);
            substring = substring.replaceAll("[\"]", "");
            substring = substring.replaceAll("[,]", ".");
            line = line.replaceAll(DIGIT_REGEX, substring);
            String[] lines = line.split(",");
            for (String infoLine : lines)
            System.out.println(infoLine);
        } else {
            String[] lines = line.split(",");
            for (String infoline : lines)
                System.out.println(infoline);
        }
    }
}
