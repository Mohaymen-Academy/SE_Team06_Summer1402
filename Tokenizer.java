import java.util.ArrayList;
import java.util.Arrays;

public interface Tokenizer {
    public static final ArrayList<String> EMPTY = new ArrayList<String>();

    public ArrayList<String> tokenize(String str);
}

class RegexTokenizer implements Tokenizer {
    private String _regex;

    public RegexTokenizer(String regex) {
        _regex = regex;
    }

    public ArrayList<String> tokenize(String str) {
        if (str.isBlank())
            return EMPTY;
        return new ArrayList<String>(Arrays.asList(str.split(_regex)));
    }
}