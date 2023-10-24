package web.logger;

import io.qameta.allure.Step;

public class Logs {

    @Step("{0}")
    public static String logReporter(String message){
        return message;
    }
}
