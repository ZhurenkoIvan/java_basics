public class Books {

    private final String name;
    private final String author;
    private final int pagesCount;
    private final int numberISBN;

    public Books(String name, String author, int pagesCount, int numberISBN) {
        this.author = author;
        this.name = name;
        this.pagesCount = pagesCount;
        this.numberISBN = numberISBN;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public int getPagesCount() {
        return pagesCount;
    }

    public int getNumberISBN() {
        return numberISBN;
    }
}
