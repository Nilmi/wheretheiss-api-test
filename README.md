**Automated API test for https://wheretheiss.at/w/developer**

**How to run the test?**

Preconditions:

- JDK 11 or higher version installed and configured according
  to [installation guide](https://docs.oracle.com/en/java/javase/11/install/overview-jdk-installation.html)

Steps:

1. Clone the project from GitHub using `'git clone https://github.com/Nilmi/wheretheiss-api-test.git'`
2. Open the terminal and navigate to the project root directory
3. Run the command `./gradlew test -i --tests "at.wheretheiss.apitest.TestRunner"`

![Run the test](https://user-images.githubusercontent.com/25843579/128882422-a5d0fd71-799b-4c5c-9ee2-8b92091f458d.png)

**Technical details**

This automated API test is implemented using Java programming language and Spring Boot framework.

The following are the other tools, frameworks and libraries used:

|Tool / Framework / Library |Usage | |--|--| |Gradle |Build tool and dependency management | |Gherkin |Write the test
cases in Behavior Driven Development(BDD) pattern | |Cucumber |Implement Gherkin test cases using Java programming
language | |RESTAssured |Send request to API, receive the response, and map response to Data Transfer Object (DTO)  |
|JUnit |Use JUnit assertions to verify actual result against the expected result | |Masterthought reports |Generate
automated test report |

**Code structure**

- Automated API test is designed to enable code reuse, maintainability and expandability.
- This project can be expanded to a test suite which contains large number of test cases belong to different test
  suites (for example: regression test, smoke test, etc) cover different features.
- The code is organized in to the following components,
    - Gherkin test cases:

        - Test cases are organized in feature files as scenarios (package: src/test/resources/features/).
        - Test cases can be tagged according to the test suite they belong / functionality they cover.
        - When test cases are tagged like this, the tag can be used to run test cases as test suite.
        - Currently, there are two feature files covering the below APIs.
            - satellites/[id]/positions
            - satellites/[id]/tles

      ![Gherkin test cases](https://user-images.githubusercontent.com/25843579/128882048-c9b1077a-d44f-497d-a47b-6ab47bea5486.png)

    - Step definitions:

        - Maps Gherkin scenario steps with Java code (package: src/test/java/at/wheretheiss/apitest/stepdefinitions).
        - Step definitions are implemented using RESTAssured library.

    - DTOs:
        - DTOs are used to deserialize json objects to Java objects (package: src/test/java/at/wheretheiss/apitest/dto).
        - DTO objects with deserialized json data are then used to verify actual result with expected result.

    - Properties:
        - Application property file (src/main/resources/application.properties) contains the base url and APIs used in the tests.
        - Tests refer this property file through the Java classes in properties package (package: src/main/java/at/wheretheiss/apitest/properties) to get the base url and APIs.

    - Runner:
        - Contains feature file, step definition binding.

    - build.gradle:
        - All the dependencies are specified here.
        - Contains the steps to generate the report after the test.
        - Contains steps to run when `./gradlew :test -i --tests "at.wheretheiss.apitest.TestRunner"` command is executed.     

**About the test**

Tests are written using Gherkin language.

As the first step, test conditions were identified to test the two APIs. This project covers some identified positive
and negative test conditions. But there are number of other test cases which can be added to this project to increase
the test coverage.

**Test report**

After execution of these API test cases it generates a report in HTML format. Reports are generated inside the <
project-directory>/build/reports/cucumber-html-reports.
'overview-features.html' is the landing page of the report.

This report has two different views:

	- Dashboard view
		- Provides an overview of test results using graphical representation
		- Summarizes test results according to features, scenarios, test steps
		- Provides test execution date, time details

![Dashboard view](https://user-images.githubusercontent.com/25843579/128882793-6df98695-ffc5-4908-86d3-7c0230311f44.png)

	- Detailed results view
		- Provides a detailed test results representation of each test case
		- Test steps of each test case are highlighted according to color code based on the execution status

![Detailed results view](https://user-images.githubusercontent.com/25843579/128882992-c9cbc11f-399d-4a30-b698-21bb6dd1666b.png)

|Colour code |Representation | |--|--| |Green |Passed | |Red |Failed | |Blue |Skipped |