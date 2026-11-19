
# MySQL Troubleshooting

## Database Connection

The Newsagent application uses MySQL to store data.

Connection settings are configured in:
src/main/java/com/newsagent/db/DatabaseConnection.java

## Error: Unknown Database

If MySQL reports "Unknown database newsagentdb":

1. Open MySQL Workbench.
2. Check the available databases using SHOW DATABASES.
3. If newsagentdb is missing, create it using the project schema.sql file.
4. Run the project sample_data.sql file if sample records are needed.

## Error: Access Denied

1. Check the MySQL username.
2. Check the password.
3. Check that the correct MySQL server is running.
4. Confirm the port matches DatabaseConnection.java.

## Error: Connection Refused

1. Check that MySQL is running.
2. Confirm the hostname and port.
3. Restart the MySQL service if necessary.
4. Test the connection in MySQL Workbench.

## Testing the Database

Run these queries:

```sql
USE newsagentdb;
SHOW TABLES;
SELECT * FROM DeliveryAreaTable;
SELECT * FROM DeliveryPersonTable;
```
