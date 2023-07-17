import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReader {
    private static final String[] EMPTY = {};
    private String sourcePath;

    public FileReader(String _sourcePath) {
        sourcePath = _sourcePath;
    }

    static String[] split(String s) {
        s = s.replaceAll("^\\s+", "").replaceAll("\\s+$", "");
        if (s.isEmpty())
            return EMPTY;
        return s.split("\\s+|:|-");
    }

    static ArrayList<String> getWords(String filename) {
        ArrayList<String> words = new ArrayList<String>();
        try {
            File file = new File(filename);
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] lineWords = split(line);
                for (String word : lineWords)
                    words.add(word);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.err.println("File Not Found!");
        }

        return words;
    }

    private ArrayList<String> getFilenames() {
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

    public static void main(String[] args) {
        FileReader sample = new FileReader("documents");
        ArrayList<String> words = getWords("documents/Clean Agile.txt");
        for (String word : words)
            System.out.print(word + "$");
    }
}