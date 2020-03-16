var express = require('express');
var app = express();
app.use(express.json());

const port = process.env.FUNCTIONS_HTTPWORKER_PORT;

app.all('/SimpleHttpTrigger', function (req, res) {
   console.log(`Got a request for the homepage. Query Params are:`);
   for (const key in req.query) {
      console.log(key, req.query[key])
   }
   res.send('Hello');
})

app.all('/SimpleHttpTriggerWithReturn', function (req, res) {
   console.log(`Got a POST request for the homepage. Query Params are:`);
   for (const key in req.query) {
      console.log(key, req.query[key])
   }
   res.send('Hello POST with return');
})

app.post('/QueueTrigger', function (req, res) {
   console.log(`Got a POST request for the homepage. Query Params are:`);
   for (const key in req.query) {
      console.log(key, req.query[key])
   }
   console.log(req.body);
   res.json({ ReturnValue: 'hello queue out' })
})

app.post('/QueueTriggerWithOutputs', function (req, res) {
   console.log(`Got a POST request for the homepage. Query Params are:`);
   for (const key in req.query) {
      console.log(key, req.query[key])
   }
   console.log(req.body);
   var outputBindings = {};
   outputBindings["output1"] = "hello queue out1";
   var invocationLogs = ["invocation log1", "invocation log2", "invocation log3"];
   var invocationResponse = { Outputs: outputBindings, Logs: invocationLogs, ReturnValue: 'hello queue out' }
   console.log(JSON.stringify(invocationResponse));
   res.json(invocationResponse);
})

var server = app.listen(port, 'localhost', function () {
   console.log(`Your port is ${process.env.FUNCTIONS_HTTPWORKER_PORT}`);
   var host = server.address().address
   var port = server.address().port

   console.log("Example app listening at http://%s:%s", host, port)
})