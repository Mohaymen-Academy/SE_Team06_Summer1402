public interface Normalizer {
    public String normalize(String str);
}

class DefaultNormalizer implements Normalizer {
    public String normalize(String str) {
        return str.trim().toLowerCase();
    }
}
