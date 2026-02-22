# Trello REST Assured Test Suite

Automated API tests for the Trello REST API using Rest Assured and TestNG.

---

## Prerequisites

- Java 17+
  - Maven 3.6+
- A Trello account

---

## Setup

### 1. Create a Trello Account

Go to [https://trello.com/signup](https://trello.com/signup) and create a free account.

---

### 2. Get Your API Key and Secret

1. Log in to Trello, then go to [https://trello.com/power-ups/admin](https://trello.com/power-ups/admin)
2. Click **New** to create a new Power-Up (any name works, e.g. "My Test App")
3. Once created, click the **API Key** tab
4. Copy your **API Key** — this is your `consumerKey`
5. Click **Secret** to reveal it — this is your `consumerSecret`

---

### 3. Generate a Token

1. On the same API Key page, click the **Token** link
2. Trello will ask you to authorize access — click **Allow**
3. Copy the token shown — this is your `accessToken`
4. The `tokenSecret` is shown on the same page alongside the token

---

### 4. Configure Credentials

1. Navigate to `src/test/resources/`
2. Copy `auth.properties.example` and rename the copy to `auth.properties`
3. Open `auth.properties` and fill in your credentials:

```properties
consumerKey=YOUR_API_KEY
consumerSecret=YOUR_API_KEY_SECRET
accessToken=YOUR_TOKEN
tokenSecret=YOUR_TOKEN_SECRET
```

> **Important:** `auth.properties` is gitignored and must never be committed. It contains your private credentials.

---

## Running the Tests

### Run all tests (via TestNG suite)

```bash
mvn test
```

This runs all test classes defined in `testng.xml` (Boards, Lists, Cards) and cleans up all created boards after the suite finishes.

### Run a specific test class

```bash
mvn test -Dtest=BoardsTest
mvn test -Dtest=ListsTest
mvn test -Dtest=CardsTest
```

### Run from IntelliJ IDEA

Right-click `testng.xml` in the project root → **Run**

---

## Project Structure

```
src/test/java/com/trello/
├── apis/
│   ├── BoardApis.java       # Board API calls (create, get, delete)
│   ├── ListApis.java        # List API calls (add, get)
│   └── CardApis.java        # Card & attachment API calls
├── base/
│   ├── BaseTest.java        # @AfterSuite cleanup - extended by all test classes
│   └── Specs.java           # Shared RestAssured request spec (base URI + auth)
├── data/
│   └── Route.java           # API endpoint path constants
├── models/
│   ├── Board.java
│   ├── BoardList.java
│   ├── Card.java
│   └── Attachment.java
├── steps/
│   ├── BoardSteps.java      # Test data generation for boards
│   ├── ListSteps.java       # Test data generation for lists
│   └── CardSteps.java       # Test data generation for cards
├── testCases/
│   ├── BoardsTest.java      # Board CRUD tests
│   ├── ListsTest.java       # List tests
│   └── CardsTest.java       # Card and attachment tests
└── utils/
    └── ConfigManager.java   # Loads credentials from auth.properties

src/test/resources/
├── auth.properties.example  # Credential template - copy and rename to auth.properties
├── auth.properties          # Your credentials (not committed to git)
└── file-sample_150kB.pdf    # Sample file used in attachment tests
```

---

## Viewing the Allure Report

### Prerequisites

Install Allure CLI (required once):

**Mac (Homebrew):**
```bash
brew install allure
```

**Windows (Scoop):**
```bash
scoop install allure
```

### Generate and open the report

After running `mvn test`, results are saved to `allure-results/`. Serve the report with:

```bash
allure serve allure-results
```

This opens an interactive HTML report in your browser automatically.

---

## Notes

- After each full test run, `@AfterSuite` in `BaseTest` automatically deletes all boards from your Trello account to keep it clean.
- Tests use [JavaFaker](https://github.com/DiUS/java-faker) to generate random board, list, and card names.
