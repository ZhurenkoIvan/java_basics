import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    public static String folderSizeFormatter(Long folderSize, String path) {
        double intermediateValue = folderSize;
        if (folderSize < 1024) System.out.println("Размер папки " + path + "Составляет" + folderSize + "байт");
        if (folderSize < 1_048_576 && folderSize >= 1024) {
            folderSize = (long) (intermediateValue / 1024.0 * 10);
            intermediateValue = folderSize / 10.0;
            return  "Размер папки " + path + " Составляет " + intermediateValue + " Кб";
        }
        if (folderSize < 1_073_741_824L && folderSize >= 1_048_576) {
            folderSize = (long) (intermediateValue / 1_048_576.0 * 10);
            intermediateValue = folderSize / 10.0;
            return "Размер папки " + path + " Составляет " + intermediateValue + " Мб";
        }
        else  {
            folderSize = (long) (intermediateValue / 1_073_741_824.0 * 10);
            intermediateValue = folderSize / 10.0;
            return "Размер папки " + path + " Составляет " + intermediateValue + " Гб";
        }

    }



    public static long calculateFolderSize(String path) {
        Long folderSize = 0l;
        File firstFile = new File(path);
        if (firstFile.isFile()) {
            return firstFile.length();
        }
        List<File> allFolders = new ArrayList<>();
        allFolders.add(firstFile);
        List<File> allFiles = new ArrayList<>();

        while (!allFolders.isEmpty()) {
            File folder = allFolders.get(0);
                if (folder.isDirectory()){
                    List<String> absolutePaths = getAllAbsolutePathsInFolder(folder);
                    for(String string : absolutePaths) {
                        File file = new File(string);
                        if (file.isFile()) allFiles.add(file);
                        else allFolders.add(file);
                    }
                }
                allFolders.remove(folder);
        }
        for (File file : allFiles) {
            folderSize = folderSize + file.length();
        }

        return folderSize;
    }

    private static List<String> getAllAbsolutePathsInFolder (File folder) {
        File[] files = folder.listFiles();
        List <String> absolutePaths = new ArrayList<>();
        if (files == null) {
            List<String> emptyList = new ArrayList<>();
            return emptyList;
        }
        for (File file: files) {
            absolutePaths.add(file.getAbsolutePath());
        }
        return absolutePaths;
    }
}
