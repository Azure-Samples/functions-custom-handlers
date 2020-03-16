using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using CSharpCustomHandlers;

namespace CSharpCustomHandlers.Controllers
{
    [Route("[controller]")]
    [ApiController]
    public class QueueTriggerController : ControllerBase
    {
        private ILogger<QueueTriggerController> _logger = null;
        public QueueTriggerController(ILogger<QueueTriggerController> logger)
        {
            _logger = logger;
        }

        // GET api/values/5
        [HttpGet]
        public ActionResult<string> Get()
        {
            return "hello queue from c# worker";
        }

        // POST api/values
        [HttpPost]
        public ActionResult Post([FromBody]InvocationRequest value)
        {
            foreach(var data in value.Data.Keys)
            {
                _logger.LogInformation($"data:{data} value:{value.Data[data]}");
            }

            foreach (var metadadata in value.Metadata.Keys)
            {
                _logger.LogInformation($"data:{metadadata} value:{value.Metadata[metadadata]}");
            }
            InvocationResult invocationResult = new InvocationResult()
            {
                ReturnValue = "HelloWorld from c# worker"
            };
            return new JsonResult(invocationResult);
        }
    }
}