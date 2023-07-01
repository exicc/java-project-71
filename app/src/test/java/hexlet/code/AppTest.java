package hexlet.code;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {
    @Test
    public void test1(){
        String str = "String";
        assertThat(str).isNotNull();
        System.out.println("Done");
    }
}
