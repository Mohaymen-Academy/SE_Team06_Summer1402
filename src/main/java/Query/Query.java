package Query;

import InvertedIndex.InvertedIndex;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Setter
public abstract class Query {
    protected static final HashSet<String> EMPTY_RESULT = new HashSet<>();
    protected final InvertedIndex ii;
    protected HashSet<String> params = new HashSet<>();

    public void addParam(String param) {
        this.params.add(param);
    }

    public void clearParams() {
        this.params.clear();
    }

    public abstract HashSet<String> get();
}
