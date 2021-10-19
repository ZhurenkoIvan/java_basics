import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int cores = Runtime.getRuntime().availableProcessors();
        String srcFolder = "C:\\IdeaProjects\\java_basics\\13_FilesAndNetwork\\homework_13.4\\src\\Images";
        String dstFolder = "C:\\IdeaProjects\\java_basics\\15_Multithreading\\ImageResizer\\src\\main\\resources\\Images";

        File srcDir = new File(srcFolder);

        long start = System.currentTimeMillis();

        File[] files = srcDir.listFiles();
        int startFileIndex = 0;

        while ((files.length - startFileIndex - files.length / cores) >= 12) {
            File[] files1 = new File[files.length/cores];
            System.arraycopy(files, startFileIndex, files1, 0, files1.length);
            new ImageResizer(files1, dstFolder, start).start();
            startFileIndex += files.length / cores;
        }
        File[] files1 = new File[files.length - startFileIndex];
        System.arraycopy(files, startFileIndex, files1, 0, files1.length);
        new ImageResizer(files1, dstFolder, start).start();
        File dstDir = new File(dstFolder);
        File[] files2 = dstDir.listFiles();
        System.out.println(files2.length);
        System.out.println("Время работы программы без потоков = " + (System.currentTimeMillis() - start));
    }
}
