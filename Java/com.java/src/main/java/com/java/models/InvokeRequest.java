package com.java.models;

import java.util.HashMap;
import java.util.Map;

public class InvokeRequest {
    public Map<String, Object> Data;
    public Map<String, Object> Metadata;

    public InvokeRequest(){
        Data = new HashMap<String, Object>();
        Metadata = new HashMap<String, Object>();
    }
}
