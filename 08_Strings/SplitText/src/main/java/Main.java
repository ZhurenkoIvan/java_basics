import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
  private static String regex = "[A-Za-z’]+";

  public static void main(String[] args) {

  }

  public static String splitTextIntoWords(String text) {
    String splitedText = "";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(text);
    while (matcher.find()) {
      int start = matcher.start();
      int end = matcher.end();
      if (end == text.length() - 1) {
        splitedText = splitedText.concat(text.substring(start, end));
      }else {
        splitedText = splitedText.concat(text.substring(start, end).concat(System.lineSeparator()));
      }

    }
    return splitedText;
  }

}