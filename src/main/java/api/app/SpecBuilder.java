package api.app;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static api.app.applicationApi.Routes.*;

public class SpecBuilder {


    public static RequestSpecification getRequestSpec() {
        return new RequestSpecBuilder().
                setBaseUri(BASE_URI_SPOTIFY).
                setBasePath(BASE_PATH).
                setContentType(ContentType.JSON).
                addFilter(new AllureRestAssured()).
                log(LogDetail.ALL).
                build();
    }

    public static RequestSpecification getAccountRequestSpec() {
        return new RequestSpecBuilder().
                setBaseUri(BASE_URI_ACCOUNTS).
                setBasePath(API + TOKEN).
                setContentType(ContentType.URLENC).
                addFilter(new AllureRestAssured()).
                log(LogDetail.ALL).
                build();
    }

    public static ResponseSpecification getResponseSpec() {
        return  new ResponseSpecBuilder().
                log(LogDetail.ALL).build();
    }
}
