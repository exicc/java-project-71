package hexlet.code.formatters;

import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Formatter {

    public static String chooseFormatter(String formatName, List<Map<String, Object>> diff) throws Exception {

        String format = formatName.toUpperCase(Locale.getDefault());

        return switch (format) {
            case "STYLISH" -> Stylish.format(diff);
            case "PLAIN" -> Plain.format(diff);
            case "JSON" -> Json.format(diff);
            default -> throw new Exception("Unsupported format: " + formatName);
        };
    }
}
