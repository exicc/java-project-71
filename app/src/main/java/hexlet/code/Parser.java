package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.Map;

public class Parser {

    public static Map<String, Object> parseJson(String content) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(content, new ObjectMapper()
                .getTypeFactory()
                .constructMapType(Map.class, String.class, Object.class));
    }

    public static Map<String, Object> parseYaml(String content) throws Exception {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        return mapper.readValue(content, new ObjectMapper()
                .getTypeFactory()
                .constructMapType(Map.class, String.class, Object.class));
    }

    public static String readFileContent(String filePath) throws IOException {
        return Files.readString(Path.of(filePath));
    }

}
