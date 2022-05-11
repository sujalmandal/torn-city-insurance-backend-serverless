package s.m.torn;

import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@Getter
@ToString
public class EventResponse {

    Map<String, Data> events;

    @Getter
    @ToString
    public static class Data{
        public int timestamp;
        public String event;
    }
}
