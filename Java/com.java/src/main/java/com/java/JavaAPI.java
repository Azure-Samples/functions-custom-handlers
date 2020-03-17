package com.java;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import com.java.models.*;

@RestController
public class JavaAPI {

  @PostMapping("/QueueTrigger")
  public InvokeResponse QueueTriggerHandler(@RequestBody InvokeRequest request){
    System.out.println("Java queue trigger handler");
    InvokeResponse resp = new InvokeResponse();
    resp.Logs.add("Java: test log1");
    resp.Logs.add("Java: test log2");
    resp.ReturnValue = "HelloWorld";
    return resp;
  }

  @PostMapping("/QueueTriggerWithOutputs")
  public InvokeResponse QueueTriggerWithOutputsHandler(@RequestBody InvokeRequest request){
    System.out.println("Java queue trigger handler");
    InvokeResponse resp = new InvokeResponse();
    resp.Logs.add("Java: test log1");
    resp.Logs.add("Java: test log2");
    resp.Outputs.put("output1", "output from Java");
    resp.ReturnValue = 100;
    return resp;
  }

  @PostMapping("/BlobTrigger")
  public InvokeResponse BlobTriggerHandler(@RequestBody InvokeRequest request){
    InvokeResponse resp = new InvokeResponse();
    resp.Logs.add("Java: test log1");
    resp.Logs.add("Java: test log2");
    resp.ReturnValue = request.Data;
    return resp;
  }

  @RequestMapping(value = {"/SimpleHttpTrigger", "/SimpleHttpTriggerWithReturn"})
  public Map<String, String> SimpleHttpTrigger(){
    System.out.println("Java: Simple Http Trigger");    

    Map<String, String> dynOutput = new HashMap<String, String>();
    dynOutput.put("home", "123-456-789");
    dynOutput.put("office", "987-654-321");

    return dynOutput;
  }

  @PostMapping(value = {"/HttpTriggerWithOutputs", "/HttpTriggerStringReturnValue"})
  public InvokeResponse HttpTriggerWithOutputs(){
    System.out.println("Java: Http Trigger with Outputs");
    InvokeResponse resp = new InvokeResponse();
    resp.ReturnValue = "return val";

    Map<String, String> dynOutput = new HashMap<String, String>();
    dynOutput.put("home", "123-456-789");
    dynOutput.put("office", "987-654-321");

    resp.Outputs.put("output1", "Mark Taylor");
    resp.Outputs.put("output2", dynOutput);

    Map<String, Object> headers = new HashMap<String, Object>();
    headers.put("header1", "header1Val");
    headers.put("header2", "header2Val");

    Map<String, Object> httpRes = new HashMap<String,Object>(); 
    httpRes.put("statusCode", "201");
    httpRes.put("body", "my world");
    httpRes.put("headers", headers);
    
    resp.Outputs.put("res", httpRes);
    return resp;
  }
}