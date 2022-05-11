package s.m.torn;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Slf4j
public final class TornClient {

    private static final String ENDPOINT_TEMPLATE = "%s/%s/?selections=%s&key=%s";
    private static final String BASE_URL ="https://api.torn.com";
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private static TornClient INSTANCE;

    static {
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    private String apiKey;
    private Module module;
    private Selection selection;

    public static TornClient create(String apiKey){
        INSTANCE = new TornClient();
        INSTANCE.apiKey = apiKey;
        return INSTANCE;
    }

    public TornClient module(Module m){
        this.module = m;
        return this;
    }

    public TornClient selection(Selection s){
        this.selection = s;
        return this;
    }

    @SneakyThrows
    public <T> T execute(Class<T> valueType) {
        String endpoint = String.format(
                ENDPOINT_TEMPLATE,
                BASE_URL,
                this.module.urlComponent,
                this.selection.urlComponent,
                this.apiKey
        );
        log.info("executing call {}",endpoint);
        return OBJECT_MAPPER.readValue(execute(endpoint),valueType);
    }

    public enum Module {
        USER("user", List.of(Selection.EVENTS, Selection.BASIC));

        private String urlComponent;
        private List<Selection> selections;

        Module(final String url, final List<Selection> selections){
            this.urlComponent = url;
            this.selections = selections;
        }
    }

    public enum Selection {
        EVENTS("events"),
        BASIC(" basic");

        private String urlComponent;

        Selection(String s){
            this.urlComponent = s;
        }
    }

    @SneakyThrows
    private static String execute(String endpoint){
        URL url = new URL(endpoint);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }
}
