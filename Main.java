import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ConsoleInterface ci = new ConsoleInterface();
        FileReader fileReader = new FileReader("documents");
        ArrayList<String> filenames = fileReader.getFilenames();
        InvertedIndex ii = new InvertedIndex();

        ci.print("wait for preprocessing...");

        for (String filename : filenames) {
            String fullText = fileReader.getFullText(filename);
            StringUtils su = new StringUtils(fullText);
            su.normalize();
            String[] words = su.split(new String[] { ":", "-" });
            su.setString(filename);
            su.removeAfter('.');
            ii.addDocument(su.getString(), words);
        }

        while (true){
            String word = ci.getWord().toLowerCase();
            ArrayList<String> results = ii.searchDocuments(word);
            ci.showResultBooks(results);
        }
    }
}
