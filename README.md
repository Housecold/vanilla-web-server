# 🌐 Vanilla Web Server

This project involves creating a simple web server using Java that responds to GET requests and serves static HTML pages.

## Goal

Implement the necessary classes and methods to create a web server that listens for incoming HTTP GET requests on a specific port and responds by serving the appropriate static content.

## Skills

- Consolidate knowledge of the client-server architecture.
- Get Familiarize with how the HTTP protocol and the request-response flow work.
- Understand how both HTTP requests and responses are structured.
- Practice using Maven.

### Key Points:

- Organize the `www` directory in the `src/main` directory as the document root where all the files the web server can serve are stored.
- Create any necessary classes for implementing the desired behavior. However, the program should include at least the following functionalities:

  I. **Request Handling**
     - The server must handle incoming GET requests by parsing the requested URL path and looking for the corresponding file in the document root directory.

  II. **Response Generation**
     - The server should generate the appropriate HTTP response based on the request. If the requested file exists, respond with a 200 status code and the file content. If the file does not exist, respond with a 404 status code and a default `404.html` page.

  III. **Content Delivery**
     - Ensure that static content such as HTML pages, images, and icons are served correctly with the appropriate content type and headers.

  IV. **HTTP Protocol Adherence**
     - Responses should include header fields such as `Content-Type` and `Content-Length` and should follow the HTTP protocol structure.

### Build Instructions

To build the project using Maven, use the `pom.xml` file provided. This file will package your project into a JAR file. To do so, run this command:

```bash
mvn package
```

## Requirements

- Java Development Kit (JDK) 17 or above.
- Apache Maven.
- A Java IDE like IntelliJ IDEA, or any other of your choice.

## Installation

Clone the repository and navigate to the folder.

```bash
git clone <repository-url>
cd <repository-folder>
```

## Usage

To run the Java program:

```bash
java -jar target/vanilla-web-server-1.0-SNAPSHOT-jar-with-dependencies.jar 
```

## Contributing

Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## Contributors

-  Daniel Magalhães - [@Housecold](https://github.com/Housecold)
-  David Vilaverde - [DTRV95](https://github.com/DTRV95)
