public class StringUtils {
    private static final String[] EMPTY = {};
    private String data;

    public StringUtils(String _data) {
        data = _data;
    }

    public void setString(String newData) {
        data = newData;
    }

    public String getString() {
        return data;
    }

    public void normalize() {
        data = data.trim().toLowerCase();
    }

    public void removeAfter(char indicator) {
        String result = "";
        for (int i = 0; i < data.length(); i++)
            if (data.charAt(i) == indicator)
                break;
            else
                result += data.charAt(i);
        data = result;
    }

    public String[] split(String[] delims) {
        String s = data.replaceAll("^\\s+", "").replaceAll("\\s+$", "");
        if (s.isEmpty())
            return EMPTY;

        String delimRegex = "\\s+";
        for (String delim : delims)
            delimRegex += "|" + delim;

        return data.split(delimRegex);
    }
}
