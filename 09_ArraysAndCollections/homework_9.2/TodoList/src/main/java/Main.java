import java.util.Scanner;

public class Main {
    public static final String NUMBER_REGEX = "[0-9]+";

    private static boolean checkIfHasNumber (String text) {
        boolean check = false;
        String[] array = text.split(" ");
        for (String string : array) {
            if (string.matches(NUMBER_REGEX)){
                check = true;
            }
        }
        return check;
    }


    public static void main(String[] args) {
        TodoList todoList = new TodoList();
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            int firstSpace = input.indexOf(" ");
            if (input.equals("LIST")){
                for (int i = 0; i < todoList.size(); i++) {
                    System.out.println(i + " - " + todoList.getTodoListAtIndex(i));
                }
                continue;
            }
            if (!input.contains(" ")) {
                continue;
            }
            //Если содержит в себе индекс
            if (checkIfHasNumber(input) && !input.substring(0, firstSpace).equals("DELETE")) {
                int secondSpace = input.indexOf(" ", firstSpace + 1);
                int number = Integer.parseInt(input.substring(firstSpace + 1, secondSpace));
                String todo = input.substring(secondSpace + 1);
                //Добавление с индексом
                if (input.substring(0, firstSpace).equals("ADD")) {
                    todoList.add(number, todo);
                    System.out.println("Дело \"" + todo + "\" добавлено");
                }
                //Редактирование существующего
                if (input.substring(0, firstSpace).equals("EDIT")) {
                    System.out.println("Дело \"" + todoList.getTodoListAtIndex(number) + "\" заменено на дело \"" + todo + "\"");
                    todoList.edit(todo, number);

                }
            }
                //Удаление существующего
            if (input.substring(0, firstSpace).equals("DELETE")) {
                int number = Integer.parseInt(input.substring(firstSpace + 1));
                if (number < todoList.size()) {
                    System.out.println("Дело \"" + todoList.getTodoListAtIndex(number) + "\" удалено");
                    todoList.delete(number);
                } else {
                    System.out.println("Дело с таким номером не существует");
                }

            }

            //Если не содержит в себе индекса
            if (input.substring(0,firstSpace).equals("ADD") && !checkIfHasNumber(input)) {
                String todo = input.substring(firstSpace + 1);
                todoList.add(todo);
                System.out.println("Дело \"" + todo + "\" добавлено");
            }


        }
        // TODO: написать консольное приложение для работы со списком дел todoList
    }
}
