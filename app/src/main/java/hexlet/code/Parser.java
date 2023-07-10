package hexlet.code;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;

import java.io.File;
import java.io.IOException;

public class Parser {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static JsonNode parseFile(File file) throws IOException {
        String fileName = file.getName();
        if (fileName.endsWith(".yaml") || fileName.endsWith(".yml")) {
            return parseYaml(file);
        } else {
            return parseJson(file);
        }
    }

    static JsonNode parseJson(File file) throws IOException {
        return OBJECT_MAPPER.readTree(file);
    }

    static JsonNode parseYaml(File file) throws IOException {
        YAMLFactory yamlFactory = new YAMLFactory();
        YAMLParser yamlParser = yamlFactory.createParser(file);
        return OBJECT_MAPPER.readTree(yamlParser);
    }
}