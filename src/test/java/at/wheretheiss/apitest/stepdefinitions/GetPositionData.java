package at.wheretheiss.apitest.stepdefinitions;

import at.wheretheiss.apitest.dto.PositionDto;
import at.wheretheiss.apitest.dto.ResponseData;
import at.wheretheiss.apitest.properties.UrlProperties;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.config.JsonPathConfig;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import static io.restassured.config.JsonConfig.jsonConfig;

public class GetPositionData {

    private static final Logger logger = LoggerFactory.getLogger(GetPositionData.class);

    @Autowired
    private ResponseData responseData;

    @Autowired
    private UrlProperties urlProperties;

    @When("a get request is sent to the API with following parameters")
    public void sendRequestWithParameters(DataTable dataTable) {

        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        if (rows.isEmpty()) {
            return;
        }

        RequestSpecification requestSpecification = RestAssured.given()
                .config(RestAssured.config().jsonConfig(jsonConfig().numberReturnType(JsonPathConfig.NumberReturnType.DOUBLE)))
                .pathParam("id", rows.get(0).get("id"))
                .queryParam("timestamps", rows.get(0).get("timestamps"))
                .baseUri(urlProperties.getBase())
                .basePath(urlProperties.getPositionsPath())
                .when();

        String units = rows.get(0).get("units");
        if (StringUtils.isNotEmpty(units)) {
            requestSpecification.queryParam("units", units);
        }

        ValidatableResponse validatableResponse = requestSpecification.get().then();
        validatableResponse.extract().response().getBody().prettyPrint();
        responseData.setResponse(validatableResponse);
        responseData.getPositionDtoList().clear();
        if (responseData.getResponse().extract().statusCode() == HttpStatus.SC_OK) {
            responseData.getResponse()
                    .extract()
                    .response()
                    .jsonPath()
                    .getList("$", PositionDto.class)
                    .forEach(positionDto -> responseData.getPositionDtoList().add(positionDto));
        }
    }

    @And("response array contains object with following values")
    public void responseArrayContainsObjectWithFollowingValues(DataTable dataTable) {
        dataTable.cells().stream()
                .skip(1)
                .map(PositionDto::new)
                .forEach(positionDto ->
                        Assert.assertTrue(responseData.getPositionDtoList().contains(positionDto)));
    }

    @And("response array contains {int} objects")
    public void responseArrayContainsObjects(int expectedObjectCount) {
        Assert.assertEquals(expectedObjectCount, responseData.getPositionDtoList().size());
    }
}
