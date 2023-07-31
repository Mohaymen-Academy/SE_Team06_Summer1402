package Tokenizer;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Arrays;

@RequiredArgsConstructor
public class RegexTokenizer implements Tokenizer {
    private final String regex;

    public List<String> tokenize(String str) {
        if (str.isBlank()) return EMPTY;
        return Arrays.asList(str.strip().split(this.regex));
    }
}
