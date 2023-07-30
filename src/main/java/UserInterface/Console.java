package UserInterface;

import java.io.PrintStream;
import java.util.Scanner;

public class Console extends UserInterface {
    private final Scanner input = new Scanner(System.in);
    private final PrintStream output = System.out;

    public void print(String s) {
        this.output.print(s);
    }

    public void println(String s) {
        this.output.println(s);
    }

    public String getLine() {
        return this.input.nextLine();
    }
}
