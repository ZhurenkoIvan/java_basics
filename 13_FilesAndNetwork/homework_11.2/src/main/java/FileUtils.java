import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileUtils {
    public static void copyFolder(String sourceDirectory, String destinationDirectory) {
        List<File> allDirectories = new ArrayList<>();
        List<File> allFiles = new ArrayList<>();

        File sourceFile = new File(sourceDirectory);

        allDirectories.add(sourceFile);
        while (!allDirectories.isEmpty()) {
            File file = allDirectories.get(0);
            File[] files = file.listFiles();
            for (File fileInDirectory : files) {
                if (fileInDirectory.isFile()){
                    allFiles.add(fileInDirectory);
                    createFileInDestinationPath(fileInDirectory, sourceDirectory, destinationDirectory);
                } else {
                    allDirectories.add(fileInDirectory);
                    createDirectoryInDestinationPath(fileInDirectory, sourceDirectory, destinationDirectory);
                }
            }
            allDirectories.remove(file);
        }
        copyFiles(allFiles, sourceDirectory, destinationDirectory);
    }


    private static void createDirectoryInDestinationPath (File file, String sourceDirectory,String destinationDirectory) {
        String filePath = file.getAbsolutePath();
        int end = sourceDirectory.length();
        String destinationFilePath = destinationDirectory + filePath.substring(end);
        File destinationFile = new File(destinationFilePath);
        destinationFile.mkdir();
    }

    private static void createFileInDestinationPath (File file, String sourceDirectory,String destinationDirectory) {
        String filePath = file.getAbsolutePath();
        int end = sourceDirectory.length();
        String destinationFilePath = destinationDirectory + filePath.substring(end);
        File destinationFile = new File(destinationFilePath);
        try {
            destinationFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void copyFiles (List<File> fileList, String sourceDirectory, String destinationDirectory) {
        for (File fileSource : fileList) {
            String sourceFilePath = fileSource.getAbsolutePath();
            int end = sourceDirectory.length();
            String destinationFilePath = destinationDirectory + sourceFilePath.substring(end);
            File fileDest = new File(destinationFilePath);
            InputStream is = null;
            OutputStream os = null;
            try {
                is = new FileInputStream(fileSource);
                os = new FileOutputStream(fileDest);
                byte[] buffer = new byte[1024];
                int length;
                while ((length = is.read(buffer)) > 0) {
                    os.write(buffer, 0, length);
                }
                is.close();
                os.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
