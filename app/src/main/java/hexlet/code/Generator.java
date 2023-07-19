package hexlet.code;


import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Comparator;

public class Generator {
    static final String CHANGED = "CHANGED";
    static final String UNCHANGED = "UNCHANGED";
    static final String REMOVED = "DELETED";
    static final String ADDED = "ADDED";

    static List<Map<String, Object>> generateDiffList(Map<String, Object> map1, Map<String, Object> map2) {

        List<Map<String, Object>> diffList = new ArrayList<>();
        Set<String> allKeys = new TreeSet<>(Comparator.naturalOrder());
        allKeys.addAll(map1.keySet());
        allKeys.addAll(map2.keySet());

        allKeys.forEach(key -> {
            Object value1 = map1.get(key);
            Object value2 = map2.get(key);

            if (value1 != null && value2 != null) {
                diffList.add(value1.equals(value2) ? Map.of(UNCHANGED, new ComparisonResult(key, value1, null))
                        : Map.of(CHANGED, new ComparisonResult(key, value1, value2)));
            } else if (value1 != null) {
                diffList.add(Map.of(REMOVED, new ComparisonResult(key, value1, null)));
            } else if (value2 != null) {
                diffList.add(Map.of(ADDED, new ComparisonResult(key, value2, null)));
            }
        });

        return diffList;
    }
}
