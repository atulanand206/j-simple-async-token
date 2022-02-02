# Addison Global Backend Assessment Solution

This repository is an attempt towards solving a backend challenge hosted by Addison Global at [backend-technical-test](https://github.com/addisonglobal/backend-technical-test).

## The test involved the following challenges:

- Interface declaration and use of default methods in `part1` package.
  - Default methods used to fix interaction of unimplemented methods.
- Core service interacting with other services in `part2` package.
- Controller interacts with core service asynchronously.
- Random delay introduced by each service.
- Visualization of concurrent requests in `ConcurrentAppRunner`. 
  - 100 requests completed in less than 100 seconds with an executor pool of 8 threads demonstrating availability of service.
- Structure of validations and tests for improved coverage.
- In the occurrence of exceptions, the error is wrapped into an object so that the API consumers knows the way to understand the errors. 

## Run the application

- The simplest way to launch this application is to Open the Project in IntelliJ and let Gradle do all the magic. 
- The CLI way to launch this application is by running the command `./gradlew bootRun`.

Gradle tasks on the right side panel of IntelliJ provides one-click actions for all the build steps. This panel also has options to format the code and test the project.