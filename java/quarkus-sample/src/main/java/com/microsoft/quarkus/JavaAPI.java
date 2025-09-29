package com.microsoft.quarkus;

import com.microsoft.quarkus.models.InvokeRequest;
import com.microsoft.quarkus.models.InvokeResponse;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class JavaAPI {

    @POST
    @Path("/QueueTrigger")
    public InvokeResponse queueTriggerHandler(InvokeRequest request) {
        System.out.println("Java queue trigger handler");
        return new InvokeResponse(null, List.of("Java: test log1", "Java: test log2"), "HelloWorld");
    }

    @POST
    @Path("/QueueTriggerWithOutputs")
    public InvokeResponse queueTriggerWithOutputsHandler(InvokeRequest request) {
        System.out.println("Java queue trigger handler");
        var logs = List.of("Java: test log1", "Java: test log2");
        var outputs = Map.<String, Object>of("output1", "output from Java");
        return new InvokeResponse(outputs, logs, 100);
    }

    @POST
    @Path("/BlobTrigger")
    public InvokeResponse blobTriggerHandler(InvokeRequest request) {
        var logs = List.of("Java: test log1", "Java: test log2");
        return new InvokeResponse(null, logs, request.getData());
    }

    @POST
    @Path("/{a:SimpleHttpTrigger|SimpleHttpTriggerWithReturn}")
    public Map<String, String> simpleHttpTrigger() {
        System.out.println("Java: Simple Http Trigger");
        return Map.of("home", "123-456-789", "office", "987-654-321");
    }

    @GET
    @Path("/{a:HttpTriggerWithOutputs|HttpTriggerStringReturnValue}")
    public InvokeResponse httpTriggerWithOutputs() {
        System.out.println("Java: Http Trigger with Outputs");

        var dynOutput = Map.of("home", "123-456-789", "office", "987-654-321");
        var headers = Map.of("header1", "header1Val", "header2", "header2Val");
        var httpRes = Map.of("statusCode", "201", "body", "my world", "headers", headers);

        var outputs = Map.of("output1", "Mark Taylor", "output2", dynOutput, "res", httpRes);
        return new InvokeResponse(outputs, null, "return val");
    }

}
