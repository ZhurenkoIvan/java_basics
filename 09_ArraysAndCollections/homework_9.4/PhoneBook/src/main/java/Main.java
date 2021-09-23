import java.util.Scanner;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PhoneBook phoneBook = new PhoneBook();
        while (true) {
            System.out.println("Введите номер, имя или команду:");
            String input = scanner.nextLine();
            //Если это имя
            if (isItName(input)) {
                //Если этого имени нет в списке, то нужно ввести номер для этого имени
                if (!phoneBook.isContainName(input)) {
                    System.out.println("Такого имени в телефонной книге нет."
                            + System.lineSeparator() + "Введите номер абонента \"" + input + "\":");
                    String inputPhone = scanner.nextLine();
                    //Проверяется соответствие номера и добавляется контакт
                    phoneCheck(phoneBook, input, inputPhone);

                    //Если имя уже в списке, то выдается его номер
                } else {
                    System.out.println("Такой контакт уже существует. Его номер:");
                    printPhoneByName(phoneBook, input);
                }
                //Если это номер
            } else if (isItPhone(input)) {
                //Если номера нет в телефонной книге, то нужно ввести имя и создать запись в телефонной книге
                if (!phoneBook.isContainPhone(input)) {
                    System.out.println("Такого номера в телефонной книге нет."
                            + System.lineSeparator() + "Введите имя абонента для номера \"" + input + "\":");
                    String inputName = scanner.nextLine();
                    //Проверка правильности ввода имени
                    nameCheck(phoneBook, input, inputName);
                    // Если номер есть в телефонной книге, то нужно перезаписать владельца
                } else {
                    System.out.println("Этот номер уже есть в телефонной книге."
                            + System.lineSeparator() + "Перезапишите его владельца:");
                    String inputName = scanner.nextLine();
                    phoneBook.addContact(input, inputName);
                    System.out.println("Контакт перезаписан!");
                }
            } else if (input.matches("LIST")) {
                printAllContacts(phoneBook);
            } else {
                System.out.println("Неверный формат ввода, начинаем сначала.");
            }
        }
    }


    private static void printAllContacts(PhoneBook phoneBook) {
        TreeSet<String> setPhoneBook = (TreeSet<String>) phoneBook.getAllContacts();
        for (String abonent : setPhoneBook) {
            System.out.println(abonent);
        }
    }

    private static void nameCheck (PhoneBook phoneBook, String input, String inputName) {
        if (inputName.matches(phoneBook.REGEX_NAME)) {
            phoneBook.addContact(input, inputName);
            System.out.println("Контакт сохранен!");
        } else {
            System.out.println("Неверный формат ввода, начинаем сначала.");
        }

    }

    private static void phoneCheck (PhoneBook phoneBook, String input, String inputPhone) {
        if (inputPhone.matches(phoneBook.REGEX_PHONE)) {
            phoneBook.addContact(inputPhone, input);
            System.out.println("Контакт сохранен!");
        } else {
            System.out.println("Неверный формат ввода, начинаем сначала.");
        }

    }

    private static boolean isItPhone(String input) {
        return input.matches(PhoneBook.REGEX_PHONE);
    }

    private static boolean isItName(String input) {
        return input.matches(PhoneBook.REGEX_NAME);
    }

    private static void printPhoneByName (PhoneBook phoneBook, String input) {
        for (String phone : phoneBook.getPhonesByName(input)) {
            String[] intermediateArray = phone.split(" ");
            System.out.println(intermediateArray[2]);
        }
    }
}


