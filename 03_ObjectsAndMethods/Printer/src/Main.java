public class Main {

    public static void main(String[] args) {

        Printer printer = new Printer();
        printer.append("Голова винтом", "Трек", 3);
        printer.append("Вася ест суп");
        printer.append("Петя пустил слезу", "История жизни", 10);
        printer.print();
        printer.append("Витя ушел в школу", "1 сентября", 7);
        printer.print();
        System.out.println(printer.getAllPrintedPagesCount());


    }
}
