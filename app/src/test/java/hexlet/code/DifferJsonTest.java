package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class DifferJsonTest {
    @Test
    void testIdenticalFiles() throws IOException {
        File file1 = createTempFile("{\"host\":\"hexlet.io\","
                + "\"timeout\":50,"
                + "\"proxy\":\"123.234.53.22\","
                + "\"follow\":false}");
        File file2 = createTempFile("{\"host\":\"hexlet.io\""
                + ",\"timeout\":50,\"proxy\":\"123.234.53.22\",\"follow\":false}");

        String expectedDiff = """
                {
                  follow: false
                  host: hexlet.io
                  proxy: 123.234.53.22
                  timeout: 50
                }""";
        String actualDiff = Differ.generate(file1, file2);

        Assertions.assertEquals(expectedDiff, actualDiff);
    }

    @Test
    void testDifferentValues() throws IOException {
        File file1 = createTempFile("{\"host\":\"hexlet.io\","
                + "\"timeout\":50,\"proxy\":\"123.234.53.22\",\"follow\":false}");
        File file2 = createTempFile("{\"host\":\"hexlet.io\",\"timeout\":20,\"verbose\":true}");

        String expectedDiff = """
                {
                  - follow: false
                  host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }""";
        String actualDiff = Differ.generate(file1, file2);

        Assertions.assertEquals(expectedDiff, actualDiff);
    }

    @Test
    void testRemovedKey() throws IOException {
        File file1 = createTempFile("{\"host\":\"hexlet.io\","
                + "\"timeout\":50,\"proxy\":\"123.234.53.22\",\"follow\":false}");
        File file2 = createTempFile("{\"host\":\"hexlet.io\",\"timeout\":50,\"follow\":false}");

        String expectedDiff = """
                {
                  follow: false
                  host: hexlet.io
                  - proxy: 123.234.53.22
                  timeout: 50
                }""";
        String actualDiff = Differ.generate(file1, file2);

        Assertions.assertEquals(expectedDiff, actualDiff);
    }

    @Test
    void testAddedKey() throws IOException {
        File file1 = createTempFile("{\"host\":\"hexlet.io\",\"timeout\":50,\"follow\":false}");
        File file2 = createTempFile("{\"host\":\"hexlet.io\","
                + "\"timeout\":50,\"proxy\":\"123.234.53.22\",\"follow\":false}");

        String expectedDiff = """
                {
                  follow: false
                  host: hexlet.io
                  + proxy: 123.234.53.22
                  timeout: 50
                }""";
        String actualDiff = Differ.generate(file1, file2);

        Assertions.assertEquals(expectedDiff, actualDiff);
    }

    @Test
    void testKeysInAlphabeticalOrder() throws IOException {
        File file1 = createTempFile("{\"c\": 1, \"a\": 2, \"b\": 3}");
        File file2 = createTempFile("{\"b\": 3, \"a\": 2, \"c\": 1}");

        String expectedDiff = """
                {
                  a: 2
                  b: 3
                  c: 1
                }""";
        String actualDiff = Differ.generate(file1, file2);

        Assertions.assertEquals(expectedDiff, actualDiff);
    }

    private File createTempFile(String content) throws IOException {
        File tempFile = File.createTempFile("test", ".json");
        tempFile.deleteOnExit();

        java.nio.file.Files.writeString(tempFile.toPath(), content);
        return tempFile;
    }
}
