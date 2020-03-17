
# Azure Functions custom handler in C#

The samples available in this folder demonstrate how to implement a [custom handler](https://docs.microsoft.com/azure/azure-functions/functions-custom-handlers) in C#.

Example functions featured in this repo include:

| Name | Trigger | Input | Output |
|------|---------|-------|--------|
| [SimpleHttpTrigger](../../../tree/master/CSharp/SimpleHttpTrigger) | HTTP | n/a   | n/a    |
| [EventHubTrigger](../../../tree/master/CSharp/EventHubTrigger) | Event Hub | Event Hub | n/a |
| [QueueTrigger](../../../tree/master/CSharp/QueueTrigger) | Queue Storage | Queue Storage | Queue Storage|

## Configuration

The *local.settings-example.json* is provided to show what values the app is expecting to read from environment variables. Make a copy of *local.settings-example.json* and rename it *local.settings.json* and replace any values that begin with "**YOUR_**" with your values.