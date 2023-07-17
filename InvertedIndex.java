import java.util.ArrayList;
import java.util.HashMap;

public class InvertedIndex {
    private HashMap<String, ArrayList<String>> wordTable;

    public InvertedIndex() {
        wordTable = new HashMap<String, ArrayList<String>>();
    }

    public void addDocument(String documentName, String[] words) {
        for (String word : words)
            if (wordTable.get(word) != null) {
                if (!wordTable.get(word).contains(documentName))
                    wordTable.get(word).add(documentName);
            } else {
                ArrayList<String> newWordList = new ArrayList<String>();
                newWordList.add(documentName);
                wordTable.put(word, newWordList);
            }
    }

    public ArrayList<String> searchDocuments(String searchKey) {
        return wordTable.get(searchKey);
    }

    public void show() {
        for (String word : wordTable.keySet()) {
            System.out.println("===" + word + "===");
            for (String bookName : wordTable.get(word))
                System.out.println(bookName);
        }

    }
}