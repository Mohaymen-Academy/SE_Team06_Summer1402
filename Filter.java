import java.util.ArrayList;

public interface Filter {
    public ArrayList<String> filter(ArrayList<String> data);

    public void addData(ArrayList<String> newData);
}

class AndFilter implements Filter {
    private ArrayList<String> _data;

    public AndFilter(ArrayList<String> data) {
        _data = data;
    }

    public ArrayList<String> filter(ArrayList<String> data) {
        ArrayList<String> result = new ArrayList<String>();
        for (String s : data)
            if (_data.contains(s))
                result.add(s);
        return result;
    }

    public void addData(ArrayList<String> data) {
        _data = filter(data);
    }

}