# 🪑 Urban Ladder Selenium Automation

A web test-automation framework for the [Urban Ladder](https://www.urbanladder.com/) furniture website, built with **Java**, **Selenium WebDriver** and **TestNG** using the **Page Object Model (POM)** design pattern. It automates real user flows such as searching and filtering bookshelves, reading the Living menu, and validating the gift card form, with logging, screenshots and Allure result output.

![Java](https://img.shields.io/badge/Java-21-orange)
![Selenium](https://img.shields.io/badge/Selenium-4.43.0-green)
![TestNG](https://img.shields.io/badge/TestNG-7.12.0-red)
![Maven](https://img.shields.io/badge/Build-Maven-blue)
![Allure](https://img.shields.io/badge/Reports-Allure-purple)

---

## 📌 What It Tests

| Area | Scenario |
|---|---|
| **Bookshelves** | Search for "Bookshelves" from the home page |
| | Open the "Filter and Sort" panel and verify it appears |
| | Apply filters: Storage Type = Open Storage, Availability = With Storage, max price ₹15,000 |
| | Print the first 3 bookshelves priced at or below ₹15,000 and save a screenshot |
| | Navigate back to the home page and verify the page title |
| **Home page** | Hover the **Living** menu, collect all sub-menu sections and items, print them, save a screenshot, and verify the number of sections |
| **Gift cards** | Open the Gift Cards page (new tab), fill amount, quantity, theme and sender/receiver details, and verify the page title |
| | Check email-field and mobile-number validation messages |

---

## 🧰 Tech Stack

| Purpose | Tool |
|---|---|
| Language | Java 21 |
| Browser automation | Selenium WebDriver 4.43.0 (Chrome) |
| Test framework | TestNG 7.12.0 |
| Design pattern | Page Object Model with `@FindBy` and `PageFactory`-style locators |
| Logging | Apache Log4j 2 (console + file) |
| Reporting | Allure TestNG 2.24.0 (results written to `target/allure-results`) |
| Build | Maven (Surefire 3.2.5) |

---

## 🏗️ Framework Design

- **Page classes** (`HomePage`, `BookShelvesPage`, `GiftcardsPage`) hold locators and page actions, so tests stay short and readable.
- **`ReqUtils`** is a reusable helper layer for explicit waits, clicks (including JavaScript click), typing, hovering, scrolling, switching tabs and taking screenshots.
- **`DriverManager`** is a singleton that creates and quits the Chrome driver (window maximized).
- **`BaseSetup`** opens `https://www.urbanladder.com/` before each test class and closes the browser afterwards.
- **`TestListener`** automatically captures a screenshot whenever a test fails.
- **Log4j** writes logs to the console and to `logs/automation.log`.

---

## 📁 Project Structure

```
urbanladder-selenium-automation
├── pom.xml
├── testng.xml                      # Suite: BookShelvesTest, HomePageTest, GiftCardTest
├── Visual_Output/                  # Screenshots saved by the tests
├── logs/                           # Log4j output (automation.log)
└── src
    ├── main/java/org/example
    │   ├── base/BasePage.java
    │   ├── pages/
    │   │   ├── HomePage.java
    │   │   ├── BookShelvesPage.java
    │   │   └── GiftcardsPage.java
    │   └── utils/
    │       ├── DriverManager.java
    │       └── ReqUtils.java
    └── test
        ├── Resources/log4j2.xml
        └── java
            ├── base/BaseSetup.java
            └── tests/
                ├── BookShelvesTest.java
                ├── HomePageTest.java
                ├── GiftCardTest.java
                └── TestListener.java
```

---

## 🚀 Getting Started

### Prerequisites

- **JDK 21** (the project is compiled for Java 21)
- **Maven 3.8+**
- **Google Chrome** (latest)
- Internet access (the tests run against the live Urban Ladder site)

> You do **not** need to download ChromeDriver manually. Selenium 4 includes Selenium Manager, which fetches a matching driver automatically.

### Clone

```bash
git clone https://github.com/princekumar2311/urbanladder-selenium-automation.git
cd urbanladder-selenium-automation
```

### Run all tests

```bash
mvn clean test
```

### Run the TestNG suite file

```bash
mvn clean test -Dsurefire.suiteXmlFiles=testng.xml
```

### Run a single test class

```bash
mvn test -Dtest=BookShelvesTest
```

You can also run `testng.xml` directly from IntelliJ IDEA or Eclipse.

---

## 📊 Reports, Logs and Screenshots

| Output | Location |
|---|---|
| Console and file logs | `logs/automation.log` |
| Screenshots | `Visual_Output/` |
| Allure raw results | `target/allure-results` |
| Surefire reports | `target/surefire-reports` |

To view the Allure report (requires the [Allure CLI](https://allurereport.org/docs/install/)):

```bash
allure serve target/allure-results
```

### Sample screenshots

| Living menu | Top 3 bookshelves |
|---|---|
| ![Living menu](Visual_Output/LivingMenu_List.png) | ![Top three bookshelves](Visual_Output/Top_Three_BookShelves.png) |

| Email validation | Mobile validation |
|---|---|
| ![Email validation](Visual_Output/validateEmail.png) | ![Mobile validation](Visual_Output/validateMobileNumber.png) |

---

## 📝 Notes

- **Gift card validation tests.** The tests deliberately enter an invalid email (`abc@gmailcom`) and an invalid mobile number, so the site shows "Enter valid Email ID" and "Enter valid Mobile Number" (see the screenshots above). The page method returns `"Invalid ..."` when the error appears, while the current assertions expect `"Valid ..."`. As written, these two tests fail and the listener saves the screenshot. To make them pass, change the expected values in `GiftCardTest` to `"Invalid Email ID"` and `"Invalid Mobile Number"`.
- **Live-site dependency.** The tests run against the real website. Locators (some use generated class names such as `XxwSy` and `UYQNp`), the home page title and the number of Living menu sections can change when the site is updated, which may cause failures.
- **Log4j config folder.** `log4j2.xml` is in `src/test/Resources`. On Linux and macOS, Maven expects `src/test/resources` (lowercase), so rename the folder if the Log4j file is not picked up.

---

## 🗺️ Roadmap

- [ ] Add Allure annotations (`@Step`, `@Feature`) for richer reports
- [ ] Add cross-browser support (Firefox, Edge) and a headless mode
- [ ] Move URLs and test data to a config file
- [ ] Use more stable locators (data attributes, text-based XPath)
- [ ] Add CI with GitHub Actions
- [ ] Remove the unused `Main.java` template class

---

## 👨‍💻 Author

**Prince Kumar**
Computer Science & Engineering Graduate | Java Developer | QA Automation Enthusiast
GitHub: [@princekumar2311](https://github.com/princekumar2311)
