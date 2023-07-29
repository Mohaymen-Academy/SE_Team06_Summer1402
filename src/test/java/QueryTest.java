import InvertedIndex.InvertedIndex;
import Query.AndQuery;
import Query.NotQuery;
import Query.OrQuery;
import Query.Query;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashSet;
import java.util.Set;

public class QueryTest {
    InvertedIndex ii;
    Query query;

    @BeforeEach
    public void setup() {
        this.ii = Mockito.mock(InvertedIndex.class);
        Mockito.when(ii.searchDocuments("first")).thenReturn(Set.of("book1", "book2", "book3"));
        Mockito.when(ii.searchDocuments("second")).thenReturn(Set.of("book1", "book2"));
        Mockito.when(ii.searchDocuments("third")).thenReturn(Set.of("book1"));
        Mockito.when(ii.getAllDocuments()).thenReturn(new HashSet<>(Set.of("book1", "book2", "book3")));
    }

    @Test
    public void whenAndQuery() {
        this.query = new AndQuery(this.ii);
        this.query.setParams(new HashSet<>(Set.of("first", "second", "third")));
        Assertions.assertEquals(Set.of("book1"), query.get());
        this.query.clearParams();
        this.query.setParams(new HashSet<>(Set.of("first", "second")));
        Assertions.assertEquals(Set.of("book1", "book2"), query.get());
    }

    @Test
    public void whenOrQuery() {
        this.query = new OrQuery(this.ii);
        this.query.setParams(new HashSet<>(Set.of("first", "second", "third")));
        Assertions.assertEquals(Set.of("book1", "book2", "book3"), query.get());
        this.query.clearParams();
        this.query.setParams(new HashSet<>(Set.of("first")));
        Assertions.assertEquals(Set.of("book1", "book2", "book3"), query.get());
    }

    @Test
    public void whenNotQuery() {
        this.query = new NotQuery(this.ii);

        this.query.setParams(new HashSet<>(Set.of("first", "second", "third")));
        Assertions.assertEquals(Set.of(), query.get());
        this.query.clearParams();

        this.query.setParams(new HashSet<>(Set.of("second", "third")));
        Assertions.assertEquals(Set.of("book3"), query.get());
        this.query.clearParams();

        this.query.setParams(new HashSet<>(Set.of()));
        Assertions.assertEquals(Set.of("book1", "book2", "book3"), query.get());
    }
}
