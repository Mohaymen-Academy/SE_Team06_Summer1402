import java.util.ArrayList;

public class Main {
    public static final String WHITESPACE_REGEX = "\\s+";
    private static UserInterface console = new Console();
    private static InvertedIndex ii;
    private static Merger<String> andMerger = new AndMerger<String>();
    private static Query andQuery;
    private static Query orQuery;
    private static Filter notFilter = new NotFilter();

    private static void initInvertedIndex(String sourcePath) {
        ii = new InvertedIndex(new RegexTokenizer(WHITESPACE_REGEX + "|:|-"), new DefaultNormalizer());
        FileReader fileReader = new FileReader(sourcePath);
        ArrayList<String> filenames = fileReader.getFilenames();
        for (String filename : filenames)
            ii.addDocument(fileReader.getFullText(filename), StringUtils.removeAfter(filename, '.'));
    }

    private static void ShowSearchResult(ArrayList<String> searchResult) {
        if (searchResult.size() != 0)
            console.printOrderedArray(searchResult);
        else
            console.println("<-No Result->");
    }

    private static void initQueries() {
        andQuery = new AndQuery(ii);
        orQuery = new OrQuery(ii);
    }

    private static void createQueries(ArrayList<String> andParams, ArrayList<String> orParams) {
        if (andParams.size() != 0) {
            andQuery.setParams(andParams);
            andMerger.add(andQuery.get());
        }

        if (orParams.size() != 0) {
            orQuery.setParams(orParams);
            andMerger.add(orQuery.get());
        }
    }

    private static void createFilter(ArrayList<String> notParams) {
        for (String notParam : notParams)
            notFilter.addData(ii.searchDocuments(notParam));
    }

    private static ArrayList<String> mergeQueries() {
        return andMerger.merge();
    }

    private static ArrayList<String> filterQueries(ArrayList<String> queryResults) {
        return notFilter.filter(queryResults);
    }

    private static void clearSearchData() {
        andQuery.clearParams();
        orQuery.clearParams();
        andMerger.clearData();
        notFilter.clearData();
    }

    public static void main(String[] args) {
        console.println("Wait for the preprocessing...");
        initInvertedIndex("./documents");

        Tokenizer wsTokenizer = new RegexTokenizer(WHITESPACE_REGEX);
        initQueries();
        while (true) {
            console.print("Enter Search Keys (Ctrl+C to exit, +OR, -NOT): ");
            Options options = new Options(wsTokenizer.tokenize(console.getLine()));
            ArrayList<String> andParams, orParams, notParams;
            orParams = options.pop('+');
            notParams = options.pop('-');
            andParams = options.getRemained();
            createQueries(andParams, orParams);
            createFilter(notParams);

            ArrayList<String> searchResult = filterQueries(mergeQueries());

            ShowSearchResult(searchResult);
            clearSearchData();
        }
    }
}
