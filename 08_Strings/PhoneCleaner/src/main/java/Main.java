import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

  private static String regex = "[0-9]";

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    while (true) {
      String input = scanner.nextLine();
      String output = "";
      Pattern pattern = Pattern.compile(regex);
      Matcher matcher = pattern.matcher(input);
      while (matcher.find()) {
        int start = matcher.start();
        int end = matcher.end();
        output += input.substring(start, end);
      }


      if (output.length() == 11) {
        if(output.charAt(0) == '7') {
          System.out.println(output);
        }
        if (output.charAt(0) == '8') {
          System.out.println(7 + output.substring(1,11));
        } else {
          System.out.println("Неверный формат номера");
        }
      } else if (output.length() == 10) {
        System.out.println(7 + output);
      } else {
        System.out.println("Неверный формат номера");
      }
      //TODO:напишите ваш код тут, результат вывести в консоль.
    }
  }

}
