package hexlet.code;

import java.util.Comparator;
import java.util.List;

public class Utils {

    public static List<ComparisonResult> listSortingByKey(List<ComparisonResult> comparisonResults) {

        comparisonResults.sort(Comparator.comparing(ComparisonResult::getKey));
        return comparisonResults;
    }
}
