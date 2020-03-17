using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;

namespace CSharpCustomHandlers.Controllers
{
    [Route("[controller]")]
    [ApiController]
    public class EventHubTriggerController : ControllerBase
    {
        private ILogger<EventHubTriggerController> _logger = null;

        public EventHubTriggerController(ILogger<EventHubTriggerController> logger)
        {
            _logger = logger;
        }

        [HttpGet]
        public ActionResult<string> Get()
        {
            return "hello from c# handler worker";
        }

        [HttpPost]
        public ActionResult Post([FromBody]InvocationRequest value)
        {
            foreach (var data in value.Data.Keys)
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