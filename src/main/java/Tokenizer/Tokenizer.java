package Tokenizer;

import java.util.ArrayList;
import java.util.List;

public interface Tokenizer {
    public static final List<String> EMPTY = new ArrayList<>();

    public List<String> tokenize(String str);
}
