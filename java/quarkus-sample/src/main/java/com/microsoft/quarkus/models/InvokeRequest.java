package com.microsoft.quarkus.models;

import java.util.Map;

public class InvokeRequest {

    private Map<String, Object> data;
    Map<String, Object> metadata;

    public InvokeRequest(Map<String, Object> data, Map<String, Object> metadata) {
        this.data = data;
        this.metadata = metadata;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }
}
