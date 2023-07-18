import java.util.Scanner;
import java.util.ArrayList;

public class ConsoleInterface {
    private Scanner sc;

    public ConsoleInterface() {
        sc = new Scanner(System.in);
    }

    public String getWord() {
        System.out.print("Enter Your Search Key(Ctrl+C to finish): ");
        return sc.nextLine();
    }

    public void showResultBooks(ArrayList<String> books) {
        if (books.size() == 0) {
            System.out.println("<--No Results-->");
            return;
        }
        System.out.println("<-Search Results->");
        for (int i = 0; i < books.size(); i++)
            System.out.println((i + 1) + "." + books.get(i));
    }

    public void print(String s) {
        System.out.println(s);
    }
}
