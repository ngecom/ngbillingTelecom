package com.ngbilling.core.payload.request.util;

public class ComboReferenceInput {
    private Integer id;
    private String code;
    private String description;

    public ComboReferenceInput(){

    }
    public ComboReferenceInput(Integer id,String code,String description){
        this.id  = id;
        this.code = code;
        this.description = description;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
