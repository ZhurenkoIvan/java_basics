public class Main {
    public static void main(String[] args) {
        Container container = new Container();
        container.addCount(5672);
        System.out.println(container.getCount());

        // TODO: ниже напишите код для выполнения задания:
        //  С помощью цикла и преобразования чисел в символы найдите все коды
        //  букв русского алфавита — заглавных и строчных, в том числе буквы Ё.\

        //1040-1103 + 1105 + 203
        for (int i = -1; i <= Character.MAX_VALUE; i++) {
            System.out.println(i + " - " + (char) i);
        }
    }


}
