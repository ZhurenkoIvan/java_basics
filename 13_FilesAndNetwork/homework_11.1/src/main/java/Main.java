import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String path = scanner.nextLine();
        try {
            Long folderSize = FileUtils.calculateFolderSize(path);
            double intermediateValue = folderSize;
            if (folderSize < 1024) System.out.println("Размер папки " + path + "Составляет" + folderSize + "байт");
            if (folderSize < 1_048_576 && folderSize >= 1024) {
                folderSize = (long) (intermediateValue / 1024.0 * 10);
                intermediateValue = folderSize / 10.0;
                System.out.println("Размер папки " + path + " Составляет " + intermediateValue + " Кб");
            }
            if (folderSize < 1_073_741_824L && folderSize >= 1_048_576) {
                folderSize = (long) (intermediateValue / 1_048_576.0 * 10);
                intermediateValue = folderSize / 10.0;
                System.out.println("Размер папки " + path + " Составляет " + intermediateValue + " Мб");
            }
            if (folderSize < 1_099_511_627_776L && folderSize >= 1_073_741_824L) {
                folderSize = (long) (intermediateValue / 1_073_741_824.0 * 10);
                intermediateValue = folderSize / 10.0;
                System.out.println("Размер папки " + path + " Составляет " + intermediateValue + " Гб");
            }
        }
        catch (IllegalArgumentException ex) {
            ex.printStackTrace();
            System.out.println("Неправильный формат ввода.\nВведите: \"Диск:/папка/папка/.../файл.расширение");
        }
    }
}
