use hyper::service::{make_service_fn, service_fn};
use hyper::{Body, Error, Request, Response, Server};
use hyper::{Method, StatusCode};
use std::convert::Infallible;
use std::env;

async fn handler(req: Request<Body>) -> Result<Response<Body>, Infallible> {
    let builder = Response::builder();

    let response = match (req.method(), req.uri().path()) {
        (&Method::GET, "/api/SimpleHttpTrigger") => builder
            .body(Body::from("Hello World from rust Custom Handler"))
            .unwrap(),
        (&Method::POST, "/echo") => builder.body(req.into_body()).unwrap(),
        _ => builder
            .status(StatusCode::NOT_FOUND)
            .body(Body::from(""))
            .unwrap(),
    };

    Ok(response)
}

#[tokio::main]
async fn main() -> Result<(), Error> {
    let port_key = "FUNCTIONS_CUSTOMHANDLER_PORT";
    let port: u16 = match env::var(port_key) {
        Ok(val) => val.parse().expect("Custom Handler port is not a number!"),
        Err(_) => 3000,
    };
    let addr = ([127, 0, 0, 1], port).into();

    let server = Server::bind(&addr).serve(make_service_fn(|_| async move {
        Ok::<_, Infallible>(service_fn(handler))
    }));

    println!("Listening on http://{}", addr);

    server.await
}
