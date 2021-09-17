import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

  private static final String REGEX = "[0-9]+";

  public static void main(String[] args) {

  }

  public static int calculateSalarySum(String text){
    int sum = 0;
    Pattern pattern = Pattern.compile(Main.REGEX);
    Matcher matcher = pattern.matcher(text);
    while (matcher.find()) {
      int start = matcher.start();
      int end = matcher.end();
      sum += Integer.parseInt(text.substring(start, end));
    }
    return sum;
  }

}