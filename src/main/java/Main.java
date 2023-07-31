import InvertedIndex.InvertedIndex;
import Normalizer.Normalizer;
import Normalizer.WholeTextNormalizer;
import Query.Query;
import Tokenizer.EdgeNgramTokenizer;
import Tokenizer.Tokenizer;
import UserInterface.Console;
import UserInterface.UserInterface;

import java.util.List;

public class Main {
    private InvertedIndex ii;
    private final FileReader fileReader = new FileReader("documents/");
    private final UserInterface console = new Console();
    private static final List<String> REMOVE_MARKS = List.of(",", ".", "!", "-", "\"", "_", "?");

    public static String removeTxtExtension(String filename) {
        String result = filename;
        if (filename.endsWith(".txt")) result = filename.substring(0, filename.length() - 4);
        return result;
    }

    public void initInvertedIndex() {
        int MAX_GRAM = 10;
        int MIN_GRAM = 2;
        Tokenizer edgeNgramTokenizer = EdgeNgramTokenizer.builder().minGram(MIN_GRAM).maxGram(MAX_GRAM).build();
        Normalizer normalizer = WholeTextNormalizer.builder().removeMarks(Main.REMOVE_MARKS).toLowerCase(true).build();
        this.ii = new InvertedIndex(edgeNgramTokenizer);
        this.ii.setNormalizer(normalizer);
    }

    public void setInvertedIndexData() {
        this.fileReader.getFilenames().forEach(filename -> this.ii.addDocument(Main.removeTxtExtension(filename), fileReader.getFullText(filename)));
    }

    public void listenQueries(String instruction) {
        ArgumentParser ap = new ArgumentParser();
        while (true) {
            this.console.print(instruction + ": ");
            String QueryArguments = this.console.getLine();
            ap.setData(QueryArguments);
            Query query = Query.builder().should(ap.parse("+")).mustNot(ap.parse("-")).must(ap.parse()).build();
            if (query.run(this.ii).size() == 0)
                console.println("<-No Result->");
            else
                console.printUnOrderedList(query.run(this.ii));
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.initInvertedIndex();
        main.setInvertedIndexData();
        main.listenQueries("Query (and +or -not)");
    }
}
