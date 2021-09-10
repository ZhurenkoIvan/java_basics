public class Printer {

    private static String queue = "";
    private static int allPagesInQueue = 0;
    private static int allPrintedPages = 0;

    public void append(String text) {
        append(text, "Имя документа", 1);
    }

    public void append(String text, String name) {
        append(text, name, 1);
    }

    public void append(String text, String name, int numberOfPages) {
        queue = queue + "Текст документа: " + text + ". Название документа: " + name + ". Количество страниц: " + numberOfPages + ".\n";
        allPagesInQueue = allPagesInQueue + numberOfPages;
        allPrintedPages = allPrintedPages + numberOfPages;
    }

    public void clear() {
        queue = "";
        allPagesInQueue = 0;
    }

    public void print() {
        System.out.println("Очередь печати: ");
        System.out.println(queue);
        clear();
    }

    public int getPendingPagesCount () {
        return allPagesInQueue;
    }

    public int getAllPrintedPagesCount () {
        System.out.print("Всего распечатано страниц: ");
        return allPrintedPages;
    }

}
