package hexlet.code;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;


public class Differ {

    public static String generate(File file1, File file2) throws IOException {
        JsonNode json1 = Parser.parseFile(file1);
        JsonNode json2 = Parser.parseFile(file2);

        Map<String, Object> diff = generateDiff(json1, json2);

        return generateDiffString(diff);
    }

    private static Map<String, Object> generateDiff(JsonNode json1, JsonNode json2) {
        Set<String> allKeys = new TreeSet<>();
        json1.fieldNames().forEachRemaining(allKeys::add);
        json2.fieldNames().forEachRemaining(allKeys::add);

        Map<String, Object> diff = new LinkedHashMap<>();

        allKeys.forEach(key -> {
            JsonNode value1 = json1.get(key);
            JsonNode value2 = json2.get(key);

            if (value1 != null && value2 != null) {
                if (!value1.equals(value2)) {
                    diff.put("  - " + key, value1);
                    diff.put("  + " + key, value2);
                } else {
                    diff.put("  " + key, value1);
                }
            } else if (value1 != null) {
                diff.put("  - " + key, value1);
            } else if (value2 != null) {
                diff.put("  + " + key, value2);
            }
        });

        return diff;
    }

    private static String generateDiffString(Map<String, Object> diff) {
        StringBuilder diffString = new StringBuilder();

        for (Map.Entry<String, Object> entry : diff.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            String valueString = value.toString().replaceAll("\"", "");
            diffString.append(key).append(": ").append(valueString).append("\n");
        }

        return "{\n" + diffString + "}";
    }
}
