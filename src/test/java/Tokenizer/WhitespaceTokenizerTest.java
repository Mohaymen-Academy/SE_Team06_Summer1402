package Tokenizer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class WhitespaceTokenizerTest {
    Tokenizer tokenizer;

    @BeforeEach
    public void setup() {
        this.tokenizer = new RegexTokenizer("\\s+");
    }

    @Test
    public void tokenize_whenInputIsWhitespace_thenShouldOutputEmpty() {
        List<String> actualTokens = this.tokenizer.tokenize(" \n \t ");
        Assertions.assertEquals(Arrays.asList(), actualTokens);
    }

    @Test
    public void tokenize_whenInputHasMoreWhitespace_thenOutputShouldIgnoreWhitespace() {
        List<String> actualTokens = this.tokenizer.tokenize("  First  Second   Third    Fourth    ");
        Assertions.assertEquals(Arrays.asList("First", "Second", "Third", "Fourth"), actualTokens);
    }
}
