import java.util.ArrayList;

public class Main {
    public static final String WHITESPACE_REGEX = "\\s+";

    public static void main(String[] args) {
        ConsoleInterface ci = new ConsoleInterface();
        FileReader fileReader = new FileReader("documents");
        ArrayList<String> filenames = fileReader.getFilenames();
        InvertedIndex<String, String> ii = new InvertedIndex<String, String>();

        ci.print("wait for preprocessing...");

        for (String filename : filenames) {
            String fullText = fileReader.getFullText(filename);
            fullText = StringUtils.normalize(fullText);
            String[] words = StringUtils.split(fullText, new String[] { WHITESPACE_REGEX, ":", "-" });
            String documentName = StringUtils.removeAfter(filename, '.');
            ii.addDocument(documentName, words);
        }

        while (true) {
            String word = ci.getWord().toLowerCase();
            ArrayList<String> results = ii.searchDocuments(word);
            ci.showResultBooks(results);
        }
    }
}
