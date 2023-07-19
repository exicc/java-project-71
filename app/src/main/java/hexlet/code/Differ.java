package hexlet.code;

import hexlet.code.formatters.Formatter;

import java.io.IOException;
import java.util.List;
import java.util.Map;


public class Differ {

    public static String generate(String filePath1, String filePath2, String formatName)
            throws Exception {
        String content1;
        String content2;
        try {
            content1 = Parser.readFileContent(filePath1);
            content2 = Parser.readFileContent(filePath2);
        } catch (IOException e) {
            throw new IOException("Cant read file: " + e.getMessage());
        }

        Map<String, Object> json1 = parseFile(filePath1, content1);
        Map<String, Object> json2 = parseFile(filePath2, content2);

        List<Map<String, Object>> diff = Generator.generateDiffList(json1, json2);
        return Formatter.chooseFormatter(formatName, diff);
    }
    public static String generate(String filePath1, String filePath2) throws Exception {
        String defaultFormatName = "stylish";
        return generate(filePath1, filePath2, defaultFormatName);
    }

    private static Map<String, Object> parseFile(String filePath, String content) throws Exception {


        if (filePath.endsWith(".yaml") || filePath.endsWith(".yml")) {
            return Parser.parseYaml(content);
        }
        if (filePath.endsWith(".json")) {
            return Parser.parseJson(content);
        } else {
            throw new IllegalArgumentException("Неподдерживаемый формат файла");
        }
    }
}
