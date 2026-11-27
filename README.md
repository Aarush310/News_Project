# Newsagent Delivery System

A Java 17 and MySQL console application for managing a newsagent's daily deliveries, customer subscriptions, publications, delivery routes, and monthly customer billing. Built as a Software Engineering 3 group project following Scrum and Test-Driven Development (TDD).

## Tech Stack

- **Language:** Java 17
- **Build & Test:** Maven (`pom.xml`) + Maven Wrapper (`mvnw` / `mvnw.cmd`), JUnit 5 (`5.10.2`)
- **Database:** MySQL (`newsagentdb`) via JDBC (`mysql-connector-j`)
- **License:** MIT ([LICENSE](LICENSE))

---

## Architecture & Modules

The application follows a layered console architecture:

`XxxCLI` (menus & input loop via `InputHelper`) → `Xxx` (validation & business rules) → `XxxDAO` (JDBC SQL) → `DatabaseConnection` → MySQL (`newsagentdb`)

`MasterCLI` provides the top-level menu (`1`–`9`), and each entity CLI can also be run standalone:

| # | Entity | Model / Rules | CLI | DAO | MySQL Table | Menu Capabilities |
| --- | --- | --- | --- | --- | --- | --- |
| 1 | Customer | `Customer` | `CustomerCLI` | `CustomerDAO` | `CustomerTable` | Add, Find By ID, Update, Delete, List All |
| 2 | Order | `Order` | `OrderCLI` | `OrderDAO` | `OrderTable` | Add, Find By ID, Find Orders By Customer Name, List All, Update, Delete |
| 3 | Publication | `Publication` | `PublicationCLI` | `PublicationDAO` | `PublicationTable` | Add, Find By ID, Update, Delete, List All |
| 4 | Delivery Area | `DeliveryArea` | `DeliveryAreaCLI` | `DeliveryAreaDAO` | `DeliveryAreaTable` | Add, Find By ID, Update, Delete, List All |
| 5 | Delivery Docket | `DeliveryDocket` | `DeliveryDocketCLI` | `DeliveryDocketDAO` | `DeliveryDocketTable` | Create (shows deliveries due), Find By ID, Update (date/status), Delete (`PENDING` only), List All |
| 6 | Monthly Bills | `Bill`, `BillLine` | `BillCLI` | `BillDAO` | *(Calculated from `OrderTable` and `PublicationTable`)* | Generate Monthly Bill for a Customer (lookup by ID or name, year, month) |
| 7 | Delivery Person | `DeliveryPerson` | `DeliveryPersonCLI` | `DeliveryPersonDAO` | `DeliveryPersonTable` | Add, Find By ID, Update, Delete, List All |
| 8 | Newsagent Account | `Newsagent` | `NewsagentCLI` | `NewsagentDAO` | `NewsagentTable` | Add, Find By ID, Update, Delete, List All |

> Option `9` in `MasterCLI` exits the application. All other submenus return to the main menu when exiting from `MasterCLI`.

### Key Validation & Business Rules

- **Interactive Re-prompting (`InputHelper`):** Invalid menu inputs (wrong numeric type or rule violations) print a specific error message and re-prompt instead of returning to the menu.
- **Customer & Delivery Person:** First name and surname must be 1–50 characters using letters, `'`, `-`, and spaces (`[A-Za-z][A-Za-z' -]*`). Phone numbers must be exactly 10 digits. Delivery area must be `1`–`24`. Customer addresses are 1–150 characters.
- **Publication:** Unique name (2–50 characters), type (`newspaper` or `magazine`), frequency (`daily`, `weekly`, or `monthly`), price `> 0`, and stock `>= 0`.
- **Order:** Requires an existing customer ID and publication ID, 1–7 unique day codes (`MON`–`SUN`, comma-separated), and prevents duplicate orders for the same customer and publication.
- **Delivery Area:** Area number `1`–`24`, area name 1–50 characters, and must reference an existing `DeliveryPerson`.
- **Delivery Docket:** One docket per `(areaNumber, docketDate)` with status `PENDING` or `DELIVERED`. Finding or creating a docket prints all deliveries due in that area on that date (`Order.isDueOn`, where monthly publications are scheduled on their first chosen day of the month).
- **Monthly Billing (`Bill`):** Validated for years `2000`–`2100` and months `1`–`12`. Counts scheduled deliveries in the billing month using `Order.isDueOn` and totals line costs in `EUR`.
- **Newsagent Account:** Shop name (1–50 chars), address (1–150 chars), and 10-digit phone number.
- **Delete Protections:** Customers with existing orders, publications on orders, delivery areas with customers, delivery persons assigned to an area, and the last remaining newsagent account cannot be deleted. Delivered dockets (`DELIVERED`) cannot be deleted.

---

## Project Structure

```text
News_Project/
├── pom.xml                                  Maven configuration (Java 17, MySQL driver 8.4.0, JUnit 5.10.2)
├── mvnw / mvnw.cmd / .mvn/                  Maven Wrapper (no global Maven install required)
├── LICENSE                                  MIT License
├── lib/
│   ├── mysql-connector-j-26_7_0.jar         Bundled MySQL JDBC driver for direct CLI classpath runs
│   └── README.txt                           Note on manual JAR setup in lib/
├── sql/
│   ├── schema.sql                           Creates newsagentdb and all 7 tables (run first)
│   ├── sample_data.sql                      Idempotent Athlone sample dataset (INSERT IGNORE)
│   ├── orders_by_customer_name.sql          Sample JOIN query (CustomerTable JOIN OrderTable)
│   └── order_integration_tests.sql          Read-only SQL checks for orders, customers, and publications
├── src/
│   ├── main/java/com/newsagent/
│   │   ├── cli/                             MasterCLI, 8 entity CLIs, and InputHelper
│   │   ├── dao/                             8 JDBC DAO classes
│   │   ├── db/DatabaseConnection.java       Shared MySQL connection helper
│   │   └── model/                           8 entity classes + BillLine
│   └── test/java/com/newsagent/model/       JUnit 5 unit tests (288 tests across 8 test classes)
├── tdd/product-skeleton/                    TDD "all tests fail" product skeletons
├── docs/                                    SDLC justification, User Stories.xlsx, EP/BVA & UAT workbook,
│                                            TDD evidence, Workbench screenshots, and per-entity docs
└── .vscode/                                 VS Code Java settings and launch.json run configurations
```

---

## Prerequisites

- **Java JDK 17 or newer** (`java -version`)
- **MySQL Server 8.x** running locally (default host/port: `127.0.0.1:3306`)
- **MySQL Workbench** or the `mysql` command-line client
- **Optional:** [VS Code](https://code.visualstudio.com/) with the **Extension Pack for Java** (Maven does not need to be installed globally because the Maven Wrapper is included)

---

## Database Setup

### 1. Create the Schema and Load Sample Data

Run `sql/schema.sql` first, then `sql/sample_data.sql`:

- **In MySQL Workbench:** Open and execute `sql/schema.sql`, then open and execute `sql/sample_data.sql`.
- **Using `mysql` CLI:**
  ```bash
  mysql -u root -p < sql/schema.sql
  mysql -u root -p < sql/sample_data.sql
  ```

`sql/schema.sql` creates `newsagentdb` and 7 tables: `CustomerTable`, `PublicationTable`, `DeliveryPersonTable`, `NewsagentTable`, `DeliveryAreaTable`, `OrderTable`, and `DeliveryDocketTable`.

`sql/sample_data.sql` loads sample data for Athlone, Co. Westmeath (1 newsagent account, 6 delivery persons `101`–`106`, 6 delivery areas `1`–`6`, 12 customers, 12 publications, 25 orders, and 2 delivery dockets).

### 2. Configure Database Credentials

`src/main/java/com/newsagent/db/DatabaseConnection.java` reads JVM system properties with local defaults:

| Property | Default Value |
| --- | --- |
| `db.url` | `jdbc:mysql://127.0.0.1:3306/newsagentdb` |
| `db.user` | `root` |
| `db.password` | `""` (blank) |

You can pass credentials on the command line using `-Ddb.url=...`, `-Ddb.user=...`, and `-Ddb.password=...` without editing source files. If you edit `DatabaseConnection.java` locally for VS Code runs, **do not commit your real password**.

---

## Running the Application

> Tip: When starting from an empty database (without `sql/sample_data.sql`), create customers, publications, and delivery persons/areas before creating orders or delivery dockets.

### Option A: Command Line (Maven Wrapper + Java)

1. Compile the project:
   - **Windows (PowerShell / Command Prompt):**
     ```powershell
     .\mvnw.cmd compile
     ```
   - **macOS / Linux:**
     ```bash
     ./mvnw compile
     ```

2. Run `MasterCLI` with the bundled MySQL JDBC driver (omit `"-Ddb.password=..."` if your MySQL user has no password; keep quotes around `-Ddb.*` in PowerShell so `.` is not split):
   - **Windows:**
     ```powershell
     java "-Ddb.user=root" "-Ddb.password=YOUR_PASSWORD" -cp "target\classes;lib\mysql-connector-j-26_7_0.jar" com.newsagent.cli.MasterCLI
     ```
   - **macOS / Linux:**
     ```bash
     java "-Ddb.user=root" "-Ddb.password=YOUR_PASSWORD" -cp "target/classes:lib/mysql-connector-j-26_7_0.jar" com.newsagent.cli.MasterCLI
     ```

You can also pass `"-Ddb.url=jdbc:mysql://127.0.0.1:3306/newsagentdb"` if your MySQL server uses a different host or port.

### Option B: VS Code

1. Open the repository folder via **File → Open Folder**. VS Code automatically imports it as a Maven project from `pom.xml`.
2. Update your MySQL credentials in `src/main/java/com/newsagent/db/DatabaseConnection.java` (or configure VM args `-Ddb.user=... -Ddb.password=...` in `.vscode/launch.json`).
3. Open `src/main/java/com/newsagent/cli/MasterCLI.java` and click **Run** above `main` (or use the Run & Debug launch configurations in `.vscode/launch.json` for `MasterCLI`, `CustomerCLI`, `OrderCLI`, `PublicationCLI`, `DeliveryAreaCLI`, or `DeliveryDocketCLI`; `BillCLI`, `DeliveryPersonCLI`, and `NewsagentCLI` can also be run directly from their `main` methods).
4. Verify database changes in MySQL Workbench (for example, `SELECT * FROM PublicationTable;`).

If VS Code shows stale Java errors after pulling changes: press `Ctrl+Shift+P` → `Java: Clean Java Language Server Workspace` → **Restart and delete**.

### Option C: Packaged Executable JAR (if supplied)

If running a packaged fat JAR (`NewsagentSystem.jar`) that bundles the MySQL driver:

```bash
java "-Ddb.user=root" "-Ddb.password=YOUR_PASSWORD" -jar NewsagentSystem.jar
```

*(Note: `NewsagentSystem.jar` is not committed to this repository, and `pom.xml` builds a standard non-shaded JAR. Use Option A or Option B when running directly from source.)*

---

## Testing & TDD

### Unit Tests (JUnit 5)

Run all 288 JUnit tests from the command line (no MySQL connection required — tests cover the model validation and billing rules):

- **Windows:** `.\mvnw.cmd test`
- **macOS / Linux:** `./mvnw test`
- **VS Code:** Run tests from the **Testing** panel (beaker icon).

| Test Class | Tests | Entity Covered |
| --- | --- | --- |
| `CustomerTest` | 51 | Customer validation rules |
| `OrderTest` | 46 | Order validation, duplicates, delivery due-date rules |
| `DeliveryPersonTest` | 41 | Delivery person validation rules |
| `PublicationTest` | 38 | Publication validation rules |
| `BillTest` | 35 | Billing period, delivery counts, line totals, ID/name check |
| `NewsagentTest` | 28 | Newsagent shop validation rules |
| `DeliveryAreaTest` | 25 | Delivery area validation rules |
| `DeliveryDocketTest` | 24 | Docket creation, update, status, and delete rules |
| **Total** | **288** | **8 model classes** |

### TDD Red → Green Verification (`tdd/product-skeleton/`)

1. Copy `tdd/product-skeleton/<Entity>.java` over `src/main/java/com/newsagent/model/<Entity>.java`.
2. Run that entity's test class (e.g., `.\mvnw.cmd test -Dtest=CustomerTest` or in VS Code): every test fails with `UnsupportedOperationException: No Product Written Yet`.
3. Restore the implemented class (`git restore src/main/java/com/newsagent/model/<Entity>.java` or **Source Control → Discard Changes** in VS Code) and re-run the test class to confirm all tests pass.

---

## Coursework & Demo Checklist

| Requirement | Where to Show / Verify It |
| --- | --- |
| Equivalence Partitioning & Boundary Value Analysis | Run `.\mvnw.cmd test` or VS Code Testing panel: **288 JUnit tests pass**. Rule-by-rule coverage is in `docs/Test Design - EP and BVA.xlsx` (note: the `Summary` sheet records an earlier 280-test baseline prior to the name-validation additions in `CustomerTest` and `DeliveryPersonTest`). |
| Show order IDs by customer name (`CustomerTable JOIN OrderTable`) | In `MasterCLI`: `2. Order Management` → `3. Find Orders By Customer Name`. In MySQL Workbench: run `sql/orders_by_customer_name.sql` (update `'Aarush'` to match a loaded customer, or run `sql/order_integration_tests.sql`). |
| SDLC justification | `docs/SDLC Justification.docx` (Scrum in four 1-week sprints with TDD, compared against Waterfall, V-Model, Iterative, Incremental, and Spiral). |
| User Acceptance Tests (UAT) | `docs/Test Design - EP and BVA.xlsx` (`UAT` sheet): `UAT01`–`UAT53` run end-to-end through `MasterCLI` against MySQL (`52` passing tests; `UAT33` is marked `N/A` after the Extra Features menu was removed). |
| User stories & TDD evidence | `docs/User Stories.xlsx` (`US1`–`US32` acceptance criteria), `docs/TDD Test Run Evidence.docx`, `docs/delivery-evidence/`, and `docs/order-bill-evidence/`. |

---

## MySQL Troubleshooting

- **`Unknown database 'newsagentdb'`:** Run `sql/schema.sql` in MySQL Workbench or via `mysql` CLI, then confirm with `SHOW DATABASES;`.
- **`Access denied for user`:** Verify your MySQL username and password and pass `-Ddb.user=... -Ddb.password=...` (or update local `DatabaseConnection.java` values).
- **`Communications link failure` / Connection refused:** Ensure the MySQL service is running and listening on port `3306` (or pass `-Ddb.url=jdbc:mysql://<host>:<port>/newsagentdb`).

---

## Contributing & GitHub Workflow

Work on a feature branch and open a pull request into `main`. Commit in TDD order so history reflects each step:

1. Product skeleton
2. Failing black-box tests
3. Product class implementation
4. White-box tests
5. DAO
6. CLI
7. SQL table / docs

Prefix commit messages with the user story ID, for example: `US9: add Publication product skeleton`.

---

## License

This project is licensed under the MIT License — see [LICENSE](LICENSE) for details.
