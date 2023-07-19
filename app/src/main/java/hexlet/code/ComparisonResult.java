package hexlet.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor

public class ComparisonResult {

    @Getter
    private String key;
    @Getter
    private Object oldValue;
    @Getter
    private Object newValue;
}
