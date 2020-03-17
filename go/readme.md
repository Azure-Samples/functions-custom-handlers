
# Azure Functions custom handler in Go

The samples available in this folder demonstrate how to implement a [custom handler](https://docs.microsoft.com/azure/azure-functions/functions-custom-handlers) in Go.

Example functions featured in this repo include:

| Name | Trigger | Input | Output |
|------|---------|-------|--------|
| [BlobTrigger](./blobtrigger) | Blob Storage | Blob Storage | Blob Storage|
| [HttpTriggerStringReturnValue](./httptriggerstringreturnvalue) | HTTP | n/a | Queue Storage |
| [HttpTriggerWithOutputs](./httptriggerwithoutputs)| HTTP | n/a | Queue Storage |
| [QueueTrigger](./queuetrigger) | Queue Storage | Queue Storage | Queue Storage |
| [QueueTriggerWithOutputs](./queuetriggerwithoutputs) | Queue Storage | Queue Storage | Queue Storage |
| [SimpleHttpTrigger](./simplehttptrigger) | HTTP | n/a   | n/a |
| [SimpleHttpTriggerWithReturn](./SimpleHttpTriggerWithReturn) | HTTP | Event Hub | n/a |

## Configuration

The *local.settings-example.json* is provided to show what values the app is expecting to read from environment variables. Make a copy of *local.settings-example.json* and rename it *local.settings.json* and replace any values that begin with "**YOUR_**" with your values.
