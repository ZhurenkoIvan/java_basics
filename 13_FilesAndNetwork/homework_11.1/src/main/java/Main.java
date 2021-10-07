import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String path = scanner.nextLine();
        try {
            System.out.println(FileUtils.folderSizeFormatter(FileUtils.calculateFolderSize(path), path));
        }
        catch (IllegalArgumentException ex) {
            ex.printStackTrace();
            System.out.println("Неправильный формат ввода.\nВведите: \"Диск:/папка/папка/.../файл.расширение");
        }
    }
}
