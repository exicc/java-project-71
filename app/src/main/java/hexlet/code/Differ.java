package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


public class Differ {

    public static String generate(File file1, File file2, String formatName)
            throws IOException, IllegalAccessException {
        JsonNode json1 = Parser.parseFile(file1);
        JsonNode json2 = Parser.parseFile(file2);

        List<ComparisonResult> diff = generateDiffList(json1, json2);
        return getFormattedList(formatName, diff);
    }

    private static String getFormattedList(String formatName, List<ComparisonResult> diff)
            throws IllegalAccessException, JsonProcessingException {
        return getFormat(formatName, diff);
    }

    private static String getFormat(String formatName, List<ComparisonResult> diff)
            throws IllegalAccessException, JsonProcessingException {

        if (formatName.equalsIgnoreCase("stylish")) {
            return Stylish.format(Utils.listSortingByKey(diff));
        } else if (formatName.equalsIgnoreCase("plain")) {
            return Plain.format(Utils.listSortingByKey(diff));
        } else if (formatName.equalsIgnoreCase("json")) {
            return Json.format(Utils.listSortingByKey(diff));
        } else {
            throw new IllegalAccessException("Unsupported format: " + formatName);
        }
    }

    private static List<ComparisonResult> generateDiffList(JsonNode json1, JsonNode json2) {
        Set<String> allKeys = new TreeSet<>();
        json1.fieldNames().forEachRemaining(allKeys::add);
        json2.fieldNames().forEachRemaining(allKeys::add);

        List<ComparisonResult> diff = new ArrayList<>();

        allKeys.forEach(key -> {
            JsonNode value1 = json1.get(key);
            JsonNode value2 = json2.get(key);

            if (value1 != null && value2 != null) {
                if (!value1.equals(value2)) {
                    diff.add(new ComparisonResult(key, value1, value2, "-+"));
                } else {
                    diff.add(new ComparisonResult(key, value1, null, " "));
                }
            } else if (value1 != null) {
                diff.add(new ComparisonResult(key, value1, null,  "-"));
            } else if (value2 != null) {
                diff.add(new ComparisonResult(key, value2, null, "+"));
            }
        });

        return diff;
    }
}
