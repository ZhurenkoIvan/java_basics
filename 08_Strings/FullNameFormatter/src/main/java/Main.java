import java.util.Scanner;

public class Main {
  private static String template = "Фамилия: %s%nИмя: %s%nОтчество: %s%n";

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    while (true) {
      int check = 0;
      String input = scanner.nextLine();
      if (isStringAllowed(input)) {
        System.out.println("Введенная строка не является ФИО");
        return;
      }
      for (int i = 0; i < input.length(); i++) {
        int letter = input.charAt(i);
        check = spaceCheck(letter, check);
        if (isSymbolAllowed(letter)) {continue;
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

  private static int spaceCheck(int letter, int check) {
    if (letter == ' ') {
      check ++;
    }
    return check;

  }

  private static boolean isSymbolAllowed ( int letter) {
    return ((letter >= 'А' && letter <= 'я') || letter == 'ё' || letter == 'Ё' || letter == '-' || letter == ' ');
  }

  private static boolean isStringAllowed (String input) {
    return input.isEmpty() || input.contains("  ");
  }

}