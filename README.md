# Spring Boot Profile API,

![Java](https://img.shields.io/badge/Java-17-blue.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.5.3-green.svg)
![License](https://img.shields.io/badge/License-MIT-yellow.svg)

This project is a backend system developed using Java and Spring Boot. It provides user authentication and a user profile system, which can be used in web applications or game backends.

## Table of Contents
- [Features](#features)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
- [Deployment](#deployment)

## Features
- User registration and authentication with Spring Security.
- Profile system with RESTful API endpoints.
- Secure storage of user information in a database.

## Getting Started

#### To quick test:
````
- Clone repository.
- Build package.
- Run jar file located in target folder.
- Go to localhost:8080
- For database console, go to localhost:8080/h2-console
````

### Prerequisites
- Java JDK
- Maven

### Deployment
Deploy the backend to your preferred cloud platform following your deployment practices.

1. Build the JAR file:
Use the Maven package command to build the Spring Boot project and generate the JAR file. Open a terminal and navigate to your project's root directory, then run:

```
mvn clean package
````
This command will clean the project, compile the code, run tests, and package the application into a JAR file. You can find the generated JAR file in the target directory.

2. Choose a Hosting Platform:
Decide where you want to host the Spring Boot application. Common choices include cloud platforms like AWS, Google Cloud, Azure, or simpler solutions like Heroku. For testing purposes, you might also consider using a local server.

3. Deploy to a Cloud Platform:
If you choose a cloud platform, you'll need to follow their specific deployment instructions. Here is a general guide for deploying a Spring Boot application to Heroku:

    Create a Heroku account if you don't have one.
    Install the Heroku CLI: Heroku CLI Installation
    Login to your Heroku account using the CLI: heroku login
    Navigate to your project's root directory.
    Create a Heroku app: heroku create
    Deploy your application: git push heroku master

After the deployment is complete, you can access your Spring Boot application through the provided Heroku URL.