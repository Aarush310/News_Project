# Newsagent Application: Create checkpoint (4 entities)

Architecture, as on the lecturer's board: `XxxCLI` (menu) → `Xxx` (rules) → `XxxDAO` (SQL) → `DatabaseConnection` → MySQL.

| Person | Entity | Classes | Table |
| --- | --- | --- | --- |
| 1 | Customer | Customer, CustomerCLI, CustomerDAO | CustomerTable |
| 2 | Order | Order, OrderCLI, OrderDAO | OrderTable |
| 3 | Publication | Publication, PublicationCLI, PublicationDAO | PublicationTable |
| 4 | Delivery Area | DeliveryArea, DeliveryAreaCLI, DeliveryAreaDAO | DeliveryAreaTable |

All four entities use one database (`newsagentdb`) and one shared `DatabaseConnection`.

## Project structure

```
sql/schema.sql                         creates newsagentdb and all 4 tables (run once)
src/main/java/com/newsagent/
    model/   Customer, Order, Publication, DeliveryArea      (rules)
    dao/     CustomerDAO, OrderDAO, PublicationDAO, ...      (SQL)
    cli/     CustomerCLI, OrderCLI, PublicationCLI, ...      (menus, run these)
    db/      DatabaseConnection                              (shared connection)
src/test/java/com/newsagent/model/     JUnit 5 tests, one per entity
tdd/product-skeleton/                  product skeletons for the TDD "all tests fail" step
docs/                                  test designs and class documents
```

## Run it (VS Code)

1. Install the **Extension Pack for Java** (Microsoft) and a JDK (17 or newer).
2. Run `sql/schema.sql` in MySQL Workbench.
3. File → Open Folder → this project folder. VS Code imports it as a Maven project from `pom.xml` (JUnit 5 and the MySQL driver download automatically the first time).
4. In `src/main/java/com/newsagent/db/DatabaseConnection.java` put your own MySQL username and password. Do not commit your real password.
5. Open any CLI in `src/main/java/com/newsagent/cli/` and press Run above `main`. Add customers and publications before orders: an order needs an existing customer and publication.
6. Run the tests from the Testing panel (beaker icon).
7. Check the rows in MySQL Workbench, e.g. `SELECT * FROM PublicationTable;`

If VS Code shows old errors after pulling: Ctrl+Shift+P → `Java: Clean Java Language Server Workspace` → Restart and delete.

## Product skeleton + JUnit (TDD)

1. Copy `tdd/product-skeleton/Xxx.java` over `src/main/java/com/newsagent/model/Xxx.java`.
2. Run `XxxTest`: every test fails ("No Product Written Yet"). Screenshot it.
3. Source Control → right-click `Xxx.java` → Discard Changes, run `XxxTest` again: all pass. Screenshot it.

## GitHub

Work on a branch and open a pull request. Commit in TDD order so the history shows it: skeleton → black-box tests (failing) → product class → white-box tests → DAO → CLI → SQL table. Start each message with the story number, e.g. `US9: add Publication product skeleton`.
