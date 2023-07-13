package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class DifferJsonTest {

    String format = "stylish";
    @Test
    void testIdenticalFiles() throws IOException, IllegalAccessException {
        File file1 = createTempFile("""
                {
                  "numbers1": [1, 2, 3, 4],
                  "numbers2": [2, 3, 4, 5],
                  "default": null,
                  "numbers3": [3, 4, 5],
                  "chars1": ["a", "b", "c"],
                  "chars2": ["d", "e", "f"]
                }
                """);
        File file2 = createTempFile("""
                {
                  "numbers1": [1, 2, 3, 4],
                  "numbers2": [22, 33, 44, 55],
                  "default": ["value1", "value2"],
                  "numbers4": [4, 5, 6],
                  "chars1": ["a", "b", "c"],
                  "chars2": false,
                  "obj1": {
                      "nestedKey": "value",
                      "isNested": true
                    }
                }
                """);

        String expectedDiff = """
                {
                    chars1: [a,b,c]
                  - chars2: [d,e,f]
                  + chars2: false
                  - default: null
                  + default: [value1,value2]
                    numbers1: [1,2,3,4]
                  - numbers2: [2,3,4,5]
                  + numbers2: [22,33,44,55]
                  - numbers3: [3,4,5]
                  + numbers4: [4,5,6]
                  + obj1: {nestedKey:value,isNested:true}
                }""";
        String actualDiff = Differ.generate(file1, file2, format);

        Assertions.assertEquals(expectedDiff, actualDiff);
    }

    private File createTempFile(String content) throws IOException {
        File tempFile = File.createTempFile("test", ".json");
        tempFile.deleteOnExit();

        java.nio.file.Files.writeString(tempFile.toPath(), content);
        return tempFile;
    }
}
