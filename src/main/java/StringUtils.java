public class StringUtils {
    
    public static String removeAfter(String str, char indicator) {
        String result = "";
        for (int i = 0; i < str.length(); i++)
            if (str.charAt(i) == indicator)
                break;
            else
                result += str.charAt(i);
        return result;
    }

}
