package Normalizer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class WholeTextNormalizerTest {
    @Test
    public void normalize_whenRemoveMarkOnly() {
        Normalizer normalizer = WholeTextNormalizer.builder().removeMarks(Arrays.asList(",", ".", "!", "-")).build();
        String actual = normalizer.normalize(",this. is!! a--- better, ,version");
        Assertions.assertEquals("this is a better version", actual);
    }

    @Test
    public void normalize_whenSpaceMarkOnly() {
        Normalizer normalizer = WholeTextNormalizer.builder().spaceMarks(Arrays.asList(",", ".", "!", "-")).build();
        String actual = normalizer.normalize(",This.is!going-to work");
        Assertions.assertEquals(" This is going to work", actual);

    }

    @Test
    public void normalize_whenToLowerCaseOnly() {
        Normalizer normalizer = WholeTextNormalizer.builder().toLowerCase(true).build();
        String actual = normalizer.normalize("ThIs Is GoInG tO wOrK!");
        Assertions.assertEquals("this is going to work!", actual);
    }

    @Test
    public void normalize_whenToUpperCaseOnly() {
        Normalizer normalizer = WholeTextNormalizer.builder().toUpperCase(true).build();
        String actual = normalizer.normalize("ThIs Is GoInG tO wOrK!");
        Assertions.assertEquals("THIS IS GOING TO WORK!", actual);
    }

    @Test
    public void normalize_whenRemoveMarksSpaceMarksToLowerCase() {
        Normalizer normalizer = WholeTextNormalizer.builder().spaceMarks(Arrays.asList(",", ".", "!", "-")).removeMarks(Arrays.asList("'", "\"")).toLowerCase(true).build();
        String actual = normalizer.normalize(",ThI's.I\"s!GoIn'G-To Wor\"k");
        Assertions.assertEquals(" this is going to work", actual);
    }
}
