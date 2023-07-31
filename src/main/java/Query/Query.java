package Query;

import InvertedIndex.InvertedIndex;
import lombok.Builder;

import java.util.ArrayList;
import java.util.HashSet;

@Builder
public class Query {
    private static final HashSet<String> EMPTY_RESULT = new HashSet<>();
    private ArrayList<String> must;
    private ArrayList<String> should;
    private ArrayList<String> mustNot;

    private HashSet<String> runMust(InvertedIndex ii) {
        HashSet<String> finalSet = new HashSet<>(ii.getAllDocuments());
        this.must.forEach(param -> finalSet.retainAll(ii.searchDocuments(param)));
        return finalSet;
    }

    private HashSet<String> runShould(InvertedIndex ii) {
        if (this.should.size() == 0)
            return new HashSet<>(ii.getAllDocuments());
        HashSet<String> finalSet = new HashSet<>();
        this.should.forEach(param -> finalSet.addAll(ii.searchDocuments(param)));
        return finalSet;
    }

    private HashSet<String> runMustNot(InvertedIndex ii) {
        HashSet<String> finalSet = new HashSet<>(ii.getAllDocuments());
        this.mustNot.forEach(param -> finalSet.removeAll(ii.searchDocuments(param)));
        return finalSet;
    }

    public HashSet<String> run(InvertedIndex ii) {
        HashSet<String> finalResult = new HashSet<>(ii.getAllDocuments());
        if (this.must != null) finalResult.retainAll(this.runMust(ii));
        if (this.should != null) finalResult.retainAll(this.runShould(ii));
        if (this.mustNot != null) finalResult.retainAll(this.runMustNot(ii));
        return finalResult;
    }
}
