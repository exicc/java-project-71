package hexlet.code.formatters;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import hexlet.code.ComparisonResult;

import java.util.List;

public class Plain {
    public static String format(List<ComparisonResult> diff) {
        StringBuilder stringBuilder = new StringBuilder();
        String property = "Property ";
        String updated = " was updated. ";
        String removed = " was removed ";
        String added = " was added with value: ";

        for (ComparisonResult result : diff) {
            String changeType = result.getChangeType();
            String key = result.getKey();
            Object oldValue = result.getOldValue();
            Object newValue = result.getNewValue();
            if (result.getNewValue() != null) {
                newValue = result.getNewValue();
            }

            switch (changeType) {
                case "-+" -> stringBuilder.append(property)
                        .append(addQuotes(key))
                        .append(updated)
                        .append("From ")
                        .append(getObjectValue(oldValue))
                        .append(" to ")
                        .append(getObjectValue(newValue))
                        .append("\n");
                case "+" -> stringBuilder.append(property)
                        .append(addQuotes(key))
                        .append(added)
                        .append(getObjectValue(oldValue))
                        .append("\n");
                case "-" -> stringBuilder.append(property)
                        .append(addQuotes(key))
                        .append(removed)
                        .append("\n");
                case " " -> {
                }
                default -> throw new IllegalArgumentException("Invalid change type: " + changeType);
            }
        }

        return stringBuilder.toString();
    }

    private static String getObjectValue(Object value) {
        if (value instanceof ArrayNode || value instanceof ObjectNode) {
            return "[complex value]";
        } else {
            return value.toString().replaceAll("\"", "'");
        }
    }
    private static String addQuotes(Object value) {
        return "'" + value + "'";
    }
}
