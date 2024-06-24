package com.example.project.beans.model;

import java.util.List;
import java.util.Map;

import com.example.project.beans.model.response.Template;

public class ResponseModel {
    private String version;
    private Template template;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }
}

