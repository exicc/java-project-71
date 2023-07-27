package hexlet.code;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Objects;

public class Generator {
    public static final String CHANGED = "CHANGED";
    public static final String UNCHANGED = "UNCHANGED";
    public static final String REMOVED = "REMOVED";
    public static final String ADDED = "ADDED";

    static List<Map<String, Object>> generateDiffList(Map<String, Object> map1, Map<String, Object> map2) {

        List<Map<String, Object>> diffList = new ArrayList<>();
        Set<String> allKeys = new TreeSet<>(Comparator.naturalOrder());
        allKeys.addAll(map1.keySet());
        allKeys.addAll(map2.keySet());

        allKeys.forEach(key -> {
            Object oldValue = map1.get(key);
            Object newValue = map2.get(key);

            if (!map1.containsKey(key)) {
                diffList.add(Map.of(ADDED, new ComparisonResult(key, newValue, null)));
            } else if (!map2.containsKey(key)) {
                diffList.add(Map.of(REMOVED, new ComparisonResult(key, oldValue, null)));
            } else if (Objects.equals(map1.get(key), map2.get(key))) {
                diffList.add(Map.of(UNCHANGED, new ComparisonResult(key, oldValue, null)));
            } else if (!Objects.equals(map1.get(key), map2.get(key))) {
                diffList.add(Map.of(CHANGED, new ComparisonResult(key, oldValue, newValue)));
            }
        });

        return diffList;
    }
}
