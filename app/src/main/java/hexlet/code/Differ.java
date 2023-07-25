package hexlet.code;

import hexlet.code.formatters.Formatter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;


public class Differ {

    public static String generate(String filePath1, String filePath2, String formatName) throws Exception {

        String contentType1 = getDataFormat(filePath1);
        String contentType2 = getDataFormat(filePath2);

        String content1 = readContent(filePath1);
        String content2 = readContent(filePath2);

        Map<String, Object> contentMap1;
        Map<String, Object> contentMap2;

        if (contentType1.equals(contentType2)) {
            contentMap1 = Parser.parseContentByType(content1, contentType1);
            contentMap2 = Parser.parseContentByType(content2, contentType2);
        } else {
            throw new Exception("Trying compare " + contentType1 + " with " + contentType2);
        }

        List<Map<String, Object>> diff = Generator.generateDiffList(contentMap1, contentMap2);
        return Formatter.chooseFormatter(formatName, diff);
    }

    public static String generate(String filePath1, String filePath2) throws Exception {
        String defaultFormatName = "stylish";
        return generate(filePath1, filePath2, defaultFormatName);
    }

    private static String getDataFormat(String filePath) {
        int index = filePath.lastIndexOf('.');
        return index > 0
                ? filePath.substring(index + 1)
                : "";
    }

    private static String readContent(String filePath) throws IOException {
        return Files.readString(Path.of(filePath));
    }
}
