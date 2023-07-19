package hexlet.code.formatters;

import hexlet.code.ComparisonResult;

import java.util.List;
import java.util.Map;


public class Stylish {
    static final String CHANGED = "CHANGED";
    static final String UNCHANGED = "UNCHANGED";
    static final String REMOVED = "DELETED";
    static final String ADDED = "ADDED";

    public static String format(List<Map<String, Object>> diff) {
        StringBuilder stringBuilder = new StringBuilder();


        String minus = "  - ";
        String plus = "  + ";


        diff.forEach(map -> map.forEach((changeType, comparisonResultObj) -> {
            if (!(comparisonResultObj instanceof ComparisonResult comparisonResult)) {
                throw new IllegalArgumentException("Invalid value type in the Map. Expected ComparisonResult.");
            }

            String key = comparisonResult.getKey();
            Object oldValue = comparisonResult.getOldValue();
            Object newValue = comparisonResult.getNewValue() != null
                    ? comparisonResult.getNewValue()
                    : null;

            switch (changeType) {
                case CHANGED -> stringBuilder.append(minus)
                        .append(key)
                        .append(": ")
                        .append(replaceQuotes(oldValue))
                        .append("\n")
                        .append(plus)
                        .append(key)
                        .append(": ")
                        .append(replaceQuotes(newValue))
                        .append("\n");
                case ADDED -> stringBuilder.append(plus)
                        .append(key)
                        .append(": ")
                        .append(oldValue)
                        .append("\n");
                case REMOVED -> stringBuilder.append(minus)
                        .append(key)
                        .append(": ")
                        .append(replaceQuotes(oldValue))
                        .append("\n");
                case UNCHANGED -> stringBuilder.append("    ")
                        .append(key)
                        .append(": ")
                        .append(replaceQuotes(oldValue))
                        .append("\n");
                default -> throw new IllegalArgumentException("Invalid change type: "
                        + changeType);
            }
        }));

        return "{\n" + stringBuilder + "}";
    }

    private static String replaceQuotes(Object value) {

        return value != null ? value
                .toString()
                .replaceAll("[\"']", "")
                : null;
    }
}
