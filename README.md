# Azure Functions custom handlers (preview)

Every Functions app is executed by a language-specific handler. While Azure Functions supports many [language handlers](https://docs.microsoft.com/azure/azure-functions/supported-languages) by default, there are cases where you may want additional control over the app execution environment. Custom handlers give you this additional control.

Custom handlers are lightweight web servers that receive events from the Functions host. Any language that supports HTTP primitives can implement a custom handler.

Custom handlers are best suited for situations where you want to:

- Implement a Functions app in a language beyond the officially supported languages
- Implement a Functions app in a language version or runtime not supported by default
- Have granular control over the app execution environment

With custom handlers, all [triggers and input and output bindings](https://docs.microsoft.com/azure/azure-functions/functions-triggers-bindings) are supported via [extension bundles](https://docs.microsoft.com/azure/azure-functions/functions-bindings-register).

Read more [about custom handlers in detail](https://docs.microsoft.com/azure/azure-functions/functions-custom-handlers).

## Samples

The following samples demonstrate how to implement a custom handler in the following languages:

- [C#](https://github.com/Azure-Samples/functions-custom-handlers/tree/master/CSharp)
- [Go](https://github.com/Azure-Samples/functions-custom-handlers/tree/master/go)
- [Java](https://github.com/Azure-Samples/functions-custom-handlers/tree/master/Java)
- [JavaScript](https://github.com/Azure-Samples/functions-custom-handlers/tree/master/node)
- [R](https://github.com/Azure-Samples/functions-custom-handlers/tree/master/R)
- [Rust](https://github.com/Azure-Samples/functions-custom-handlers/tree/master/Rust)
