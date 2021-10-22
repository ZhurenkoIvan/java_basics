import java.io.File;

public class Main {

    public static void main(String[] args) {
        String srcFolder = "src/main/resources/Images2";
        String dstFolder = "src/main/resources/Images";

        File srcDir = new File(srcFolder);

        long start = System.currentTimeMillis();

        File[] files = srcDir.listFiles();
        int startFileIndex = 0;

        while ((files.length - startFileIndex - files.length / 10) >= 10) {
            File[] files1 = new File[files.length/10];
            System.arraycopy(files, startFileIndex, files1, 0, files1.length);
            new ImageResizer(files1, dstFolder, start).start();
            startFileIndex += files.length / 10;
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
