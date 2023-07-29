package Query;

import InvertedIndex.InvertedIndex;

import java.util.HashSet;

public class NotQuery extends Query {
    public NotQuery(InvertedIndex ii) {
        super(ii);
    }

    public HashSet<String> get() {
        HashSet<String> finalSet = new HashSet<>(this.ii.getAllDocuments());
        this.params.stream().forEach(param -> finalSet.removeAll(this.ii.searchDocuments(param)));
        return finalSet;
    }
}
