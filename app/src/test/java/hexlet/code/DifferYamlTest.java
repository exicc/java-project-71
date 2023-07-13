package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class DifferYamlTest {

    String format = "stylish";
    @Test
    void testIdenticalFiles() throws IOException, IllegalAccessException {
        File file1 = createTempFile("file1.yml", """
                host: hexlet.io
                timeout: 50
                proxy: 123.234.53.22
                follow: false
                """);
        File file2 = createTempFile("file2.yml", """
                host: hexlet.io
                timeout: 50
                proxy: 123.234.53.22
                follow: false
                """);

        String expectedDiff = """
                {
                    follow: false
                    host: hexlet.io
                    proxy: 123.234.53.22
                    timeout: 50
                }""";
        String actualDiff = Differ.generate(file1, file2, format);

        Assertions.assertEquals(expectedDiff, actualDiff);
    }

    @Test
    void testDifferentValues() throws IOException, IllegalAccessException {
        File file1 = createTempFile("file1.yml", """
                host: hexlet.io
                timeout: 50
                proxy: 123.234.53.22
                follow: false
                """);
        File file2 = createTempFile("file2.yml", """
                host: hexlet.io
                timeout: 20
                verbose: true
                """);

        String expectedDiff = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }""";
        String actualDiff = Differ.generate(file1, file2, format);

        Assertions.assertEquals(expectedDiff, actualDiff);
    }

    @Test
    void testRemovedKey() throws IOException, IllegalAccessException {
        File file1 = createTempFile("file1.yml", """
                host: hexlet.io
                timeout: 50
                proxy: 123.234.53.22
                follow: false
                """);
        File file2 = createTempFile("file2.yml", """
                host: hexlet.io
                timeout: 50
                follow: false
                """);

        String expectedDiff = """
                {
                    follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                    timeout: 50
                }""";
        String actualDiff = Differ.generate(file1, file2, format);

        Assertions.assertEquals(expectedDiff, actualDiff);
    }

    @Test
    void testAddedKey() throws IOException, IllegalAccessException {
        File file1 = createTempFile("file1.yml", """
                host: hexlet.io
                timeout: 50
                follow: false
                """);
        File file2 = createTempFile("file2.yml", """
                host: hexlet.io
                timeout: 50
                proxy: 123.234.53.22
                follow: false
                """);

        String expectedDiff = """
                {
                    follow: false
                    host: hexlet.io
                  + proxy: 123.234.53.22
                    timeout: 50
                }""";
        String actualDiff = Differ.generate(file1, file2, format);

        Assertions.assertEquals(expectedDiff, actualDiff);
    }

    @Test
    void testKeysInAlphabeticalOrder() throws IOException, IllegalAccessException {
        File file1 = createTempFile("file1.yml", """
                c: 1
                a: 2
                b: 3
                """);
        File file2 = createTempFile("file2.yml", """
                b: 3
                a: 2
                c: 1
                """);

        String expectedDiff = """
                {
                    a: 2
                    b: 3
                    c: 1
                }""";
        String actualDiff = Differ.generate(file1, file2, format);

        Assertions.assertEquals(expectedDiff, actualDiff);
    }

    private File createTempFile(String fileName, String content) throws IOException {
        File tempFile = File.createTempFile(fileName, ".yml");
        tempFile.deleteOnExit();

        java.nio.file.Files.writeString(tempFile.toPath(), content);
        return tempFile;
    }
}
