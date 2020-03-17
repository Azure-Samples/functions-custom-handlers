# Azure Functions custom handler in JavaScript

The samples available in this folder demonstrate how to implement a [custom handler](https://docs.microsoft.com/azure/azure-functions/functions-custom-handlers) in JavaScript.

Example functions featured in this repo include:

| Name | Trigger | Input | Output |
|------|---------|-------|--------|
| [QueueTrigger](./queuetrigger) | Queue Storage | Queue Storage | Queue Storage |
| [QueueTriggerWithOutputs](./queuetriggerwithoutputs) | Queue Storage | Queue Storage | Queue Storage |
| [SimpleHttpTrigger](./simplehttptrigger) | HTTP | n/a   | n/a |
| [SimpleHttpTriggerWithReturn](./SimpleHttpTriggerWithReturn) | HTTP | Event Hub | n/a |

## Configuration

The *local.settings-example.json* is provided to show what values the app is expecting to read from environment variables. Make a copy of *local.settings-example.json* and rename it *local.settings.json* and replace any values that begin with "**YOUR_**" with your values.
