public interface Normalizer {
    public String normalize(String str);
}

class TestNormalizer implements Normalizer {
    public String normalize(String str) {
        return str.trim().toLowerCase();
    }
}
