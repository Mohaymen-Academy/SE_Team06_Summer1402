import java.util.Scanner;
import java.util.ArrayList;

public abstract class UserInterface {
    public abstract void print(String s);

    public abstract void println(String s);

    public abstract String getLine();

    public void printOrderedArray(ArrayList<String> arr) {
        for (int i = 0; i < arr.size(); i++)
            println((i + 1) + ". " + arr.get(i));
    }
}

class Console extends UserInterface {
    private Scanner sc;

    public Console() {
        sc = new Scanner(System.in);
    }

    public void print(String s) {
        System.out.print(s);
    }

    public void println(String s) {
        System.out.println(s);
    }

    public String getLine() {
        return sc.nextLine();
    }
}
