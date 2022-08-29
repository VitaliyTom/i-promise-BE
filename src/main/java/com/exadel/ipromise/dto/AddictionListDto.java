package com.exadel.ipromise.dto;

public class AddictionListDto {

    Long addictionId;

    String addiction;

    public AddictionListDto() {
    }

    public AddictionListDto(Long addictionId, String addiction) {
        this.addictionId = addictionId;
        this.addiction = addiction;
    }

    public Long getAddictionId() {
        return addictionId;
    }

    public void setAddictionId(Long addictionId) {
        this.addictionId = addictionId;
    }

    public String getAddiction() {
        return addiction;
    }

    public void setAddiction(String addiction) {
        this.addiction = addiction;
    }

}
