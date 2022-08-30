package com.exadel.ipromise.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Addiction {

    @JsonProperty("addiction_id")
    Long addictionId;

    @JsonProperty("name_addiction")
    String nameAddiction;

    public Addiction() {
    }

    public Addiction(Long addictionId, String addiction) {
        this.addictionId = addictionId;
        this.nameAddiction = addiction;
    }

    public Long getAddictionId() {
        return addictionId;
    }

    public void setAddictionId(Long addictionId) {
        this.addictionId = addictionId;
    }

    public String getNameAddiction() {
        return nameAddiction;
    }

    public void setNameAddiction(String nameAddiction) {
        this.nameAddiction = nameAddiction;
    }

}
