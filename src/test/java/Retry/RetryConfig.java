package Retry;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class RetryConfig {
    private static int maxRetries = 2;
    private static boolean applyGlobally = false;

    static {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(new File("retry-config.json"));
            JsonNode settings = node.get("retrySettings");
            maxRetries = settings.get("maxRetries").asInt();
            applyGlobally = settings.get("applyGlobally").asBoolean();
        } catch (IOException e) {
            System.out.println("⚠ Retry config file not found, defaults will be used");
        }
    }

    public static int getMaxRetries() {
        return maxRetries;
    }

    public static boolean isApplyGlobally() {
        return applyGlobally;
    }
}
