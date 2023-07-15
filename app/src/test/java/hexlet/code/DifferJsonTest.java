package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class DifferJsonTest {

    String format = "stylish";
    @BeforeEach
    void init() {

    }
    @Test
    void testJsonFormatter() throws IOException, IllegalAccessException {

        String filePath1 = "src/test/resources/complex1.json";
        String filePath2 = "src/test/resources/complex2.json";

        // Создаем объект File
        File file1 = new File(filePath1);
        File file2 = new File(filePath2);

        String expectedDiff = """
               {
                   chars1: [a, b, c]
                 - chars2: [d, e, f]
                 + chars2: false
                 - checked: false
                 + checked: true
                 - default: null
                 + default: [value1, value2]
                 - id: 45
                 + id: null
                 - key1: value1
                 + key2: value2
                   numbers1: [1, 2, 3, 4]
                 - numbers2: [2, 3, 4, 5]
                 + numbers2: [22, 33, 44, 55]
                 - numbers3: [3, 4, 5]
                 + numbers4: [4, 5, 6]
                 + obj1: {nestedKey:value, isNested:true}
                 - setting1: Some value
                 + setting1: Another value
                 - setting2: 200
                 + setting2: 300
                 - setting3: true
                 + setting3: none
               }""";
        String actualDiff = Differ.generate(file1, file2, format);

        Assertions.assertEquals(expectedDiff, actualDiff);
    }
}
