import InvertedIndex.InvertedIndex;
import Normalizer.Normalizer;
import Normalizer.WholeTextNormalizer;
import Tokenizer.RegexTokenizer;
import Tokenizer.Tokenizer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Set;

public class InvertedIndexTest {
    InvertedIndex ii;
    Tokenizer tokenizer;

    @BeforeEach
    public void setup() {
        this.tokenizer = new RegexTokenizer("\\s+");
        this.ii = new InvertedIndex(tokenizer);
    }

    @Test
    public void searchDocuments_whenNoNormalizer() {
        this.ii.addDocument("book", "this book   is about nothing   special!");
        this.ii.addDocument("book1", "this is another version of that Book!");
        Assertions.assertEquals(Set.of("book", "book1"), ii.searchDocuments("this"));
        Assertions.assertEquals(Set.of("book"), ii.searchDocuments("book"));
        Assertions.assertEquals(Set.of(), ii.searchDocuments("NotInTheText"));
    }

    @Test
    public void searchDocuments_whenHasNormalizer() {
        Normalizer normalizer = WholeTextNormalizer.builder().removeMarks(Arrays.asList("'",",", ".", "!", "?", "-", "_")).toLowerCase(true).build();
        this.ii.setNormalizer(normalizer);
        this.ii.addDocument("book", "this_ 'book'. ,,  i's about 'nothing'! ?. , special!-special");
        this.ii.addDocument("book1", "that_ 'book'. ,,  i's about 'nothing'! ?. , special!-notspecial");
        Assertions.assertEquals(Set.of("book", "book1"), ii.searchDocuments("nothing"));
        Assertions.assertEquals(Set.of("book"), ii.searchDocuments("this"));
        Assertions.assertEquals(Set.of(), ii.searchDocuments("thisbook"));
    }

    @Test
    public void whenGetAllDocuments() {
        this.ii.addDocument("book", "text");
        this.ii.addDocument("book1", "text");
        this.ii.addDocument("book2", "text");
        Assertions.assertEquals(Set.of("book", "book1", "book2"), this.ii.getAllDocuments());
    }
}
