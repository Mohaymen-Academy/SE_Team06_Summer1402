package Query;


import InvertedIndex.InvertedIndex;

import java.util.HashSet;
import java.util.Set;

public class AndQuery extends Query {

    public AndQuery(InvertedIndex ii) {
        super(ii);
    }

    public HashSet<String> get() {
        if (this.params.size() == 0)
            return this.EMPTY_RESULT;
        HashSet<String> finalSet = new HashSet<>(this.ii.searchDocuments(this.params.iterator().next()));
        this.params.stream().forEach((param) -> finalSet.retainAll(this.ii.searchDocuments(param)));
        return finalSet;
    }
}
