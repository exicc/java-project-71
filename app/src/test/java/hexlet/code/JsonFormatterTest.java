package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;


public class JsonFormatterTest {
    String format = "json";
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
                [ {
                  "key" : "chars1",
                  "oldValue" : [ "a", "b", "c" ],
                  "changeType" : " "
                }, {
                  "key" : "chars2",
                  "oldValue" : [ "d", "e", "f" ],
                  "newValue" : false,
                  "changeType" : "-+"
                }, {
                  "key" : "default",
                  "oldValue" : null,
                  "newValue" : [ "value1", "value2" ],
                  "changeType" : "-+"
                }, {
                  "key" : "numbers1",
                  "oldValue" : [ 1, 2, 3, 4 ],
                  "changeType" : " "
                }, {
                  "key" : "numbers2",
                  "oldValue" : [ 2, 3, 4, 5 ],
                  "newValue" : [ 22, 33, 44, 55 ],
                  "changeType" : "-+"
                }, {
                  "key" : "numbers3",
                  "oldValue" : [ 3, 4, 5 ],
                  "changeType" : "-"
                }, {
                  "key" : "numbers4",
                  "oldValue" : [ 4, 5, 6 ],
                  "changeType" : "+"
                }, {
                  "key" : "obj1",
                  "oldValue" : {
                    "nestedKey" : "value",
                    "isNested" : true
                  },
                  "changeType" : "+"
                } ]""";
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
