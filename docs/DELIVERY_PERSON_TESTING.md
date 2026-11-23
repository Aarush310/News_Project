# Delivery Person Testing

## Validation Rules
- First name: 1 to 50 characters
- Surname: 1 to 50 characters
- Phone number: exactly 10 digits
- Delivery area: 1 to 24

## Boundary Value Tests

| Test | Input | Expected Result |
|---|---|---|
| DP01 | Delivery area 0 | Invalid |
| DP02 | Delivery area 1 | Valid |
| DP03 | Delivery area 24 | Valid |
| DP04 | Delivery area 25 | Invalid |
| DP05 | Phone number 9 digits | Invalid |
| DP06 | Phone number 10 digits | Valid |
| DP07 | Phone number 11 digits | Invalid |

## Test Results
Record the actual results after running each test.