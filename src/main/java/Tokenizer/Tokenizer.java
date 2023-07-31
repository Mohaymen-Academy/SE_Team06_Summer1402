package Tokenizer;

import java.util.ArrayList;
import java.util.List;

public interface Tokenizer {
    List<String> EMPTY = new ArrayList<>();

    List<String> tokenize(String str);
}
