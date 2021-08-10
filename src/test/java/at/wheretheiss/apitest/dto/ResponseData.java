package at.wheretheiss.apitest.dto;

import io.restassured.response.ValidatableResponse;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
public class ResponseData implements Serializable {

    private static final long serialVersionUID = 5300377549466807160L;

    private ValidatableResponse response;
    private List<PositionDto> positionDtoList;

    public List<PositionDto> getPositionDtoList() {
        if (positionDtoList == null) {
            this.positionDtoList = new ArrayList<>();
        }
        return positionDtoList;
    }

    public void setPositionDtoList(List<PositionDto> positionDtoList) {
        this.positionDtoList = positionDtoList;
    }

    public ValidatableResponse getResponse() {
        return response;
    }

    public void setResponse(ValidatableResponse response) {
        this.response = response;
    }

}
