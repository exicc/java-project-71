package hexlet.code;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Differ {
    public static void generate(String filePath1, String filePath2) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        JsonNode data1 = mapper.readTree(new File(filePath1));
        JsonNode data2 = mapper.readTree(new File(filePath2));

        String result = compareJsonNodes(data1, data2);
        System.out.println(result);
    }

    public static String compareJsonNodes(JsonNode node1, JsonNode node2) {
        Iterator<String> fieldNames = node1.fieldNames();
        List<JsonField> fields = new ArrayList<>();

        while (fieldNames.hasNext()) {
            String fieldName = fieldNames.next();
            JsonNode value1 = node1.get(fieldName);
            JsonNode value2 = node2.get(fieldName);

            fields.add(new JsonField(fieldName, value1, value2));
        }

        fieldNames = node2.fieldNames();

        while (fieldNames.hasNext()) {
            String fieldName = fieldNames.next();
            JsonNode value1 = node1.get(fieldName);
            JsonNode value2 = node2.get(fieldName);

            if (value1 == null) {
                fields.add(new JsonField(fieldName, null, value2));
            }
        }

        Collections.sort(fields);

        StringBuilder result = new StringBuilder();
        for (JsonField field : fields) {
            String fieldName = field.getFieldName();
            JsonNode value1 = field.getValue1();
            JsonNode value2 = field.getValue2();

            if (value2 == null) {
                result.append("    - ").append(fieldName).append(": ").append(value1).append("\n");
            } else if (value1 != null && !value1.equals(value2)) {
                result.append("    - ").append(fieldName).append(": ").append(value1).append("\n");
                result.append("    + ").append(fieldName).append(": ").append(value2).append("\n");
            } else {
                result.append("    ").append(fieldName).append(": ").append(value1).append("\n");
            }
        }

        return "{\n" + result + "}";
    }
}
