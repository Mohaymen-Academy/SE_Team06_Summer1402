import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReader {
    private String sourcePath;

    public FileReader(String _sourcePath) {
        sourcePath = _sourcePath;
    }

    public ArrayList<String> getFilenames() {
        ArrayList<String> filenames = new ArrayList<String>();
        File file = new File(sourcePath);
        File[] files = file.listFiles();
        for (File currentFile : files)
            if (currentFile.isFile())
                filenames.add(currentFile.getName());
        return filenames;
    }

    private String getPath(String filename) {
        return (sourcePath + "/" + filename);
    }

    public String getFullText(String filename) {
        String fullText = "";
        try {
            String filePath = getPath(filename);
            File file = new File(filePath);
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine())
                fullText += sc.nextLine() + "\n";
            sc.close();
        } catch (FileNotFoundException e) {
            System.err.println("File Not Found!");
        }
        return fullText;
    }
}