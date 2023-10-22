## Description

Recipe Book Management is a Spring Boot MVC application for managing and sharing recipes. This repository contains the source code for the application.

## Prerequisites

Before you begin, ensure you have met the following requirements:

- Java 17
- MySQL database
- Maven

## Installation

1. Clone this repository:

   ```bash
   git clone https://github.com/sh4on/recipe-book.git

2. Configure your MySQL database by editing the application.properties file located in src/main/resources:

properties

spring.datasource.url=jdbc:mysql://localhost:3306/db_practice
spring.datasource.username=root
spring.datasource.password=root
Build and run the application:

2. The application will be accessible at http://localhost:8080.

Usage

Create, edit, and delete authors.
Manage and share recipes.
View a list of authors and their recipes.