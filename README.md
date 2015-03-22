# java-spring-boot-restful-api
Magazine API - restful API example. 

# Stack
*  Java 8
*  JPA
*  Spring Boot
*  JUnit - Mockito
*  Gradle

# Prerequisites
*  jdk 1.8
*  gradle 2.2.1
*  any REST Client (like XHR Poster)
*  OPTIONAL: GGTS (or your favorite IDE)

# Run the example

From IDE: 
```
Import the project into the IDE. 
Build it with Gradle. 
Execute the class Application.java.
```

From Terminal / Command line:

* clone the repo
```
https://github.com/pangio/java-spring-boot-restful-api.git
```
* Build
```
./gradlew build
```
* Run
```
./gradlew bootRun
```
* use the REST Client to hit the following endpoints


# Endpoints / Http Methods

* POST - Creates a new Magazine.
```
URI: /magazines/
```

* GET - Returns a Magazine.
```
URI: /magazines/{magazineId}
```

* GET - Returns the list of all magazines.
```
URI: /magazines/
```

* PUT - Updates a Magazine.
```
URI: /magazines/{magazineId}
```

* DELETE - Deletes a Magazine.
```
URI: /magazines/{magazineId}
```

* POST - Creates a new Politic Article and is added the the articles list of the Magazine.
```
URI: /magazines/{magazineId}/politic
```

* POST - Creates a new Sport Article and is added the the articles list of the Magazine.
```
URI: /magazines/{magazineId}/sport
```

# Tests

* Under the test source folder find the suite com.pangio.magazine.api.AllTests



