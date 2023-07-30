package InvertedIndex;

import Normalizer.Normalizer;
import Tokenizer.Tokenizer;
import lombok.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Setter
public class InvertedIndex {
    private static final HashSet<String> EMPTY_RESULT = new HashSet<>();
    private final @NonNull Tokenizer tokenizer;
    private Normalizer normalizer;
    @Setter(AccessLevel.NONE)
    private Map<String, HashSet<String>> tokenMap = new HashMap<>();
    private @Getter HashSet<String> allDocuments = new HashSet<>();

    private String normalize(String data) {
        if (this.normalizer != null)
            return this.normalizer.normalize(data);
        return data;
    }

    public void addDocument(String name, String data) {
        this.allDocuments.add(name);
        String normalizedDocument = this.normalize(data);
        List<String> tokens = this.tokenizer.tokenize(normalizedDocument);
        for (String token : tokens)
            if (this.tokenMap.containsKey(token)) {
                tokenMap.get(token).add(name);
            } else {
                HashSet<String> newTokenSet = new HashSet<>();
                newTokenSet.add(name);
                tokenMap.put(token, newTokenSet);
            }
    }

    public HashSet<String> searchDocuments(String token) {
        String normalizedToken = this.normalize(token);
        if (this.tokenMap.containsKey(normalizedToken))
            return tokenMap.get(normalizedToken);
        return EMPTY_RESULT;
    }

}