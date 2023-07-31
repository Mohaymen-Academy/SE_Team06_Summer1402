import Tokenizer.RegexTokenizer;
import Tokenizer.Tokenizer;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ArgumentParser {
    private static final Tokenizer tokenizer = new RegexTokenizer("\\s+");
    private static final ArrayList<String> EMPTY_RESULT = new ArrayList<>();
    private ArrayList<String> args;

    public void setData(String data) {
        this.args = new ArrayList<>(ArgumentParser.tokenizer.tokenize(data));
    }

    public boolean isEmptyArgs() {
        return this.args == null || this.args.size() == 0;
    }

    public ArrayList<String> parse(String indicator) {
        if (this.isEmptyArgs())
            return ArgumentParser.EMPTY_RESULT;
        List<String> result = this.args.stream().filter(arg -> arg.startsWith(indicator)).toList();
        return new ArrayList<>(result.stream().peek(arg -> this.args.remove(arg)).map(arg -> arg.replaceFirst(Pattern.quote(indicator), "")).collect(Collectors.toList()));
    }

    public ArrayList<String> parse() {
        if (isEmptyArgs())
            return ArgumentParser.EMPTY_RESULT;
        return this.args;
    }

    public void clearData() {
        if (this.args != null)
            this.args.clear();
    }

}
