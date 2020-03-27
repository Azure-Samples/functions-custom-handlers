
# Azure Functions custom handler in Java

The samples available in this folder demonstrate how to implement a [custom handler](https://docs.microsoft.com/azure/azure-functions/functions-custom-handlers) in Java.

Example functions featured in this repo include:

| Name | Trigger | Input | Output |
|------|---------|-------|--------|
| [BlobTrigger](../../../tree/master/Java/BlobTrigger) | Blob Storage | Blob Storage | Blob Storage|
| [HttpTriggerStringReturnValue](../../../tree/master/Java/HttpTriggerStringReturnValue) | HTTP | n/a | Queue Storage |
| [HttpTriggerWithOutputs](../../../tree/master/Java/HttpTriggerWithOutputs)| HTTP | n/a | Queue Storage |
| [QueueTrigger](./queuetrigger) | Queue Storage | Queue Storage | Queue Storage |
| [QueueTriggerWithOutputs](../../../tree/master/Java/QueueTriggerWithOutputs) | Queue Storage | Queue Storage | Queue Storage |
| [SimpleHttpTrigger](../../../tree/master/Java/SimpleHttpTrigger) | HTTP | n/a   | n/a |
| [SimpleHttpTriggerWithReturn](../../../tree/master/Java/SimpleHttpTriggerWithReturn) | HTTP | Event Hub | n/a |

## Configuration

The *local.settings-example.json* is provided to show what values the app is expecting to read from environment variables. Make a copy of *local.settings-example.json* and rename it *local.settings.json* and replace any values that begin with "**YOUR_**" with your values.

## Pre-reqs

- Java 8
- Maven (for packaging)

## Build + Run the Java API standalone

- Use the following configuration in your `launch.json` to run the Java API:

```json
 "configurations": [
    {
      "type": "java",
      "name": "Debug (Launch)",
      "request": "launch",
      "mainClass": "",
      "env": {
        "FUNCTIONS_HTTPWORKER_PORT": 5000
      }
    },
```

- Hit the endpoints at `http://localhost:5000/SimpleHttpTrigger`

## Run with Functions

- Package the Java api into a jar file:

```bash
mvn package -f "java/com.java/pom.xml"
```

- In a terminal, `cd` to the `functions` directory and run:

```bash
func start
```

> All being well you should see the Spring ascii logo, where the functions runtime has started the process.

- Hit the endpoints at `http://localhost:7071/api/SimpleHttpTrigger`
