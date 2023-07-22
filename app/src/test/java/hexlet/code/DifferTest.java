package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public final class DifferTest {

    private static final String STYLISH_FORMAT = "stylish";
    private static final String PLAIN_FORMAT = "plain";
    private static final String JSON_FORMAT = "json";
    private static String resultJson;
    private static String resultPlain;
    private static String resultStylish;

    @ParameterizedTest
    @ValueSource(strings = {"json", "yml"})
    public void generateTest(String format) throws Exception {
        String filePath1 = getFixturePath("complex1." + format).toString();
        String filePath2 = getFixturePath("complex2." + format).toString();

        assertThat(Differ.generate(filePath1, filePath2))
                .isEqualTo(resultStylish);

        assertThat(Differ.generate(filePath1, filePath2, STYLISH_FORMAT))
                .isEqualTo(resultStylish);

        assertThat(Differ.generate(filePath1, filePath2, PLAIN_FORMAT))
                .isEqualTo(resultPlain);

        assertThat(Differ.generate(filePath1, filePath2, JSON_FORMAT))
                .isEqualTo(resultJson);
    }

    private static Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName)
                .toAbsolutePath().normalize();
    }

    private static String readFixture(String fileName) throws Exception {
        Path filePath = getFixturePath(fileName);
        return Files.readString(filePath).trim();
    }

    @BeforeAll
    public static void beforeAll() throws Exception {
        resultJson = readFixture("result_json.json");
        resultPlain = readFixture("result_plain.txt");
        resultStylish = readFixture("result_stylish.txt");
    }
}
