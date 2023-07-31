package Normalizer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class WholeTextNormalizerTest {
    @Test
    public void normalize_whenRemoveMarkOnly() {
        Normalizer normalizer = WholeTextNormalizer.builder().removeMarks(Arrays.asList(",", ".", "!", "-")).build();
        String actual = normalizer.normalize(",this. is!! a--- better, ,version");
        Assertions.assertEquals(" this  is   a    better   version", actual);
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
    public void normalize_whenRemoveMarksToLowerCase() {
        Normalizer normalizer = WholeTextNormalizer.builder().removeMarks(Arrays.asList("'", "\"", ",", ".", "!", "-")).toLowerCase(true).build();
        String actual = normalizer.normalize(",ThIs.Is!GoInG-To\"Work\"");
        Assertions.assertEquals(" this is going to work ", actual);
    }
}
