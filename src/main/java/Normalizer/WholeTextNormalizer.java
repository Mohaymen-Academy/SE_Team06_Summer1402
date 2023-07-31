package Normalizer;

import lombok.Builder;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Builder
public class WholeTextNormalizer implements Normalizer {
    private List<String> removeMarks;
    private boolean toLowerCase;
    private boolean toUpperCase;

    public String normalize(String str) {
        String result = str;
        result = Optional.ofNullable(this.removeMarks).map(Collection::stream).orElse(Stream.empty()).reduce(result, (acc, mark) -> acc.replace(mark, " "));
        if (toLowerCase) result = result.toLowerCase();
        if (toUpperCase) result = result.toUpperCase();
        return result;
    }
}
