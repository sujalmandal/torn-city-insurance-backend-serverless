
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import s.m.torn.EventResponse;
import s.m.torn.TornClient;

public class InsuranceHandlerTest {

    private String apiKey;

    @BeforeEach
    public void setup(){
        apiKey = "";
    }

    @Test
    public void testInsuranceCreate() {
        EventResponse response = TornClient.create(apiKey).module(TornClient.Module.USER)
                .selection(TornClient.Selection.EVENTS).execute(EventResponse.class);
        System.out.println(response);
    }
}
