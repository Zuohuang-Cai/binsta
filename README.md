# Binsta

## Overview

This project is a web application built using Java. It demonstrates how to use Java technologies to create and manage a
web page, covering both front-end and back-end functionalities.

## Features

- Backend processing with Java
- Simple front-end presentation
- User interaction features
- Data storage and management

## Technology Stack

- **Java** - Springboot
- **Servlets/Thymeleaf** - Web page generation
- **HTML/CSS/JavaScript** - Front-end presentation
- **[Hibernate]** - Data storage

## Requirements

- JDK 21.0.2
- 10.4.28-MariaDB
- Maven/gradle

## Installation

1. **Clone the Repository**

    ```bash
    git clone git@github.com:Zuohuang-Cai/binsta.git
    ```

2. **Navigate to the Project Directory**

    ```bash
    cd binsta
    ```

3. **Build the Project**

   If using Maven:

    ```bash
    mvn clean install
    ```

   If using Gradle:

    ```bash
    gradle build
    ```

4. **Configure the Environment**

   Configure [Web Server] and [Database], and modify `application.properties`,for example:
    ```properties
    spring.application.name=demo
    spring.datasource.url=jdbc:mariadb://localhost:3306/binsta
    spring.datasource.username=root
    spring.datasource.password=
    spring.datasource.driver-class-name=org.mariadb.jdbc.Driver
    spring.jpa.hibernate.ddl-auto=create-drop
    spring.jpa.show-sql=true
    spring.web.resources.static-locations=classpath:/static/
    spring.thymeleaf.mode=HTML
    spring.thymeleaf.encoding=utf-8
    spring.thymeleaf.prefix=classpath:/templates/
    springdoc.swagger-ui.path=/doc/api
    spring.profiles.active=prod

    logging.level.org.hibernate.SQL=DEBUG
    logging.level.org.springframework.security=DEBUG
    logging.level.com.zaxxer.hikari=DEBUG

5. **Start the Server**

   Start the [Web Server] and access the application at `http://localhost:8080/`.

## Usage

1. Open a web browser and navigate to `http://localhost:8080/`.
2. Follow the instructions on the page to interact with the application.


## Seeder

- **Default Avatar**:
    - In the `HomeController.index`, `defaultAvatar.png` is used as the default avatar. Ensure that you have this image
      placed in the `resources/static/images` folder of your project.

- **Seeder Configuration**:
    - The seeder is enabled by default when the application starts. If you want to disable the seeder, you can change
      the `spring.profiles.active` property in your `application.properties` file to `prod`.

  ```properties
  spring.profiles.active=prod
    ```

- The BlogSeeder has a property named IMAGES, which contains the names of images stored in the resources/static/images
  directory. This list is used for seeding blog entries. Make sure that the images exist with the correct names, similar
  to UserSeeder.

- in branch "images" contain all images and md file for Seeder.
 ```java

Class BlogSeeder {
    private static final List<String> IMAGES = Arrays.asList(
        "mountain.jpeg", "space.jpeg", "pinkBol.png", "sunflower.png", "rat.png", "panda.png", "tiger.png"
    );
}

```

```java

Class UserSeeder {

private static final List<String> Avatars = Arrays.asList(
        "AvatarKids.png", "AvatarMan.png", "AvatarWoman.png"
);
}
```
- some images in branche "images" are huge than 1mb ,so make sure you set max_allowed_packet=16M in my.cnf file of MariaDB.
