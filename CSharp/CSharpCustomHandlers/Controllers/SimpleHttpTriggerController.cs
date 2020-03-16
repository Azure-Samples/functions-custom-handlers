using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;

namespace CSharpCustomHandlers.Controllers
{
    [Route("[controller]")]
    [ApiController]
    public class SimpleHttpTriggerController : ControllerBase
    {
        private ILogger<SimpleHttpTriggerController> _logger = null;
        public SimpleHttpTriggerController(ILogger<SimpleHttpTriggerController> logger)
        {
            _logger = logger;
        }

        [HttpGet]
        public ActionResult<string> Get()
        {
            return "hello from c# worker";
        }

        [HttpPost]
        public ActionResult<string> Post(string value)
        {
            var invocationRequest = this.HttpContext;
            _logger.LogInformation($"queryparam:{invocationRequest.Request.Query["name"]}");
            return "HelloWorld from c# worker";
        }
    }
}