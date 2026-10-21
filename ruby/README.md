# SWIFT Code Lookup API - Ruby Gem

SWIFT Code Lookup identifies the bank institution, branch, city, and country code behind any SWIFT or BIC code. Search by code or query by bank name to verify matching financial institutions and branch locations.

## Installation

Add this line to your application's Gemfile:

```ruby
gem 'apiverve_swiftlookup'
```

And then execute:

```bash
$ bundle install
```

Or install it yourself as:

```bash
$ gem install apiverve_swiftlookup
```

## Getting Started

Get your API key at [APIVerve](https://apiverve.com)

### Basic Usage

```ruby
require 'apiverve_swiftlookup'

# Initialize the client
client = APIVerve::Swiftlookup::Client.new(api_key: "YOUR_API_KEY")

# Make a request
response = client.execute({ swift: "CHASUS33ARP" })

# Print the response
puts response
```


### Error Handling

```ruby
begin
  response = client.execute({ swift: "CHASUS33ARP" })
  puts response["data"]
rescue APIVerve::Swiftlookup::ValidationError => e
  puts "Validation error: #{e.errors.join(', ')}"
rescue APIVerve::Swiftlookup::APIError => e
  puts "API error: #{e.message}"
  puts "Status code: #{e.status_code}"
end
```

### Debug Mode

```ruby
# Enable debug logging
client = APIVerve::Swiftlookup::Client.new(
  api_key: "YOUR_API_KEY",
  debug: true
)
```

## Example Response

```json
{
  "status": "ok",
  "error": null,
  "data": {
    "count": 1,
    "banks": [
      {
        "bank": "JPMORGAN CHASE BANK, N.A.",
        "city": "NEW YORK,NY",
        "branch": "ACCOUNT RECONCILIATION PROCESSING",
        "swift_code": "CHASUS33ARP",
        "country": "United States",
        "country_code": "US"
      }
    ]
  }
}
```

## Documentation

For more information, visit the [API Documentation](https://docs.apiverve.com/ref/swiftlookup?utm_source=rubygems&utm_medium=referral&utm_content=readme).

## Support

- Website: [https://apiverve.com/marketplace/swiftlookup?utm_source=ruby&utm_medium=referral&utm_content=readme](https://apiverve.com/marketplace/swiftlookup?utm_source=ruby&utm_medium=referral&utm_content=readme)
- Email: hello@apiverve.com

## License

This gem is available under the [MIT License](LICENSE).
