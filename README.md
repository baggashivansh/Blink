# Blink

A minimal production style URL shortener built with Spring Boot.

Blink converts long and messy URLs into short, clean links that are easier to share and remember. When someone visits the shortened link, the service redirects them to the original destination.

This project focuses on building a simple but realistic backend service that mimics how real world link shorteners work.

Example

Original URL

https://example.com/products/electronics/mobile-phones/apple/iphone-15-pro-max?ref=homepage&campaign=spring_sale

Shortened URL

https://blink-ozo5.onrender.com/a7F3x


--------------------------------------------------

Live Demo

Backend deployed on Render

https://blink-ozo5.onrender.com

Source Code

https://github.com/baggashivansh/Blink

You can open the live service and generate short URLs directly from the interface.


--------------------------------------------------

Why This Project Exists

Long URLs are often difficult to share. They contain tracking parameters, deep routing paths, and other metadata that make them unreadable.

A URL shortener solves this by generating a short identifier that maps to the original URL.

When a user opens the shortened link, the system performs a lookup and redirects them to the stored destination.

This project demonstrates how such a system can be implemented using a modern Java backend stack.

The goal was not just to shorten URLs, but to simulate how a small production style backend service behaves.


--------------------------------------------------

How Blink Works

At a high level the system follows a simple flow.

1. A user submits a long URL to the backend.
2. The server generates a unique short code.
3. The mapping between the short code and the original URL is stored.
4. When someone opens the short link, the service looks up the code and redirects the request.

The redirect is performed using an HTTP redirect response so the browser automatically navigates to the original page.

This pattern is used by services like Bitly and TinyURL.


--------------------------------------------------

System Architecture

Blink follows a simple layered backend architecture.

Client (Browser)
↓
Spring Boot REST Controller
↓
Service Layer
↓
Repository / Data Storage

Each layer has a clear responsibility.

Controller layer  
Handles incoming HTTP requests and exposes API endpoints.

Service layer  
Contains the business logic responsible for generating short codes and processing URLs.

Repository layer  
Manages persistence and retrieval of URL mappings.

This separation keeps the system maintainable and easier to extend.


--------------------------------------------------

Features

• Generate shortened URLs  
• Redirect short links to their original destination  
• REST API for URL shortening  
• Simple frontend interface  
• Static frontend served directly from Spring Boot  
• Dockerized deployment  
• Live deployment on Render  


--------------------------------------------------

API

Create Short URL

POST /api/shorten

Request

{
  "url": "https://google.com"
}

Response

{
  "shortUrl": "https://blink-ozo5.onrender.com/abc123"
}


--------------------------------------------------

Redirect Endpoint

GET /{shortCode}

Example

https://blink-ozo5.onrender.com/abc123

When this endpoint is accessed, the service looks up the short code and returns an HTTP redirect response pointing to the stored original URL.

The browser then automatically navigates to the destination.


--------------------------------------------------

Running Locally

Clone the repository

git clone https://github.com/baggashivansh/Blink.git  
cd Blink

Run the Spring Boot application

./mvnw spring-boot:run

Once the server starts, open the application in your browser

http://localhost:8080


--------------------------------------------------

Deployment

Blink is deployed on Render using Docker.

Deployment workflow

1. The GitHub repository is connected to Render
2. Render builds the Docker image
3. Maven compiles the Spring Boot project
4. A JAR file is generated
5. The container starts the application
6. Render assigns a public URL

Render provides the port dynamically, so the application binds to the environment port instead of a fixed port.

This allows the service to run correctly inside a containerized environment.


--------------------------------------------------

Tech Stack

Backend

Java 21  
Spring Boot  
Spring Web  
Spring Data JPA  

Build Tool

Maven

Deployment

Docker  
Render

Frontend

HTML  
CSS  
Vanilla JavaScript


--------------------------------------------------

Project Structure

Blink

src  
 └─ main  
     ├─ java  
     │   └─ com/shivansh/blink  
     │       ├─ controller  
     │       ├─ service  
     │       ├─ repository  
     │       └─ model  
     │  
     └─ resources  
         └─ static  
             └─ index.html  

Dockerfile  
pom.xml  
README.md


--------------------------------------------------

Author

Built by Shivansh Bagga

