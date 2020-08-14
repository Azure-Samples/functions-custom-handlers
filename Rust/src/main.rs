use hyper::service::{make_service_fn, service_fn};
use hyper::{Body, Error, Request, Response, Server};
use hyper::{Method, StatusCode};
use serde::{Deserialize, Serialize};
use std::collections::HashMap;
use std::convert::Infallible;
use std::env;

async fn handler(req: Request<Body>) -> Result<Response<Body>, Infallible> {
    let response = match (req.method(), req.uri().path()) {
        (&Method::GET, "/SimpleHttpTrigger") => http_trigger(req).await,
        (&Method::POST, "/BlobTrigger") => blob_trigger(req).await,
        _ => Response::builder()
            .status(StatusCode::NOT_FOUND)
            .body(Body::from(""))
            .unwrap(),
    };

    Ok(response)
}

async fn http_trigger(_req: Request<Body>) -> Response<Body> {
    let builder = Response::builder();
    builder
        .body(Body::from("Hello World from rust http worker"))
        .unwrap()
}

async fn blob_trigger(req: Request<Body>) -> Response<Body> {
    let builder = Response::builder();
    let b = hyper::body::to_bytes(req.into_body()).await.unwrap();
    let r: FnRequest = serde_json::from_slice(&b.to_vec()).unwrap();
    let base64_blob_contents = r.data.get("triggerBlob").unwrap();
    let blob_contents = base64::decode(base64_blob_contents).unwrap();
    let str_blob_contents = std::str::from_utf8(&blob_contents).unwrap();

    println!("Contents {}", str_blob_contents);

    let response = FnResponse {
        outputs: None,
        return_value: Some(str_blob_contents.to_string()),
    };

    builder
        .status(200)
        .header("Content-Type", "application/json")
        .body(Body::from(serde_json::to_string(&response).unwrap()))
        .unwrap()
}

#[tokio::main]
async fn main() -> Result<(), Error> {
    let port_key = "FUNCTIONS_HTTPWORKER_PORT";
    let port: u16 = match env::var(port_key) {
        Ok(val) => val.parse().expect("worker port is not a number!"),
        Err(_) => 3000,
    };
    let addr = ([127, 0, 0, 1], port).into();

    let server = Server::bind(&addr).serve(make_service_fn(|_| async move {
        Ok::<_, Infallible>(service_fn(handler))
    }));

    println!("Listening on http://{}", addr);

    server.await
}

#[derive(Debug, Deserialize)]
struct FnRequest {
    #[serde(rename = "Data")]
    data: HashMap<String, String>,
    #[serde(rename = "Metadata")]
    metadata: HashMap<String, String>,
}

#[derive(Debug, Serialize)]
struct FnResponse {
    #[serde(rename = "Outputs")]
    outputs: Option<HashMap<String, String>>,
    #[serde(rename = "ReturnValue")]
    return_value: Option<String>,
}
