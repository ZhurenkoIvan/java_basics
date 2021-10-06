import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите папку, которую необходимо скопировать: ");
        String sourceDirectory = scanner.nextLine();
        System.out.println("Введите конечную папку: ");
        String destinationDirectory = scanner.nextLine();
        try {
            FileUtils.copyFolder(sourceDirectory, destinationDirectory);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
