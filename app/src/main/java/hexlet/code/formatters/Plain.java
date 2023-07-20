package hexlet.code.formatters;

import hexlet.code.ComparisonResult;

import java.util.List;
import java.util.Map;

public class Plain {
    static final String CHANGED = "CHANGED";
    static final String UNCHANGED = "UNCHANGED";
    static final String REMOVED = "DELETED";
    static final String ADDED = "ADDED";

    public static String format(List<Map<String, Object>> diff) {
        StringBuilder stringBuilder = new StringBuilder();


        String property = "Property ";
        String wasUpdated = " was updated. ";
        String wasRemoved = " was removed";
        String wasAdded = " was added with value: ";


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
                case CHANGED -> stringBuilder.append(property)
                        .append(addQuotes(key))
                        .append(wasUpdated)
                        .append("From ")
                        .append(setComplexValue(oldValue))
                        .append(" to ")
                        .append(setComplexValue(newValue))
                        .append("\n");
                case ADDED -> stringBuilder.append(property)
                        .append(addQuotes(key))
                        .append(wasAdded)
                        .append(setComplexValue(oldValue))
                        .append("\n");
                case REMOVED -> stringBuilder.append(property)
                        .append(addQuotes(key))
                        .append(wasRemoved)
                        .append("\n");
                case UNCHANGED -> {
                }
                default -> throw new IllegalArgumentException("Invalid change type: "
                        + changeType);
            }
        }));

        return stringBuilder.toString().trim();
    }

    private static Object setComplexValue(Object value) {
        if (value == null) {
            return value;
        } else {
            if (!(value instanceof Integer || value instanceof Boolean || value instanceof String)) {
                return "[complex value]";
            } else {
                return addQuotes(value);
            }
        }
    }

    private static Object addQuotes(Object value) {
        if (value instanceof String) {
            return "'" + value + "'";
        }
        return value;
    }
}
