package logic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileParser {

    public static <T> List<T> parseJsonToList(String path, Class<T> var) {
        List<T> tList;
        String json = readFromFile(path);
        ObjectMapper objectMapper = JsonMapper.builder()
                .findAndAddModules()
                .build();
        CollectionType listType = objectMapper.getTypeFactory()
                .constructCollectionType(List.class, var);
        try {
            tList = objectMapper.readValue(json, listType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Json parsing error: " + e);
        }
        return tList;
    }

    public static <T>T parseJsonToObject(String path, Class<T> var) {
        T tObject;
        String json = readFromFile(path);
        ObjectMapper objectMapper = JsonMapper.builder()
                .findAndAddModules()
                .build();
        try {
            tObject = objectMapper.readValue(json, var);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Json parsing error: " + e);
        }
        return tObject;
    }

    private static String readFromFile(String path) {
        try {
            return Files.readString(Paths.get(path));
        } catch (IOException e) {
            throw new RuntimeException("File not found");
        }
    }

}
