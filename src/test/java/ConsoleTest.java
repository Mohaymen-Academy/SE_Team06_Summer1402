import UserInterface.Console;
import UserInterface.UserInterface;

import java.util.List;

public class ConsoleTest {
    UserInterface console = new Console();

    public void whenPrint() {
        console.print("first line ");
        console.print("first line");
        console.println("");
    }

    public void whenPrintLine() {
        console.println("first line");
        console.println("second line");
    }

    public void whenPrintUnOrderedList() {
        console.printUnOrderedList(List.of("first line", "second line", "third line"));
    }

    public static void main(String[] args) {
        ConsoleTest test = new ConsoleTest();
        test.whenPrint();
        test.whenPrintLine();
        test.whenPrintUnOrderedList();
    }
}
