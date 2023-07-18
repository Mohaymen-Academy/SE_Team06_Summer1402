public class StringUtils {
    private static final String[] EMPTY = {};

    public static String normalize(String str) {
        return str.trim().toLowerCase();
    }

    public static String removeAfter(String str, char indicator) {
        String result = "";
        for (int i = 0; i < str.length(); i++)
            if (str.charAt(i) == indicator)
                break;
            else
                result += str.charAt(i);
        return result;
    }

    public static String[] split(String str) {
        if (str.isBlank())
            return EMPTY;
        return str.split("\\s+");
    }

    public static String[] split(String str, String[] delimiters) {
        if (str.isBlank())
            return EMPTY;
        String delimRegex = String.join("|", delimiters);
        return str.split(delimRegex);
    }
}
