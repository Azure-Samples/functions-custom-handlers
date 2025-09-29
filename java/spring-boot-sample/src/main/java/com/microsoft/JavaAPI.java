package com.microsoft;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.microsoft.models.InvokeRequest;
import com.microsoft.models.InvokeResponse;

import org.springframework.web.bind.annotation.*;

@RestController
public class JavaAPI {

    @PostMapping("/QueueTrigger")
    public InvokeResponse queueTriggerHandler(@RequestBody InvokeRequest request) {
        System.out.println("Java queue trigger handler");
        return new InvokeResponse(null, List.of("Java: test log1", "Java: test log2"), "HelloWorld");
    }

    @PostMapping("/QueueTriggerWithOutputs")
    public InvokeResponse queueTriggerWithOutputsHandler(@RequestBody InvokeRequest request) {
        System.out.println("Java queue trigger handler");
        var logs = List.of("Java: test log1", "Java: test log2");
        var outputs = Map.<String, Object>of("output1", "output from Java");
        return new InvokeResponse(outputs, logs, 100);
    }

    @PostMapping("/BlobTrigger")
    public InvokeResponse blobTriggerHandler(@RequestBody InvokeRequest request) {
        var logs = List.of("Java: test log1", "Java: test log2");
        return new InvokeResponse(null, logs, request.getData());
    }

    @RequestMapping(value = {"/SimpleHttpTrigger", "/SimpleHttpTriggerWithReturn"})
    public Map<String, String> simpleHttpTrigger() {
        System.out.println("Java: Simple Http Trigger");
        return Map.of("home", "123-456-789", "office", "987-654-321");
    }

    @GetMapping(value = {"/HttpTriggerWithOutputs", "/HttpTriggerStringReturnValue"})
    public InvokeResponse httpTriggerWithOutputs() {
        System.out.println("Java: Http Trigger with Outputs");

        var dynOutput = Map.of("home", "123-456-789", "office", "987-654-321");
        var headers = Map.of("header1", "header1Val", "header2", "header2Val");
        var httpRes = Map.of("statusCode", "201", "body", "my world", "headers", headers);

        var outputs = Map.of("output1", "Mark Taylor", "output2", dynOutput, "res", httpRes);
        return new InvokeResponse(outputs, null, "return val");
    }
}
