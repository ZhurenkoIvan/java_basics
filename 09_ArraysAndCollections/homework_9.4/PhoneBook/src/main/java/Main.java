import java.util.Scanner;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PhoneBook phoneBook = new PhoneBook();
        while (true){
            System.out.println("Введите номер, имя или команду:");
            String input = scanner.nextLine();

            //Если это имя
            if (input.matches(phoneBook.REGEX_NAME)) {
                //Если этого имени нет в списке, то нужно ввести номер для этого имени
                if (!phoneBook.isContainName(input)) {
                    System.out.println("Такого имени в телефонной книге нет."
                    + System.lineSeparator() + "Введите номер абонента \"" + input + "\":");
                    String inputPhone = scanner.nextLine();
                    //Проверяется соответствие номера
                    if (inputPhone.matches(phoneBook.REGEX_PHONE)) {
                        phoneBook.addContact(inputPhone, input);
                        System.out.println("Контакт сохранен!");
                    } else {
                        System.out.println("Неверный формат ввода, начинаем сначала.");
                    }
                //Если имя уже в списке, то выдается его номер
                } else {
                    System.out.println("Такой контакт уже существует. Его номер:");
                    for (String phone: phoneBook.getPhonesByName(input)) {
                        String[] intermediateArray = phone.split(" ");
                        System.out.println(intermediateArray[2]);
                    }
                }
            //Если это номер
            } else if (input.matches(phoneBook.REGEX_PHONE)) {
                //Если номера нет в телефонной книге, то нужно ввести имя и создать запись в телефонной книге
                if (!phoneBook.isContainPhone(input)) {
                    System.out.println("Такого номера в телефонной книге нет."
                            + System.lineSeparator() + "Введите имя абонента для номера \"" + input + "\":");
                    String inputName = scanner.nextLine();
                    //Проверка правильности ввода имени
                    if (inputName.matches(phoneBook.REGEX_NAME)) {
                        phoneBook.addContact(input, inputName);
                        System.out.println("Контакт сохранен!");
                    } else {
                        System.out.println("Неверный формат ввода, начинаем сначала.");
                    }
                // Если номер есть в телефонной книге, то нужно перезаписать владельца
                } else {
                    System.out.println("Этот номер уже есть в телефонной книге."
                            + System.lineSeparator() + "Перезапишите его владельца:");
                    String inputName = scanner.nextLine();
                    phoneBook.addContact(input, inputName);
                    System.out.println("Контакт перезаписан!");
                }
            } else if (input.matches("LIST")) {
                TreeSet<String> setPhoneBook = (TreeSet<String>) phoneBook.getAllContacts();
                for (String abonent: setPhoneBook) {
                    System.out.println(abonent);
                }

            }else {
                System.out.println("Неверный формат ввода, начинаем сначала.");
            }

        }
    }
}
