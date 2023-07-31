import InvertedIndex.InvertedIndex;
import Query.Query;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class QueryTest {
    InvertedIndex ii;
    static final ArrayList<String> EMPTY = new ArrayList<>();

    @BeforeEach
    public void setup() {
        this.ii = Mockito.mock(InvertedIndex.class);
        Mockito.when(ii.searchDocuments("first")).thenReturn(new HashSet<>(Set.of("book1", "book2", "book3")));
        Mockito.when(ii.searchDocuments("second")).thenReturn(new HashSet<>(Set.of("book1", "book2")));
        Mockito.when(ii.searchDocuments("third")).thenReturn(new HashSet<>(Set.of("book1")));
        Mockito.when(ii.getAllDocuments()).thenReturn(new HashSet<>(Set.of("book1", "book2", "book3")));
    }

    @Test
    public void run_whenAndQuery() {
        Query query1 = Query.builder().must(new ArrayList<>(List.of("first", "second", "third"))).should(QueryTest.EMPTY).mustNot(QueryTest.EMPTY).build();
        Assertions.assertEquals(Set.of("book1"), query1.run(this.ii));

        Query query2 = Query.builder().must(new ArrayList<>(List.of("first", "second"))).build();
        Assertions.assertEquals(Set.of("book1", "book2"), query2.run(this.ii));
    }

    @Test
    public void run_whenOrQuery() {
        Query query1 = Query.builder().should(new ArrayList<>(List.of("first", "second", "third"))).mustNot(QueryTest.EMPTY).must(QueryTest.EMPTY).build();
        Assertions.assertEquals(Set.of("book1", "book2", "book3"), query1.run(this.ii));

        Query query2 = Query.builder().should(new ArrayList<>(List.of("first", "third"))).build();
        Assertions.assertEquals(Set.of("book1", "book2", "book3"), query2.run(this.ii));
    }

    @Test
    public void run_whenNotQuery() {
        Query query1 = Query.builder().mustNot(new ArrayList<>(List.of("first", "second", "third"))).should(QueryTest.EMPTY).must(QueryTest.EMPTY).build();
        Assertions.assertEquals(Set.of(), query1.run(this.ii));

        Query query2 = Query.builder().mustNot(new ArrayList<>(List.of("second", "third"))).build();
        Assertions.assertEquals(Set.of("book3"), query2.run(this.ii));
    }

    @Test
    public void run_whenComplexQuery() {
        Query query = Query.builder().must(new ArrayList<>(List.of("first", "second"))).mustNot(new ArrayList<>(List.of("third"))).should(new ArrayList<>()).build();
        Assertions.assertEquals(Set.of("book2"), query.run(this.ii));
    }

    @Test
    public void run_whenComplexQuery_1() {
        Query query = Query.builder().should(new ArrayList<>(List.of("first", "second"))).mustNot(new ArrayList<>(List.of("third"))).must(new ArrayList<>()).build();
        Assertions.assertEquals(Set.of("book2", "book3"), query.run(this.ii));
    }

    @Test
    public void run_whenComplexQuery_2() {
        Query query = Query.builder().must(new ArrayList<>()).mustNot(new ArrayList<>(List.of("third"))).should(new ArrayList<>()).build();
        Assertions.assertEquals(Set.of("book2", "book3"), query.run(this.ii));
    }
}
