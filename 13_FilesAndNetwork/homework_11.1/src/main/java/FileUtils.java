import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {



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
