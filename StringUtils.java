import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class StringUtils {
    private static final String[] EMPTY = {};
    private String data;

    public StringUtils(String _data) {
        data = _data;
    }

    public String[] split() {
        String s = data.replaceAll("^\\s+", "").replaceAll("\\s+$", "");
        if (s.isEmpty())
            return EMPTY;
        return s.split("\\s+|:|-");
    }
}
