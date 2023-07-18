import java.util.ArrayList;
import java.util.HashMap;

public class InvertedIndex<T, V> {
    private HashMap<T, ArrayList<V>> wordMap;

    public InvertedIndex() {
        wordMap = new HashMap<T, ArrayList<V>>();
    }

    public void addDocument(V documentRef, T[] words) {
        for (T word : words)
            if (wordMap.get(word) != null) {
                if (!wordMap.get(word).contains(documentRef))
                    wordMap.get(word).add(documentRef);
            } else {
                ArrayList<V> newWordList = new ArrayList<V>();
                newWordList.add(documentRef);
                wordMap.put(word, newWordList);
            }
    }

    public ArrayList<V> searchDocuments(T searchKey) {
        if (wordMap.get(searchKey) != null)
            return wordMap.get(searchKey);
        else 
            return new ArrayList<V>();
    }

    public void show() {
        for (T word : wordMap.keySet()) {
            System.out.println("===" + word + "===");
            for (V bookName : wordMap.get(word))
                System.out.println(bookName);
        }

    }
}