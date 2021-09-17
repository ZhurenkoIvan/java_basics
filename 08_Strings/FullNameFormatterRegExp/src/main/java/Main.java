import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
  private static String regexInput = "[А-яёЁ-]+";
  private static String template = "Фамилия: %s%nИмя: %s%nОтчество: %s%n";

  public static void main(String[] args) {

    String secondName = "";
    String firstName = "";
    String surName = "";
    int nameCount = 1;


    Scanner scanner = new Scanner(System.in);
    while (true) {
      String input = scanner.nextLine();
      if (input.isEmpty() || getSpaceCount(input) != 2) {
        System.out.println("Введенная строка не является ФИО");
        return;
      }
      Pattern pattern = Pattern.compile(regexInput);
      Matcher matcher = pattern.matcher(input);
      while (matcher.find()) {
        int start = matcher.start();
        int end = matcher.end();
        if (nameCount == 1) {
          secondName = input.substring(start, end);
        }
        if (nameCount == 2) {
          firstName = input.substring(start, end);
        }
        if (nameCount == 3) {
          surName = input.substring(start, end);
        }
        nameCount++;
      }
      if (firstName.isEmpty() || secondName.isEmpty() || surName.isEmpty()) {
        System.out.println("Введенная строка не является ФИО");
        return;
      }
      System.out.println(String.format(template, secondName, firstName, surName));
      return;

      //TODO:напишите ваш код тут, результат вывести в консоль.
      //При невалидном ФИО вывести в консоль: Введенная строка не является ФИО
    }

  }

  private static int getSpaceCount(String text) {
    int spaceCount = 0;
    for (int i = 0; i < text.length() - 1; i++) {
      if (text.charAt(i) == ' ') {
        spaceCount++;
      }
    }
    return spaceCount;
  }

}