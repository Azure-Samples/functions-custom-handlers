extern crate hyper;
extern crate futures;

use std::env;
use futures::future;
use hyper::{Method, StatusCode};
use hyper::{Body, Request, Response, Server};
use hyper::service::service_fn;
use hyper::rt::{self, Future};

//use std::{thread, time};

type BoxFut = Box<dyn Future<Item=Response<Body>, Error=hyper::Error> + Send>;

fn handler(req: Request<Body>) -> BoxFut {
    let mut response = Response::new(Body::empty());

    match (req.method(), req.uri().path()) {
        (&Method::GET, "/SimpleHttpTrigger") => {
            *response.body_mut() = Body::from("Hello World from rust http worker");
        },
        (&Method::POST, "/echo") => {
            *response.body_mut() = req.into_body();
        },
        _ => {
            *response.status_mut() = StatusCode::NOT_FOUND;
        },
    };

    Box::new(future::ok(response))
}

fn main() {
    let port_key = "FUNCTIONS_HTTPWORKER_PORT";
    let port: u16 = match env::var_os(port_key) {
        Some(val) => val.into_string().unwrap().parse().expect("worker port is not a number!"),
        None => 3000
    };
    let addr = ([127, 0, 0, 1], port).into();

    //thread::sleep(time::Duration::from_millis(20));

    println!("Starting server on http://{}", addr);
    let server = Server::bind(&addr)
        .serve(|| service_fn(handler))
        .map_err(|e| eprintln!("server error: {}", e));

    println!("Listening on http://{}", addr);
    rt::run(server);
}