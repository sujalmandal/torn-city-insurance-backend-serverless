package s.m.torn;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;

import java.util.Map;

public class InsuranceHandler implements RequestHandler<Map<String,String>,String> {

    /* init loading */
    private static Gson jsonSerializer = new Gson();
    private static String TABLE_NAME = "Insurance";

    @Override
    public String handleRequest(Map<String, String> requestData, Context context) {
        System.out.println(String.format("requestData : %s", requestData));
        return "okay";
    }
}
