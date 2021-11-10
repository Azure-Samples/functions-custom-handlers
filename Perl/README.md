# Azure Functions custom handler in Perl

The samples available in this folder demonstrate how to implement a [custom handler](https://docs.microsoft.com/azure/azure-functions/functions-custom-handlers).

Example functions featured in this repo include:

| Name | Trigger | Input | Output |
|------|---------|-------|--------|
| [SimpleHttpTrigger](../../../tree/master/Perl/SimpleHttpTrigger) | HTTP | n/a   | n/a |

## Configuration

The *local.settings-example.json* is provided to show what values the app is expecting to read from environment variables. Make a copy of *local.settings-example.json* and rename it *local.settings.json* and replace any values that begin with "**YOUR_**" with your values.

## Pre-reqs

- Perl (Strawberry) : https://strawberryperl.com/
    - Put the Strawberry packages which is included in perl.exe to under `Strawberry` folder after download.
    - Windows **ONLY**.
- Azure Functions Core Tools
