import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        FileReader fileReader = new FileReader("documents");
        ArrayList<String> filenames = fileReader.getFilenames();
        
    }
}
