package at.wheretheiss.apitest.stepdefinitions;

import at.wheretheiss.apitest.dto.ResponseData;
import at.wheretheiss.apitest.properties.FileProperties;
import at.wheretheiss.apitest.properties.UrlProperties;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GetTleData {

    private static final Logger logger = LoggerFactory.getLogger(GetTleData.class);

    @Autowired
    private ResponseData responseData;

    @Autowired
    private UrlProperties urlProperties;

    @Autowired
    private FileProperties fileProperties;

    @When("a get request is sent to the API with {string} {int}")
    public void sendRequestWithDefaultFormatOption(String field, int id) {
        ValidatableResponse validatableResponse = RestAssured.given()
                .pathParam(field, id)
                .baseUri(urlProperties.getBase())
                .basePath(urlProperties.getTlesPath())
                .get().then();
        validatableResponse.extract().response().getBody().prettyPrint();
        responseData.setResponse(validatableResponse);
    }

    @When("a get request is sent to the API with {string} {int} and {string} {string}")
    public void sendRequestWithFormatParameter(String field, int id, String formatParam, String format) {
        ValidatableResponse validatableResponse = RestAssured.given().pathParam(field, id)
                .baseUri(urlProperties.getBase())
                .basePath(urlProperties.getTlesPath())
                .queryParam(formatParam, format)
                .when().get().then();
        validatableResponse.extract().response().getBody().prettyPrint();
        responseData.setResponse(validatableResponse);
    }

    @Then("API returns a response with response code {int}")
    public void verifyResponseCode(int expectedResponseCode) {
        int actualStatusCode = responseData.getResponse().extract().statusCode();
        logger.info("Status  code: " + actualStatusCode);
        Assert.assertEquals(expectedResponseCode, actualStatusCode);
    }

    @And("response content-type is {string}")
    public void verifyResponseContentType(String expectedContentType) {
        String actualContentType = responseData.getResponse().extract().contentType();
        logger.info("Content type: " + actualContentType);
        Assert.assertEquals(expectedContentType, actualContentType);
    }

    @And("response schema matches with {string}")
    public void verifyResponseSchema(String schemaFile) throws IOException {
        String expectedJsonSchema = Files.readString(
                Paths.get(fileProperties.getSchemaPath()).resolve(schemaFile.concat(".txt")));
        logger.debug("Expected schema: " + expectedJsonSchema);
        responseData.getResponse().assertThat().body(JsonSchemaValidator.matchesJsonSchema(expectedJsonSchema));
    }

    @And("response contains field {string} and value {int}")
    public void verifyIntField(String field, int expectedValue) {
        int actualValue;
        if (responseData.getResponse().extract().path(field) instanceof String) {
            actualValue = Integer.parseInt(responseData.getResponse().extract().path(field));
        } else {
            actualValue = responseData.getResponse().extract().path(field);
        }
        logger.info(field + ": " + "int value: " + actualValue);
        Assert.assertEquals(expectedValue, actualValue);
    }

    @And("response contains field {string} and value {string}")
    public void verifyStringField(String field, String expectedValue) {
        String actualValue = responseData.getResponse().extract().path(field);
        logger.info(field + ": " + "String value: " + actualValue);
        Assert.assertEquals(expectedValue, actualValue);
    }

    @And("response contains {string}")
    public void verifyResponseContent(String content) {
        Assert.assertTrue(responseData.getResponse()
                .extract()
                .response()
                .getBody()
                .prettyPrint()
                .contains(content));
    }
}
