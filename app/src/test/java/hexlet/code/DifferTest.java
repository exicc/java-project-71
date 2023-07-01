package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DifferTest {

    @Test
    public void testGenerate() throws Exception {
        String str = "String";
        assertThat(str).isNotEmpty();
    }
}
