using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore;
using Microsoft.AspNetCore.Hosting;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.Logging;

namespace CSharpCustomHandlers
{
    public class Program
    {
        public static string FUNCTIONS_HTTPWORKER_PORT = Environment.GetEnvironmentVariable("FUNCTIONS_HTTPWORKER_PORT") ?? "9080";
        public static void Main(string[] args)
        {
            CreateWebHostBuilder(args).Build().Run();
        }

        public static IWebHostBuilder CreateWebHostBuilder(string[] args) =>
            WebHost.CreateDefaultBuilder(args)
                .UseStartup<Startup>()
                .UseUrls($"http://localhost:{FUNCTIONS_HTTPWORKER_PORT}");
    }
}
