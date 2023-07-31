package Tokenizer;

import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@Builder
public class EdgeNgramTokenizer implements Tokenizer {
    private int minGram;
    private int maxGram;

    public static List<String> getGrams(String data, int N) {
        if (N >= data.length())
            return List.of();

        List<String> result = new ArrayList<>();
        for (int i = 0; i <= data.length() - N; i++)
            result.add(data.substring(i, i + N));
        return result;
    }

    private List<String> getRawTokens(String str) {
        Tokenizer tokenizer = new RegexTokenizer("\\s+");
        return tokenizer.tokenize(str);
    }

    private void normalizeGrams() {
        if (this.minGram == 0) this.minGram = 1;
        if (this.maxGram == 0) this.maxGram = 1000;
    }

    public List<String> tokenize(String str) {
        List<String> rawTokens = getRawTokens(str);
        List<String> result = new ArrayList<>(rawTokens);
        normalizeGrams();
        for (int gram = this.minGram; gram <= this.maxGram; gram++) {
            int currentGram = gram;
            rawTokens.stream().map(token -> getGrams(token, currentGram)).forEach(result::addAll);
        }
        return result;
    }
}
