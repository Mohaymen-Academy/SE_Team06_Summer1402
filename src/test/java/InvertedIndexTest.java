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
        Normalizer normalizer = WholeTextNormalizer.builder().removeMarks(Arrays.asList("'", "\"")).spaceMarks(Arrays.asList(",", ".", "!", "?", "-", "_")).toLowerCase(true).build();
        this.ii.setNormalizer(normalizer);
        this.ii.addDocument("book", "thi's_ boo\"k. ,,  i's abou\"t nothin'g! ?. , specia\"l!-special");
        this.ii.addDocument("book1", "tha't_ boo\"k. ,,  i's abou\"t nothin'g! ?. , specia\"l!-notspecial");
        Assertions.assertEquals(Set.of("book", "book1"), ii.searchDocuments("nothing"));
        Assertions.assertEquals(Set.of("book"), ii.searchDocuments("this"));
        Assertions.assertEquals(Set.of(), ii.searchDocuments("thisbook"));
    }
}
