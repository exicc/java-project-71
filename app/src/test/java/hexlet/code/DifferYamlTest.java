package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DifferYamlTest {
    private static final String STYLISH_FORMAT = "stylish";
    private static final String PLAIN_FORMAT = "plain";
    private static final String JSON_FORMAT = "json";
    private static final String DEFAULT_PATH = "src/test/resources/fixtures/";
    private static String resultJson;
    private static String resultPlain;
    private static String resultStylish;

    @Test
    void testYamlToJson() throws Exception {

        String expectedDiff = resultJson;
        String actualDiff = Differ.generate(DEFAULT_PATH
                + "complex1.yml", DEFAULT_PATH
                + "complex2.yml", JSON_FORMAT);

        Assertions.assertEquals(expectedDiff, actualDiff);
    }

    @Test
    void testYamlStylish() throws Exception {

        String expectedDiff = resultStylish;
        String actualDiff = Differ.generate(DEFAULT_PATH
                + "complex1.yml", DEFAULT_PATH
                + "complex2.yml", STYLISH_FORMAT);

        Assertions.assertEquals(expectedDiff, actualDiff);
    }

    @Test
    void testYamlPlain() throws Exception {

        String expectedDiff = resultPlain;
        String actualDiff = Differ.generate(DEFAULT_PATH
                + "complex1.yml", DEFAULT_PATH
                + "complex2.yml", PLAIN_FORMAT);

        Assertions.assertEquals(expectedDiff, actualDiff);
    }

    @Test
    void testWrongFormatName() {
        String expectedErrorMessage = "Unsupported format: formatName";

        Exception exception = Assertions.assertThrows(Exception.class, ()
                -> Differ.generate(DEFAULT_PATH + "complex1.yml", DEFAULT_PATH + "complex2.yml",
                "formatName"));

        Assertions.assertEquals(expectedErrorMessage, exception.getMessage());
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
