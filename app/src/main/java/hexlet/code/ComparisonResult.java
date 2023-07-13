package hexlet.code;

public class ComparisonResult {
    private final String key;
    private final Object oldValue;
    private final Object newValue;
    private final String changeType;

    public ComparisonResult(String key, Object oldValue, Object newValue, String changeType) {
        this.key = key;
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.changeType = changeType;
    }

    public String getKey() {
        return key;
    }

    public Object getOldValue() {
        return oldValue;
    }

    public Object getNewValue() {
        return newValue;
    }

    public String getChangeType() {
        return changeType;
    }
}
