using System;
using System.Collections.Generic;
using System.Text;
using Newtonsoft.Json;

namespace APIVerve.API.SWIFTCodeLookup
{
    /// <summary>
    /// Query options for the SWIFT Code Lookup API
    /// </summary>
    public class SWIFTCodeLookupQueryOptions
    {
        /// <summary>
        /// The SWIFT code to lookup information about. A partial code of at least 4 characters matches every code it prefixes
        /// </summary>
        [JsonProperty("swift")]
        public string Swift { get; set; }
    }
}
