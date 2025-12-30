# Java REST Assured Automation Framework

This project is a robust template for building REST API test automation frameworks using Java, REST Assured, and TestNG. It includes several modern features designed for stability, performance, and easy debugging in a CI/CD environment.

## Features

- **REST Assured:** Fluent and intuitive DSL for writing powerful API tests.
- **TestNG:** Flexible test runner for managing the test suite.
- **Parallel Execution:** Configured to run test classes in parallel to significantly reduce execution time.
- **Resilience & Stability:**
  - **Circuit Breaker:** Integrated with Resilience4j to automatically halt tests when the target API becomes unstable, preventing cascading failures.
- **Deep Observability:**
  - **Log-on-Failure:** A custom filter captures full request/response details **only when a test fails**.
  - **Secure Logging:** Automatically masks sensitive data (e.g., `Authorization` tokens) in failure logs.
  - **Persistent Failure Logs:** Failure logs are stored in `target/api-tests.log`, which is automatically cleared at the start of each run.
- **Environment Configuration:** Easily switch between different test environments (e.g., QA, Staging) with a single command-line argument.
- **Dockerized Execution:** Includes a `Dockerfile` for running the entire test suite within a container, ensuring a consistent and portable environment.

## Project Structure

```
.
├── src
│   ├── main
│   │   ├── java/com/example/api
│   │   │   ├── clients       # API clients for specific endpoints (e.g., ProductClient)
│   │   │   ├── models        # Request/Response payload models (POJOs)
│   │   │   ├── specs         # Reusable REST Assured specifications
│   │   │   ├── filters       # Custom Rest Assured filters (e.g., CustomLogFilter)
│   │   │   ├── listeners     # TestNG listeners (e.g., TestResultListener)
│   │   │   └── ...           # Other framework components (constants, endpoints, etc.)
│   │   └── resources         # Property files for different environments (e.g., qa-config.properties)
│   └── test
│       └── java/com/example/api
│           ├── base          # Base test class with common setup/teardown
│           └── tests         # Test classes and test methods
├── pom.xml                   # Maven project configuration
└── Dockerfile                # Docker configuration for containerized execution
```

## Prerequisites

- Java 21+
- Apache Maven 3.9+
- Docker (for containerized execution)

## How to Run Tests

To run the tests, you must specify an environment using the `-Denv` system property. This tells the framework which configuration file to use from `src/main/resources/`.

For example, to run tests against the QA environment, use:

```bash
mvn test -Denv=qa
```

To run against the `staging` environment, use:

```bash
mvn test -Denv=staging
```

## Dockerized Execution

You can also run the entire test suite inside a Docker container.

1.  **Build the Docker image:**

    ```bash
    docker build -t java-rest-assured-framework .
    ```

2.  **Run the tests in a container:**

    By default, the container will run the tests against the `qa` environment.

    ```bash
    docker run --rm java-rest-assured-framework
    ```

    To run against a different environment, you can override the default command:

    ```bash
    docker run --rm java-rest-assured-framework mvn test -Denv=staging
    ```

### Accessing Failure Logs from Docker

If tests fail inside the container, the `api-tests.log` file will be generated within the container's filesystem. To access it on your local machine, you can mount a volume when running the container:

```bash
# For PowerShell
docker run --rm -v ${PWD}/target:/app/target java-rest-assured-framework

# For Bash (Linux/macOS/Git Bash)
docker run --rm -v $(pwd)/target:/app/target java-rest-assured-framework
```
After the run completes, the `target` directory on your host machine will contain the `api-tests.log` file if any tests failed.
