package hexlet.code.formatters;

import hexlet.code.ComparisonResult;
import hexlet.code.Generator;

import java.util.List;
import java.util.Map;

public class Plain {

    public static String format(List<Map<String, Object>> diff) {
        StringBuilder stringBuilder = new StringBuilder();


        String property = "Property ";
        String wasUpdated = " was updated. ";
        String wasRemoved = " was removed";
        String wasAdded = " was added with value: ";


        for (Map<String, Object> map : diff) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {

                String changeType = entry.getKey();
                Object comparisonResultObj = entry.getValue();

                if (!(comparisonResultObj instanceof ComparisonResult comparisonResult)) {
                    throw new IllegalArgumentException("Invalid value type in the Map. "
                            + "Expected ComparisonResult. Represented type: "
                            + comparisonResultObj.getClass());
                }

                String key = comparisonResult.getKey();
                Object oldValue = comparisonResult.getOldValue();
                Object newValue = comparisonResult.getNewValue() != null
                        ? comparisonResult.getNewValue()
                        : null;

                switch (changeType) {
                    case Generator.CHANGED -> stringBuilder.append(property)
                            .append(addQuotes(key))
                            .append(wasUpdated)
                            .append("From ")
                            .append(setComplexValue(oldValue))
                            .append(" to ")
                            .append(setComplexValue(newValue))
                            .append("\n");
                    case Generator.ADDED -> stringBuilder.append(property)
                            .append(addQuotes(key))
                            .append(wasAdded)
                            .append(setComplexValue(oldValue))
                            .append("\n");
                    case Generator.REMOVED -> stringBuilder.append(property)
                            .append(addQuotes(key))
                            .append(wasRemoved)
                            .append("\n");
                    case Generator.UNCHANGED -> {
                    }
                    default -> throw new IllegalArgumentException("Invalid change type: "
                            + changeType);
                }
            }
        }

        return stringBuilder.toString().trim();
    }

    private static Object setComplexValue(Object value) {
        if (value == null) {
            return value;
        }
        if (!(value instanceof Integer || value instanceof Boolean || value instanceof String)) {
            return "[complex value]";
        } else {
            return addQuotes(value);
        }
    }

    private static Object addQuotes(Object value) {
        if (value instanceof String) {
            return "'" + value + "'";
        }
        return value;
    }
}
