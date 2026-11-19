# Newsagent Application

Architecture, as on the lecturer's board: `XxxCLI` (menu) → `Xxx` (rules) → `XxxDAO` (SQL) → `DatabaseConnection` → MySQL.

| Person | Entity | Classes | Table |
| --- | --- | --- | --- |
| 1 | Customer | Customer, CustomerCLI, CustomerDAO | CustomerTable |
| 2 | Order | Order, OrderCLI, OrderDAO | OrderTable |
| 3 | Publication | Publication, PublicationCLI, PublicationDAO | PublicationTable |
| 4 | Delivery Area | DeliveryArea, DeliveryAreaCLI, DeliveryAreaDAO | DeliveryAreaTable |
| - | Delivery Docket | DeliveryDocket, DeliveryDocketCLI, DeliveryDocketDAO | DeliveryDocketTable |
| - | Bill | Bill, BillLine, BillCLI, BillDAO | (calculated from OrderTable and PublicationTable) |
| - | Delivery Person | DeliveryPerson, DeliveryPersonCLI, DeliveryPersonDAO | DeliveryPersonTable |
| - | Newsagent | Newsagent, NewsagentCLI, NewsagentDAO | NewsagentTable |

`MasterCLI` is the main menu: 1. Customers, 2. Orders, 3. Publications, 4. Delivery Areas, 5. Delivery Dockets, 6. Extra Features, 7. Monthly Bills, 8. Delivery Persons, 9. Newsagent Account, 10. Exit. Each entity menu has Add, Find By ID, Update, Delete and List All, and its Exit comes back to the main menu.

All eight entities use one database (`newsagentdb`) and one shared `DatabaseConnection`.

## Project structure

```
sql/schema.sql                         creates newsagentdb and all 7 tables (run once)
sql/sample_data.sql                    realistic test data: areas, customers, publications, orders, dockets
src/main/java/com/newsagent/
    model/   Customer, Order, Publication, DeliveryArea, DeliveryDocket, Bill, DeliveryPerson, Newsagent
    dao/     CustomerDAO, OrderDAO, PublicationDAO, ...      (SQL)
    cli/     CustomerCLI, OrderCLI, PublicationCLI, ...      (menus, run these)
    db/      DatabaseConnection                              (shared connection)
src/test/java/com/newsagent/model/     JUnit 5 tests, one per entity
tdd/product-skeleton/                  product skeletons for the TDD "all tests fail" step
docs/                                  test designs and class documents
```

## Run it (VS Code)

1. Install the **Extension Pack for Java** (Microsoft) and a JDK (17 or newer).
2. Run `sql/schema.sql` in MySQL Workbench, then `sql/sample_data.sql` for test data.
3. File → Open Folder → this project folder. VS Code imports it as a Maven project from `pom.xml` (JUnit 5 and the MySQL driver download automatically the first time).
4. In `src/main/java/com/newsagent/db/DatabaseConnection.java` put your own MySQL username and password. Do not commit your real password.
5. Open `src/main/java/com/newsagent/cli/MasterCLI.java` and press Run above `main` (or run any single entity CLI). Add customers and publications before orders: an order needs an existing customer and publication.
6. Run the tests from the Testing panel (beaker icon).
7. Check the rows in MySQL Workbench, e.g. `SELECT * FROM PublicationTable;`

If VS Code shows old errors after pulling: Ctrl+Shift+P → `Java: Clean Java Language Server Workspace` → Restart and delete.

## Product skeleton + JUnit (TDD)

1. Copy `tdd/product-skeleton/Xxx.java` over `src/main/java/com/newsagent/model/Xxx.java`.
2. Run `XxxTest`: every test fails ("No Product Written Yet"). Screenshot it.
3. Source Control → right-click `Xxx.java` → Discard Changes, run `XxxTest` again: all pass. Screenshot it.

## Demo checklist

| Requirement | Where to show it |
| --- | --- |
| 100% equivalence partitioning and boundary value analysis | Run all tests in the Testing panel: 280 JUnit tests pass. Coverage per rule: `docs/Test Design - EP and BVA.xlsx` (one EP-BVA sheet per entity). |
| Show order IDs by your name (two tables connected) | `MasterCLI` → 2. Order Management → 3. Find Orders By Customer Name, type your name. In Workbench: run `sql/orders_by_customer_name.sql` (`CustomerTable JOIN OrderTable`). |
| SDLC justification | `docs/SDLC Justification.docx` (Scrum, compared with Waterfall, V-Model, Iterative, Incremental, Spiral). |
| UAT (user acceptance tests) | `docs/Test Design - EP and BVA.xlsx`, UAT sheet: 33 tests run end to end through MasterCLI against MySQL, all pass. |
| User stories and test design | `docs/User Stories.xlsx` (US1-US32 with acceptance criteria) and `docs/Test Design - EP and BVA.xlsx`. |

## GitHub

Work on a branch and open a pull request. Commit in TDD order so the history shows it: skeleton → black-box tests (failing) → product class → white-box tests → DAO → CLI → SQL table. Start each message with the story number, e.g. `US9: add Publication product skeleton`.

## Main Menu

The Newsagent application provides the following options:

1. Customer Management
2. Order Management
3. Publication Management
4. Delivery Area Management
5. Delivery Docket Management
6. Monthly Bills
7. Delivery Persons
8. Newsagent Account
9. Exit

The main menu is controlled by MasterCLI.java.
Each option opens the corresponding management menu.