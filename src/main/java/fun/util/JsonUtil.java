package fun.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fun.exception.JsonProcessException;

import java.io.IOException;

/**
 * Created by lovefly1983.
 */
public class JsonUtil {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T fromJson(String json) {
        T ret = null;
        if (json != null) {
            try {
                ret = mapper.readValue(json, new TypeReference<T>() {});
            } catch (IOException e) {
                throw new JsonProcessException(json, e.getMessage());
            }
        }
        return ret;
    }

    public static <T> String toJson(T object) {
        String ret = null;
        if (object != null) {
            try {
                ret = mapper.writeValueAsString(object);
            } catch (JsonProcessingException e) {
                throw new JsonProcessException(object.toString(), e.getMessage());
            }
        }

        return ret;
    }
}
