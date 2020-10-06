package main

import (
	"encoding/json"
	"fmt"
	"log"
	"net/http"
	"os"
	"time"
)

type returnValue struct {
	Data string
}
type invokeResponse struct {
	Outputs     map[string]interface{}
	Logs        []string
	ReturnValue interface{}
}

type invokeResponseStringReturnValue struct {
	Outputs     map[string]interface{}
	Logs        []string
	ReturnValue string
}

type invokeRequest struct {
	Data     map[string]interface{}
	Metadata map[string]interface{}
}

func queueTriggerHandler(w http.ResponseWriter, r *http.Request) {
	var invokeReq invokeRequest
	d := json.NewDecoder(r.Body)
	decodeErr := d.Decode(&invokeReq)
	if decodeErr != nil {
		http.Error(w, decodeErr.Error(), http.StatusBadRequest)
		return
	}
	fmt.Println("The JSON data is:invokeReq metadata......")
	fmt.Println(invokeReq.Metadata)
	fmt.Println("The JSON data is:invokeReq data......")
	fmt.Println(invokeReq.Data)

	returnValue := "HelloWorld"
	invokeResp := invokeResponse{Logs: []string{"test log1", "test log2"}, ReturnValue: returnValue}

	js, err := json.Marshal(invokeResp)
	if err != nil {
		http.Error(w, err.Error(), http.StatusInternalServerError)
		return
	}

	w.Header().Set("Content-Type", "application/json")
	w.Write(js)
}

func blobTriggerHandler(w http.ResponseWriter, r *http.Request) {
	var invokeReq invokeRequest
	d := json.NewDecoder(r.Body)
	decodeErr := d.Decode(&invokeReq)
	if decodeErr != nil {
		// bad JSON or unrecognized json field
		http.Error(w, decodeErr.Error(), http.StatusBadRequest)
		return
	}
	fmt.Println("The JSON data is:invokeReq metadata......")
	fmt.Println(invokeReq.Metadata)

	returnVal := invokeReq.Data["triggerBlob"]
	invokeResp := invokeResponse{Logs: []string{"test log1", "test log2"}, ReturnValue: returnVal}

	js, err := json.Marshal(invokeResp)
	if err != nil {
		http.Error(w, err.Error(), http.StatusInternalServerError)
		return
	}

	w.Header().Set("Content-Type", "application/json")
	w.Write(js)
}

func queueTriggerWithOutputsHandler(w http.ResponseWriter, r *http.Request) {
	var invokeReq invokeRequest
	d := json.NewDecoder(r.Body)
	decodeErr := d.Decode(&invokeReq)
	if decodeErr != nil {
		// bad JSON or unrecognized json field
		http.Error(w, decodeErr.Error(), http.StatusBadRequest)
		return
	}
	fmt.Println("The JSON data is:invokeReq metadata......")
	fmt.Println(invokeReq.Metadata)
	fmt.Println("The JSON data is:invokeReq data......")
	fmt.Println(invokeReq.Data)

	returnVal := 100
	outputs := make(map[string]interface{})
	outputs["output1"] = "output from go"

	invokeResp := invokeResponse{outputs, []string{"test log1", "test log2"}, returnVal}

	js, err := json.Marshal(invokeResp)
	if err != nil {
		http.Error(w, err.Error(), http.StatusInternalServerError)
		return
	}

	w.Header().Set("Content-Type", "application/json")
	w.Write(js)
}

func httpTriggerHandler(w http.ResponseWriter, r *http.Request) {
	t := time.Now()
	fmt.Println(t.Month())
	fmt.Println(t.Day())
	fmt.Println(t.Year())
	ua := r.Header.Get("User-Agent")
	fmt.Printf("user agent is: %s \n", ua)
	invocationid := r.Header.Get("X-Azure-Functions-InvocationId")
	fmt.Printf("invocationid is: %s \n", invocationid)

	//w.Write([]byte("Hello World from go worker:pgopa"))
	returnVal := returnValue{Data: "return val"}
	outputs := make(map[string]interface{})
	outputs["output"] = "Mark Taylor"
	outputs["output2"] = map[string]interface{}{
		"home":   "123-466-799",
		"office": "564-987-654",
	}
	invokeResp := invokeResponse{outputs, []string{"test log1", "test log2"}, returnVal}

	js, err := json.Marshal(invokeResp)
	if err != nil {
		http.Error(w, err.Error(), http.StatusInternalServerError)
		return
	}

	w.Header().Set("Content-Type", "application/json")
	w.Write(js)
}

func httpTriggerHandlerStringReturnValue(w http.ResponseWriter, r *http.Request) {
	t := time.Now()
	fmt.Println(t.Month())
	fmt.Println(t.Day())
	fmt.Println(t.Year())
	ua := r.Header.Get("User-Agent")
	fmt.Printf("user agent is: %s \n", ua)
	invocationid := r.Header.Get("X-Azure-Functions-InvocationId")
	fmt.Printf("invocationid is: %s \n", invocationid)

	outputs := make(map[string]interface{})
	outputs["output"] = "Mark Taylor"
	outputs["output2"] = map[string]interface{}{
		"home":   "123-466-799",
		"office": "564-987-654",
	}
	headers := make(map[string]interface{})
	headers["header1"] = "header1Val"
	headers["header2"] = "header2Val"

	res := make(map[string]interface{})
	res["statusCode"] = "201"
	res["body"] = "my world"
	res["headers"] = headers
	outputs["res"] = res
	invokeResponse := invokeResponseStringReturnValue{outputs, []string{"test log1", "test log2"}, "Hello,World"}

	js, err := json.Marshal(invokeResponse)
	if err != nil {
		http.Error(w, err.Error(), http.StatusInternalServerError)
		return
	}

	w.Header().Set("Content-Type", "application/json")
	w.Write(js)
}

func simpleHTTPTriggerHandler(w http.ResponseWriter, r *http.Request) {
	t := time.Now()
	fmt.Println(t.Month())
	fmt.Println(t.Day())
	fmt.Println(t.Year())
	ua := r.Header.Get("User-Agent")
	fmt.Printf("user agent is: %s \n", ua)
	invocationid := r.Header.Get("X-Azure-Functions-InvocationId")
	fmt.Printf("invocationid is: %s \n", invocationid)

	queryParams := r.URL.Query()

	for k, v := range queryParams {
		fmt.Println("k:", k, "v:", v)
	}

	w.Write([]byte("Hello World from go worker"))
}

func main() {
	customHandlerPort, exists := os.LookupEnv("FUNCTIONS_CUSTOMHANDLER_PORT")
	if exists {
		fmt.Println("FUNCTIONS_CUSTOMHANDLER_PORT: " + customHandlerPort)
	}
	mux := http.NewServeMux()
	mux.HandleFunc("/HttpTriggerStringReturnValue", httpTriggerHandlerStringReturnValue)
	mux.HandleFunc("/QueueTrigger", queueTriggerHandler)
	mux.HandleFunc("/BlobTrigger", blobTriggerHandler)
	mux.HandleFunc("/QueueTriggerWithOutputs", queueTriggerWithOutputsHandler)
	mux.HandleFunc("/api/SimpleHttpTrigger", simpleHTTPTriggerHandler)
	mux.HandleFunc("/api/SimpleHttpTriggerWithReturn", simpleHTTPTriggerHandler)
	fmt.Println("Go server Listening...on FUNCTIONS_CUSTOMHANDLER_PORT:", customHandlerPort)
	log.Fatal(http.ListenAndServe(":"+customHandlerPort, mux))
}
