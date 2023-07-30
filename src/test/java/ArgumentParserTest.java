import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ArgumentParserTest {
    ArgumentParser ap = new ArgumentParser();

    @Test
    public void whenOnlyMustArguments() {
        this.ap.setData("cat dog horse");
        Assertions.assertEquals(List.of(), this.ap.parse("+"));
        Assertions.assertEquals(List.of(), this.ap.parse("-"));
        Assertions.assertEquals(List.of("cat", "dog", "horse"), this.ap.parse());
    }

    @Test
    public void whenOnlyShouldArguments() {
        this.ap.setData("+cat +dog +horse");
        Assertions.assertEquals(List.of(), this.ap.parse("-"));
        Assertions.assertEquals(List.of("cat", "dog", "horse"), this.ap.parse("+"));
        Assertions.assertEquals(List.of(), this.ap.parse());
    }

    @Test
    public void whenOnlyMustNotArguments() {
        this.ap.setData("-cat -dog -horse");
        Assertions.assertEquals(List.of(), this.ap.parse("+"));
        Assertions.assertEquals(List.of("cat", "dog", "horse"), this.ap.parse("-"));
        Assertions.assertEquals(List.of(), this.ap.parse());
    }

    @Test
    public void whenAllArguments() {
        this.ap.setData("cat +dog horse -duck bird +llama");
        Assertions.assertEquals(List.of("dog", "llama"), this.ap.parse("+"));
        Assertions.assertEquals(List.of("duck"), this.ap.parse("-"));
        Assertions.assertEquals(List.of("cat", "horse", "bird"), this.ap.parse());
    }
}
