# Newsagent Application: Create checkpoint (4 entities)

Architecture, as on the lecturer's board: `XxxCLI` (main, menu) → `Xxx` (rules) → `XxxDAO` (SQL) → `DatabaseConnection` → MySQL.

| Person | Entity | Classes | Table |
| --- | --- | --- | --- |
| 1 | Customer | Customer, CustomerCLI, CustomerDAO | CustomerTable |
| 2 | Order | Order, OrderCLI, OrderDAO | OrderTable |
| 3 | Publication | Publication, PublicationCLI, PublicationDAO | PublicationTable |
| 4 | Delivery Area | DeliveryArea, DeliveryAreaCLI, DeliveryAreaDAO | DeliveryAreaTable |

Each menu has: 1. Add, 2. Find By ID, 3. Exit. `DatabaseConnection` is shared.

## Run it (VS Code)

1. Install the **Extension Pack for Java** (Microsoft) and a JDK (21 recommended).
2. Run `sql/schema.sql` in MySQL Workbench.
3. File → Open Folder → this project folder. The `.vscode/settings.json` already sets `src` and `test` as source folders and loads every jar in `lib/`.
4. Put `mysql-connector-j-x.x.x.jar` (already included) and, for tests, `junit-platform-console-standalone-x.x.x.jar` in `lib/`. (Or click "Enable Java Tests" in the Testing panel and pick JUnit 5.)
5. In `src/DatabaseConnection.java` put your MySQL username and password.
6. Open `src/OrderCLI.java` and press Run above `main` (or F5). Add the teammates' `Customer*`, `Publication*` and `DeliveryArea*` files to `src/` first; an order needs an existing customer and publication.
7. Run the tests from the Testing panel (beaker icon) on `test/OrderTest.java`.
8. Check the rows in MySQL Workbench: `SELECT * FROM OrderTable;`

## Product skeleton + JUnit (each person, for their entity)

`tdd/Xxx/` holds the product skeleton `Xxx.java` and the black-box tests `XxxTest.java`.

1. Make a second Java project, e.g. "Customer TDD", add JUnit 5, and copy both files from `tdd/Customer/` into it.
2. Run `CustomerTest`: every test fails ("No Product Written Yet"). Screenshot it.
3. Replace the skeleton with the real `src/Customer.java` and run `test/CustomerTest.java` (black-box + white-box): all pass. Screenshot it.

## GitHub

Commit in this order so the history shows TDD: skeleton → black-box tests (failing) → product class → white-box tests → DAO → CLI → SQL table. Start each message with the story number, e.g. `US1: add Customer product skeleton`.
