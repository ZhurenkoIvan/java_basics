import java.util.Scanner;

public class Main {
    /* TODO:
        Пример вывода списка Email, после ввода команды LIST в консоль:
        test@test.com
        hello@mail.ru
        - каждый адрес с новой строки
        - список должен быть отсортирован по алфавиту
        - email в разных регистрах считается одинаковыми
           hello@skillbox.ru == HeLLO@SKILLbox.RU
        - вывод на печать должен быть в нижнем регистре
           hello@skillbox.ru
        Пример вывода сообщения об ошибке при неверном формате Email:
        "Неверный формат email"
    */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EmailList emailList = new EmailList();
        
        while (true) {
            String input = scanner.nextLine();
            String[] intermediateArray = input.split(" ");
            if (intermediateArray[0].equals("ADD")) {
                emailList.add(intermediateArray[1]);
            } else if (input.equals("LIST")) {
                for (String email : emailList.getSortedEmails()) {
                    System.out.println(email);
                }
            }
            else {
                System.out.println(EmailList.WRONG_EMAIL);
                continue;
            }
        }
    }
}
