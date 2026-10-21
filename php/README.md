# SWIFT Code Lookup API - PHP Package

SWIFT Code Lookup identifies the bank institution, branch, city, and country code behind any SWIFT or BIC code. Search by code or query by bank name to verify matching financial institutions and branch locations.

## Installation

Install via Composer:

```bash
composer require apiverve/swiftlookup
```

## Getting Started

Get your API key at [APIVerve](https://apiverve.com)

### Basic Usage

```php
<?php

require_once 'vendor/autoload.php';

use APIVerve\Swiftlookup\Client;

// Initialize the client
$client = new Client('YOUR_API_KEY');

// Make a request
$response = $client->execute(['swift' => 'CHASUS33ARP']);

// Print the response
print_r($response);
```


### Error Handling

```php
use APIVerve\Swiftlookup\Client;
use APIVerve\Swiftlookup\Exceptions\APIException;
use APIVerve\Swiftlookup\Exceptions\ValidationException;

try {
    $response = $client->execute(['swift' => 'CHASUS33ARP']);
    print_r($response['data']);
} catch (ValidationException $e) {
    echo "Validation error: " . implode(', ', $e->getErrors());
} catch (APIException $e) {
    echo "API error: " . $e->getMessage();
    echo "Status code: " . $e->getStatusCode();
}
```

### Debug Mode

```php
// Enable debug logging
$client = new Client(
    apiKey: 'YOUR_API_KEY',
    debug: true
);
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

## Requirements

- PHP 7.4 or higher
- Guzzle HTTP client

## Documentation

For more information, visit the [API Documentation](https://docs.apiverve.com/ref/swiftlookup?utm_source=packagist&utm_medium=referral&utm_content=readme).

## Support

- Website: [https://apiverve.com/marketplace/swiftlookup?utm_source=php&utm_medium=referral&utm_content=readme](https://apiverve.com/marketplace/swiftlookup?utm_source=php&utm_medium=referral&utm_content=readme)
- Email: hello@apiverve.com

## License

This package is available under the [MIT License](LICENSE).
