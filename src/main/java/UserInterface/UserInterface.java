package UserInterface;

import java.util.List;
import java.util.Set;

public abstract class UserInterface {
    public abstract void print(String s);

    public abstract void println(String s);

    public abstract String getLine();

    public <T> void printUnOrderedList(List<T> list) {
        list.forEach(value -> this.println("- " + value.toString()));
    }

    public <T> void printUnOrderedList(Set<T> set) {
        set.forEach(value -> this.println("- " + value.toString()));
    }
}