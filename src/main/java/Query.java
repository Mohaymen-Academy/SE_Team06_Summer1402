import java.util.ArrayList;
import java.util.Arrays;

public abstract class Query {
    private InvertedIndex ii;
    protected ArrayList<String> params;

    public Query(InvertedIndex _ii) {
        ii = _ii;
        params = new ArrayList<String>();
    }

    public ArrayList<String> getDocuments(String token) {
//        return ii.searchDocuments(token);
        return new ArrayList<String>(Arrays.asList("hasan"));
    }

    public void clearParams() {
        params.clear();
    }

    public void setParams(ArrayList<String> newParams) {
        for (String newParam : newParams)
            if (!params.contains(newParam))
                params.add(newParam);
    }

    public abstract ArrayList<String> get();
}

class AndQuery extends Query {

    public AndQuery(InvertedIndex ii) {
        super(ii);
    }

    public ArrayList<String> get() {
        Merger<String> andMerger = new AndMerger<String>();
        for (String param : params)
            andMerger.add(getDocuments(param));
        return andMerger.merge();
    }
}

class OrQuery extends Query {
    public OrQuery(InvertedIndex ii) {
        super(ii);
    }

    public ArrayList<String> get() {
        Merger<String> orMerger = new OrMerger<String>();
        for (String param : params)
            orMerger.add(getDocuments(param));
        return orMerger.merge();
    }
}