package hexlet.code.formatters;

import hexlet.code.ComparisonResult;

import java.util.List;

public class Stylish {
    public static String format(List<ComparisonResult> diff) {
        StringBuilder stringBuilder = new StringBuilder();
        String minus = "  - ";
        String plus = "  + ";

        for (ComparisonResult result : diff) {
            String changeType = result.getChangeType();
            String key = result.getKey();
            Object oldValue = result.getOldValue();
            Object newValue = result.getNewValue();

            switch (changeType) {
                case "-+" -> stringBuilder.append(minus)
                        .append(key)
                        .append(": ")
                        .append(replaceQuotes(oldValue))
                        .append("\n")
                        .append(plus)
                        .append(key)
                        .append(": ")
                        .append(replaceQuotes(newValue))
                        .append("\n");
                case "+" -> stringBuilder.append(plus)
                        .append(key)
                        .append(": ")
                        .append(replaceQuotes(oldValue))
                        .append("\n");
                case "-" -> stringBuilder.append(minus)
                        .append(key)
                        .append(": ")
                        .append(replaceQuotes(oldValue))
                        .append("\n");
                case " " -> stringBuilder.append("    ")
                        .append(key)
                        .append(": ")
                        .append(replaceQuotes(oldValue))
                        .append("\n");
                default -> throw new IllegalArgumentException("Invalid change type: " + changeType);
            }
        }

        return "{\n" + stringBuilder + "}";
    }

    private static String replaceQuotes(Object value) {

        return value.toString().replaceAll("[\"']", "").replaceAll(",", ", ");
    }
}
