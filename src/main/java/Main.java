import InvertedIndex.InvertedIndex;
import Query.Query;
import Tokenizer.Tokenizer;
import Tokenizer.RegexTokenizer;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Tokenizer tokenizer = new RegexTokenizer("\\s+");
        InvertedIndex ii = new InvertedIndex(tokenizer);
        ii.addDocument("book1", "this is data");
        ii.addDocument("book2", "this was Data");

        Query query;
    }
}
