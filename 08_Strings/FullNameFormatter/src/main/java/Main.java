import java.util.Scanner;

public class Main {
  private static String template = "Фамилия: %s%nИмя: %s%nОтчество: %s%n";

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    while (true) {
      int check = 0;
      String input = scanner.nextLine();
      if (input.isEmpty() || input.contains("  ")) {
        System.out.println("Введенная строка не является ФИО");
        return;
      }
      for (int i = 0; i < input.length(); i++) {
        int letter = input.charAt(i);
        if (letter == 32) {
          check++;
        }
        if (letter >= 1040 && letter <= 1103) {continue;
        }else if (letter == 1025 || letter == 1105 || letter == 45 || letter == 32) { continue;
        } else {
          System.out.println("Введенная строка не является ФИО");
          return;
        }
      }
      if (check != 2) {
        System.out.println("Введенная строка не является ФИО");
        return;
      }
      int secondNameEnd = input.indexOf(' ');
      int secondnameStart = 0;
      String secondName = input.substring(secondnameStart, secondNameEnd);

      int firstNameEnd = input.lastIndexOf(' ');
      int firstNameStart = secondNameEnd + 1;
      String firstName = input.substring(firstNameStart, firstNameEnd);

      int surNameEnd = input.length();
      int surNameStart = firstNameEnd + 1;
      String surName = input.substring(surNameStart, surNameEnd);

      System.out.println(String.format(template, secondName, firstName, surName));
      break;
    }
  }

}