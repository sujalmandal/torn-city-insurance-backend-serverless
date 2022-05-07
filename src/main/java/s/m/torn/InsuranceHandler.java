package s.m.torn;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.Map;

public class InsuranceHandler implements RequestHandler<Map<String,String>,String> {

    @Override
    public String handleRequest(Map<String, String> requestData, Context context) {
        System.out.println(String.format("requestData : %s", requestData));
        return "okay";
    }
}
