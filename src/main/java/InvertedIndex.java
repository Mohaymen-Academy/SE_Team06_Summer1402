import Normalizer.Normalizer;
import Tokenizer.Tokenizer;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.*;

@RequiredArgsConstructor
@Setter
public class InvertedIndex {
    private static final Set<String> EMPTY_RESULT = new HashSet<>();
    private final @NonNull Tokenizer tokenizer;
    private Normalizer normalizer;
    private Map<String, Set<String>> tokenMap = new HashMap<String, Set<String>>();


    public String normalize(String data) {
        if (this.normalizer != null)
            return this.normalizer.normalize(data);
        return data;
    }

    public void addDocument(String name, String data) {
        String normalizedDocument = normalize(data);
        List<String> tokens = this.tokenizer.tokenize(normalizedDocument);
        for (String token : tokens)
            if (this.tokenMap.containsKey(token)) {
                tokenMap.get(token).add(name);
            } else {
                Set<String> newTokenSet = new HashSet<>();
                newTokenSet.add(name);
                tokenMap.put(token, newTokenSet);
            }
    }

    public Set<String> searchDocuments(String token) {
        String normalizedToken = this.normalizer.normalize(token);
        if (this.tokenMap.containsKey(normalizedToken))
            return tokenMap.get(normalizedToken);
        return EMPTY_RESULT;
    }

}