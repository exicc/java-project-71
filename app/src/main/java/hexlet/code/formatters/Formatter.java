package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.Map;

public class Formatter {

    public static String chooseFormatter(String formatName, List<Map<String, Object>> diff)
            throws IllegalAccessException, JsonProcessingException {

        if (formatName.equalsIgnoreCase("stylish")) {
            return Stylish.format(diff);
        }
        if (formatName.equalsIgnoreCase("plain")) {
            return Plain.format(diff);
        }
        if (formatName.equalsIgnoreCase("json")) {
            return Json.format(diff);
        } else {
            throw new IllegalAccessException("Unsupported format: " + formatName);
        }
    }
}
