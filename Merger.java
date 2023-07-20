import java.util.ArrayList;
import java.util.Arrays;

public abstract class Merger<T> {
    public final ArrayList<T> EMPTY = new ArrayList<T>();
    protected ArrayList<ArrayList<T>> allData = new ArrayList<ArrayList<T>>();

    public void add(ArrayList<T> data) {
        allData.add(data);
    }

    public void add(T[] data) {
        allData.add(new ArrayList<T>(Arrays.asList(data)));
    }

    public void clearData() {
        allData.clear();
    }

    public abstract ArrayList<T> merge();
}

class AndMerger<T> extends Merger<T> {

    private ArrayList<T> operate(ArrayList<T> base, ArrayList<T> toMerge) {
        ArrayList<T> result = new ArrayList<T>();
        for (T t : toMerge)
            if (base.contains(t))
                result.add(t);
        return result;
    }

    public ArrayList<T> merge() {
        if (allData.size() == 0)
            return EMPTY;

        ArrayList<T> result = allData.get(0);
        for (int i = 1; i < allData.size(); i++)
            result = operate(result, allData.get(i));
        return result;
    }
}

class OrMerger<T> extends Merger<T> {

    private ArrayList<T> operate(ArrayList<T> base, ArrayList<T> toMerge) {
        ArrayList<T> result = new ArrayList<T>(base);
        for (T t : toMerge)
            if (!base.contains(t))
                result.add(t);
        return result;
    }

    public ArrayList<T> merge() {
        if (allData.size() == 0)
            return EMPTY;

        ArrayList<T> result = allData.get(0);
        for (int i = 1; i < allData.size(); i++)
            result = operate(result, allData.get(i));
        return result;
    }
}