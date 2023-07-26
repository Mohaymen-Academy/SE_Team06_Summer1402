import java.util.ArrayList;

public abstract class Filter {
    protected ArrayList<ArrayList<String>> allData = new ArrayList<ArrayList<String>>();

    public void addData(ArrayList<String> data) {
        allData.add(data);
    }

    public void clearData() {
        allData.clear();
    }

    public abstract ArrayList<String> filter(ArrayList<String> data);
}

class NotFilter extends Filter {

    public ArrayList<String> filter(ArrayList<String> toFilter) {
        Merger<String> orMerger = new OrMerger<String>();
        for (ArrayList<String> data : allData)
            orMerger.add(data);

        ArrayList<String> filterData = orMerger.merge();

        ArrayList<String> result = new ArrayList<String>();
        for (String s : toFilter)
            if (!filterData.contains(s))
                result.add(s);
        return result;
    }
}