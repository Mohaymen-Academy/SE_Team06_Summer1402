import lombok.RequiredArgsConstructor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

@RequiredArgsConstructor
public class FileReader {
    private final String sourcePath;

    private String getPath(String filename) {
        String path;
        if (sourcePath.endsWith("/"))
            path = sourcePath + filename;
        else
            path = sourcePath + "/" + filename;
        return path;
    }

    public List<String> getFilenames() {
        ArrayList<String> filenames = new ArrayList<>();
        File file = new File(sourcePath);
        return Arrays.stream(Objects.requireNonNull(file.listFiles())).map(File::getName).toList();
    }

    public String getFullText(String filename) {
        StringBuilder fullText = new StringBuilder();
        try {
            String filePath = getPath(filename);
            File file = new File(filePath);
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) fullText.append(sc.nextLine()).append("\n");
            sc.close();
        } catch (FileNotFoundException e) {
            System.err.println("File Not Found!");
        }
        return fullText.toString();
    }
}