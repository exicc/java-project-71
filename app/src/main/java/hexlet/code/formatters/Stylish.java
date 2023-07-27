package hexlet.code.formatters;

import hexlet.code.ComparisonResult;
import hexlet.code.Generator;

import java.util.List;
import java.util.Map;


public class Stylish {

    public static String format(List<Map<String, Object>> diff) {
        StringBuilder stringBuilder = new StringBuilder();


        String minus = "  - ";
        String plus = "  + ";


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
                    case Generator.CHANGED -> stringBuilder.append(minus)
                            .append(key)
                            .append(": ")
                            .append(oldValue)
                            .append("\n")
                            .append(plus)
                            .append(key)
                            .append(": ")
                            .append(newValue)
                            .append("\n");
                    case Generator.ADDED -> stringBuilder.append(plus)
                            .append(key)
                            .append(": ")
                            .append(oldValue)
                            .append("\n");
                    case Generator.REMOVED -> stringBuilder.append(minus)
                            .append(key)
                            .append(": ")
                            .append(oldValue)
                            .append("\n");
                    case Generator.UNCHANGED -> stringBuilder.append("    ")
                            .append(key)
                            .append(": ")
                            .append(oldValue)
                            .append("\n");
                    default -> throw new IllegalArgumentException("Invalid change type: "
                            + changeType);
                }
            }
        }
        return "{\n" + stringBuilder + "}";
    }
}
