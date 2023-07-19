import java.util.ArrayList;

public class Options {
    private ArrayList<String> _data;

    public Options(ArrayList<String> data) {
        _data = data;
    }

    public ArrayList<String> pop(char indicator) {
        ArrayList<String> result = new ArrayList<String>();
        ArrayList<String> newData = new ArrayList<String>();
        for (String s : _data)
            if (s.charAt(0) == indicator)
                result.add(s.substring(1));
            else
                newData.add(s);
        _data = newData;
        return result;
    }

    public ArrayList<String> getRemained() {
        return _data;
    }

}
