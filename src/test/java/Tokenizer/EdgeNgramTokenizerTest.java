package Tokenizer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

public class EdgeNgramTokenizerTest {
    private final String data = "sample one";

    @Test
    public void whenMinIsNotSet_thenShouldStartWithOne() {
        Tokenizer tokenizer = EdgeNgramTokenizer.builder().maxGram(2).build();
        Set<String> actual = new HashSet<>(tokenizer.tokenize(this.data));
        Assertions.assertEquals(Set.of("s", "a", "m", "p", "l", "sa", "am", "mp", "pl", "le", "o", "n", "e", "on", "ne", "sample", "one"), actual);
    }

    @Test
    public void whenMaxIsNotSet_thenShouldMaxGramWithLength() {
        Tokenizer tokenizer = EdgeNgramTokenizer.builder().minGram(3).build();
        Set<String> actual = new HashSet<>(tokenizer.tokenize(this.data));
        Assertions.assertEquals(Set.of("sam", "amp", "mpl", "ple", "samp", "ampl", "mple", "sampl", "ample", "sample", "one"), actual);
    }

    @Test
    public void whenMinMaxSet_thenShouldFollowMinMax() {
        Tokenizer tokenizer = EdgeNgramTokenizer.builder().minGram(2).maxGram(3).build();
        Set<String> actual = new HashSet<>(tokenizer.tokenize(this.data));
        Assertions.assertEquals(Set.of("sam", "amp", "mpl", "ple", "sa", "am", "mp", "pl", "le", "on", "ne", "sample", "one"), actual);
    }
}
