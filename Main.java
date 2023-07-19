import java.util.ArrayList;
import java.util.Arrays;

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

        Tokenizer wsTokenizer = new RegexTokenizer(WHITESPACE_REGEX);
        while (true) {
            console.print("Enter Search Keys (Ctrl+C to exit, +OR, -NOT): ");
            Options options = new Options(wsTokenizer.tokenize(console.getLine()));
            ArrayList<String> andOptions, orOptions, notOptions;

            // orOptions = options.pop('+');
            // Filter orFilter = new OrFilter();
            // for (String orOption : orOptions)
            //     orFilter.addData(ii.searchDocuments(orOption));

            // notOptions = options.pop('-');
            // Filter notFilter = new NotFilter();
            // for (String notOption : notOptions)
            //     notFilter.addData(ii.searchDocuments(notOption));

            andOptions = options.getRemained();
            Filter andFilter = new AndFilter(ii.searchDocuments(andOptions.get(0)));
            for (String andOption : andOptions)
                andFilter.addData(ii.searchDocuments(andOption));

            ArrayList<String> searchResult = ii.searchDocuments(andOptions.get(0));

            // for (Filter filter : new Filter[] {andFilter, orFilter, notFilter})
            //     searchResult = filter.filter(searchResult);

            searchResult = andFilter.filter(searchResult);
            if (searchResult.size() != 0)
                console.printOrderedArray(searchResult);
            else
                console.println("<-No Result->");
        }
    }
}
