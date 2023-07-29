package Query;

import InvertedIndex.InvertedIndex;

import java.util.HashSet;

public class OrQuery extends Query {
    public OrQuery(InvertedIndex ii) {
        super(ii);
    }

    public HashSet<String> get() {
        if (this.params.size() == 0)
            return this.EMPTY_RESULT;
        HashSet<String> finalSet = new HashSet<>();
        this.params.stream().forEach(param -> finalSet.addAll(this.ii.searchDocuments(param)));
        return finalSet;
    }
}
