
# Azure Functions custom handler in Go

The samples available in this folder demonstrate how to implement a [custom handler](https://docs.microsoft.com/azure/azure-functions/functions-custom-handlers) in Go.

Example functions featured in this repo include:

| Name | Trigger | Input | Output |
|------|---------|-------|--------|
| [BlobTrigger](../../../tree/master/go/BlobTrigger) | Blob Storage | Blob Storage | Blob Storage|
| [HttpTriggerWithStreaming](../../../tree/master/go/HttpTriggerWithStreaming) | HTTP | n/a | n/a |
| [QueueTrigger](../../../tree/master/go/QueueTrigger) | Queue Storage | Queue Storage | Queue Storage |
| [QueueTriggerWithOutputs](../../../tree/master/go/QueueTriggerWithOutputs) | Queue Storage | Queue Storage | Queue Storage |
| [SimpleHttpTrigger](../../../tree/master/go/SimpleHttpTrigger) | HTTP | n/a   | n/a |

## Configuration

The *local.settings-example.json* is provided to show what values the app is expecting to read from environment variables. Make a copy of *local.settings-example.json* and rename it *local.settings.json* and replace any values that begin with "**YOUR_**" with your values.

## Building the go Executable

Run the following in the root of the sample (where your `GoCustomHandlers.go` file is located).

```
go build -o GoCustomHandlers.exe GoCustomHandlers.go
```

If you need a Linux executable (and are working in a Windows environment) you can use the following:

```
$env:GOOS = "linux"
$env:GOARCH = "amd64"
cd GoCustomHandlers
go build -o GoCustomHandlers GoCustomHandlers.go
```
