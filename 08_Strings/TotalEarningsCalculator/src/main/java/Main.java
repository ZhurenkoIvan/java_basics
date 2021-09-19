import java.io.IOException;

public class Main {

  public static void main(String[] args) {

    String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";

    //TODO: напишите ваш код, результат вывести в консоль
    int startIndex = text.indexOf(" ");
    int endIndex = 0;
    int finalIndex = text.lastIndexOf(" ");
    int sum = 0;

    while (endIndex <= finalIndex) {
      if (endIndex < finalIndex) {
        endIndex = text.indexOf(" ", startIndex + 1);
        String word = text.substring(startIndex + 1, endIndex);
        startIndex = endIndex;
        if (word.charAt(0) >= '0' && word.charAt(0) <= '9') {
          sum += Integer.parseInt(word);
        }
      } else {
        String word = text.substring(endIndex + 1, text.length());
        if (word.charAt(0) >= '0' && word.charAt(0) <= '9') {
          sum += Integer.parseInt(word);
        }
        endIndex++;
      }
    }
    System.out.println(sum);
  }
}