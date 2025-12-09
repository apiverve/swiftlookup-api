/**
 * Basic Example - SWIFT Code Lookup API
 *
 * This example demonstrates how to use the SWIFT Code Lookup API.
 * Make sure to set your API key in the .env file or replace '[YOUR_API_KEY]' below.
 */

require('dotenv').config();
const swiftlookupAPI = require('../index.js');

// Initialize the API client
const api = new swiftlookupAPI({
    api_key: process.env.API_KEY || '[YOUR_API_KEY]'
});

// Example query
var query = {
  swift: "CHASUS33ARP"
};

// Make the API request using callback
console.log('Making request to SWIFT Code Lookup API...\n');

api.execute(query, function (error, data) {
    if (error) {
        console.error('Error occurred:');
        if (error.error) {
            console.error('Message:', error.error);
            console.error('Status:', error.status);
        } else {
            console.error(JSON.stringify(error, null, 2));
        }
        return;
    }

    console.log('Response:');
    console.log(JSON.stringify(data, null, 2));
});
