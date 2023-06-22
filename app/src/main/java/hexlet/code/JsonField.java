package hexlet.code;

import com.fasterxml.jackson.databind.JsonNode;


public class JsonField implements Comparable<JsonField> {
    private final String fieldName;
    private final JsonNode value1;
    private final JsonNode value2;

    public JsonField(String fieldName, JsonNode value1, JsonNode value2) {
        this.fieldName = fieldName;
        this.value1 = value1;
        this.value2 = value2;
    }

    public String getFieldName() {
        return fieldName;
    }

    public JsonNode getValue1() {
        return value1;
    }

    public JsonNode getValue2() {
        return value2;
    }

    @Override
    public int compareTo(JsonField other) {
        return fieldName.compareTo(other.fieldName);
    }
}
