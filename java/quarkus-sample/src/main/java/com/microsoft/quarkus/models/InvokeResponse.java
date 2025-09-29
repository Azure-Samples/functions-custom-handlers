package com.microsoft.quarkus.models;

import java.util.List;
import java.util.Map;

public class InvokeResponse {

    private Map<String, Object> outputs;
    private List<String> logs;
    private Object returnValue;

    public InvokeResponse(Map<String, Object> outputs, List<String> logs, Object returnValue) {
        this.outputs = outputs;
        this.logs = logs;
        this.returnValue = returnValue;
    }

    public Map<String, Object> getOutputs() {
        return outputs;
    }

    public void setOutputs(Map<String, Object> outputs) {
        this.outputs = outputs;
    }

    public List<String> getLogs() {
        return logs;
    }

    public void setLogs(List<String> logs) {
        this.logs = logs;
    }

    public Object getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(Object returnValue) {
        this.returnValue = returnValue;
    }
}
