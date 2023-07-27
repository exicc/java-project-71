package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.util.Map;

public class Parser {

    private static Map<String, Object> parseJson(String content) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(content, new ObjectMapper()
                .getTypeFactory()
                .constructMapType(Map.class, String.class, Object.class));
    }

    private static Map<String, Object> parseYaml(String content) throws Exception {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        return mapper.readValue(content, new ObjectMapper()
                .getTypeFactory()
                .constructMapType(Map.class, String.class, Object.class));
    }

    public static Map<String, Object> parseContentByType(String content, String contentType) throws Exception {

        return switch (contentType) {
            case "yml" -> parseYaml(content);
            case "json" -> parseJson(content);
            default -> throw new Exception("Unsupported content type: " + contentType);
        };
    }
}
