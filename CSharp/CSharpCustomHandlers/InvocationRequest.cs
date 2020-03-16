using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace CSharpCustomHandlers
{
    public class InvocationRequest
    {
            public IDictionary<string, object> Data { get; set; } = new Dictionary<string, object>();

            public IDictionary<string, object> Metadata { get; set; } = new Dictionary<string, object>();
    }
}
