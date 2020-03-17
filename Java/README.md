# Azure Functions Http Worker - Java API Sample

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
> All being well you should see the Spring ascii logo, where the functions runtime has started the proces.

- Hit the endpoints at `http://localhost:7071/api/SimpleHttpTrigger`

