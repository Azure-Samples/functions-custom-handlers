# Azure Functions custom handler in Rust

The samples available in this folder demonstrate how to implement a [custom handler](https://docs.microsoft.com/azure/azure-functions/functions-custom-handlers) in Rust.

Example functions featured in this repo include:

| Name | Trigger | Input | Output |
|------|---------|-------|--------|
| [SimpleHttpTrigger](../../../tree/master/Rust/SimpleHttpTrigger) | HTTP | n/a   | n/a |

## Configuration

The *local.settings-example.json* is provided to show what values the app is expecting to read from environment variables. Make a copy of *local.settings-example.json* and rename it *local.settings.json* and replace any values that begin with "**YOUR_**" with your values.

## Pre-reqs

- Rust : https://www.rust-lang.org/learn/get-started
- Azure Functions Core Tools

## Build + Run

- run `cargo build`
- run `func host start`

**NOTE**: if running on a non-Windows platform you will have to remove ".exe" from the `defaultExecutablePath` in the `host.json`.
