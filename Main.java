import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        FileReader fileReader = new FileReader("documents");
        ArrayList<String> filenames = fileReader.getFilenames();
        InvertedIndex ii = new InvertedIndex();

        for (String filename : filenames) {
            String fullText = fileReader.getFullText(filename);
            StringUtils su = new StringUtils(fullText);
            su.normalize();
            String[] words = su.split(new String[] { ":", "-" });
            su.setString(filename);
            su.removeAfter('.');
            ii.addDocument(su.getString(), words);
        }

        ArrayList<String> books = ii.searchDocuments("SuMmaRy".toLowerCase());
        for (int i = 0; i < books.size(); i++)
            System.out.println(books.get(i));
    }
}
