import java.util.ArrayList;

public class Main {
    public static final String WHITESPACE_REGEX = "\\s+";
    private static UserInterface console = new Console();
    private static InvertedIndex ii;

    private static void initializeInvertedIndex(String sourcePath) {
        FileReader fileReader = new FileReader(sourcePath);
        ArrayList<String> filenames = fileReader.getFilenames();
        for (String filename : filenames)
            ii.addDocument(fileReader.getFullText(filename), StringUtils.removeAfter(filename, '.'));
    }

    private static Filter initializeFilter(Filter filter, ArrayList<String> filterOptions) {
        for (String filterOption : filterOptions)
            filter.addData(ii.searchDocuments(filterOption));
        return filter;
    }

    private static ArrayList<String> applyFilters(ArrayList<String> initialResult, Filter[] filters) {
        ArrayList<String> finalResult = new ArrayList<String>(initialResult);
        for (Filter filter : filters)
            finalResult = filter.filter(finalResult);
        return finalResult;
    }

    private static void ShowSearchResult(ArrayList<String> searchResult) {
        if (searchResult.size() != 0)
            console.printOrderedArray(searchResult);
        else
            console.println("<-No Result->");
    }

    public static void main(String[] args) {
        UserInterface console = new Console();
        console.println("Wait for the preprocessing...");
        ii = new InvertedIndex(new RegexTokenizer(WHITESPACE_REGEX + "|:|-"), new TestNormalizer());
        initializeInvertedIndex("./documents");

        Tokenizer wsTokenizer = new RegexTokenizer(WHITESPACE_REGEX);
        while (true) {
            console.print("Enter Search Keys (Ctrl+C to exit, +OR, -NOT): ");
            Options options = new Options(wsTokenizer.tokenize(console.getLine()));
            ArrayList<String> andOptions, orOptions, notOptions;

            orOptions = options.pop('+');
            Filter orFilter = initializeFilter(new OrFilter(), orOptions);

            notOptions = options.pop('-');
            Filter notFilter = initializeFilter(new NotFilter(), notOptions);

            andOptions = options.getRemained();
            Filter andFilter = initializeFilter(new AndFilter(ii.searchDocuments(andOptions.get(0))), andOptions);

            ArrayList<String> searchResult = applyFilters(ii.searchDocuments(andOptions.get(0)),
                    new Filter[] { andFilter, orFilter, notFilter });

            ShowSearchResult(searchResult);
        }
    }
}
