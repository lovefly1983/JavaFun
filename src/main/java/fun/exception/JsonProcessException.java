package fun.exception;

/**
 * Created by lovefly1983.
 */
public class JsonProcessException extends RuntimeException {
    public JsonProcessException(String json, String message) {
        super(String.format("Unable to parse the json string: [%s] with error message: %s",json, message));
    }
}
