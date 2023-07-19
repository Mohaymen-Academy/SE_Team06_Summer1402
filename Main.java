import java.util.ArrayList;

public class Main {
    public static final String WHITESPACE_REGEX = "\\s+";

    public static void main(String[] args) {
        UserInterface console = new Console();
        console.println("Wait for the preprocessing...");
        InvertedIndex ii = new InvertedIndex(new RegexTokenizer(WHITESPACE_REGEX + "|:|-"), new TestNormalizer());

        FileReader fileReader = new FileReader("./documents");
        ArrayList<String> filenames = fileReader.getFilenames();
        for (String filename : filenames)
            ii.addDocument(fileReader.getFullText(filename), StringUtils.removeAfter(filename, '.'));

        while (true) {
            console.print("Enter Search Key (Ctrl+C to exit): ");
            String searchKey = console.getLine();
            ArrayList<String> searchResult = ii.searchDocuments(searchKey);
            if (searchResult.size() != 0)
                console.printOrderedArray(searchResult);
            else
                console.println("<-No Result->");
        }
    }
}
