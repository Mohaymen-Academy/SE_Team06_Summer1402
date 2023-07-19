import java.util.ArrayList;
import java.util.HashMap;

public class InvertedIndex {
    private static final ArrayList<String> EMPTY_RESULT = new ArrayList<String>();
    private HashMap<String, ArrayList<String>> tokenMap;
    private Tokenizer _tokenizer;
    private Normalizer _normalizer;

    public InvertedIndex(Tokenizer tokenizer, Normalizer normalizer) {
        tokenMap = new HashMap<String, ArrayList<String>>();
        _tokenizer = tokenizer;
        _normalizer = normalizer;
    }

    public void addDocument(String document, String reference) {
        String normalizedDoc = _normalizer.normalize(document);
        ArrayList<String> tokens = _tokenizer.tokenize(normalizedDoc);
        for (String token : tokens)
            if (tokenMap.get(token) != null) {
                if (!tokenMap.get(token).contains(reference))
                    tokenMap.get(token).add(reference);
            } else {
                ArrayList<String> newTokenList = new ArrayList<String>();
                newTokenList.add(reference);
                tokenMap.put(token, newTokenList);
            }
    }

    public ArrayList<String> searchDocuments(String token) {
        String normalizedToken = _normalizer.normalize(token);
        if (tokenMap.get(normalizedToken) == null)
            return EMPTY_RESULT;

        return tokenMap.get(normalizedToken);
    }

}