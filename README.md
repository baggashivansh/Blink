# Blink

A minimal production style URL shortener built with Java and Spring Boot.

Blink converts long URLs into short, shareable links and redirects users to the original destination instantly.

Example:

Long URL

https://example.com/products/electronics/mobile-phones/apple/iphone-15-pro-max?ref=homepage&campaign=spring_sale

Shortened URL

https://blink-backend-mjj3.onrender.com/a7F3x

When a user opens the short link, Blink performs a fast redirect to the original URL.

---

# Live Demo

https://blink-backend-mjj3.onrender.com

---

# Overview

Blink is a backend focused project designed to demonstrate how URL shortening services such as Bitly or TinyURL work internally.

Although URL shorteners appear simple, they involve several important backend engineering ideas including:

Unique code generation
Database indexing
Fast redirect systems
REST API design
Service deployment

Blink implements the core mechanics behind these systems using a clean Spring Boot architecture.

---

# Features

## URL Shortening

Users can submit a long URL and Blink generates a unique short code.

Example

Input

https://github.com/shivanshbagga

Output

https://blink-backend-mjj3.onrender.com/a8F3x

Each short code maps to exactly one original URL.

---

## Fast Redirect

When a user visits a shortened link:

https://blink-backend-mjj3.onrender.com/a8F3x

Blink performs the following steps:

1. Extract the short code
2. Look up the original URL in the database
3. Redirect the user using HTTP redirect

This lookup happens in milliseconds.

---

## Clean Web Interface

Blink includes a minimal frontend interface where users can:

Paste a long URL
Generate a short link
Copy the shortened URL instantly

The frontend is served directly by the Spring Boot server.

---

# System Architecture

Blink uses a simple service architecture.

Client
↓
Spring Boot API
↓
Database

Flow: Creating a Short Link

User → POST /api/shorten
↓
Validate URL
↓
Generate short code
↓
Store mapping in database
↓
Return short URL

Flow: Redirecting

User → GET /{shortCode}
↓
Lookup short code in database
↓
Redirect to original URL

---

# Technology Stack

Backend

Java
Spring Boot
Spring Web
Spring Data JPA

Database

MySQL

Deployment

Render

Frontend

HTML
CSS
Vanilla JavaScript

---

# Project Structure

blink
src
└─ main
└─ java
└─ controller
└─ service
└─ repository
└─ model

resources
└─ static
└─ index.html

The static folder allows Spring Boot to serve the frontend directly.

---

# Database Design

URL Table

Field | Type | Description
id | BIGINT | Primary key
original_url | TEXT | Original long URL
short_code | VARCHAR | Generated short code
created_at | TIMESTAMP | Creation time
click_count | INT | Number of visits

An index on short_code enables fast lookup during redirects.

---

# API Endpoints

Create Short URL

POST /api/shorten

Request

{
"url": "https://example.com/article"
}

Response

{
"shortUrl": "https://blink-backend-mjj3.onrender.com/a8F3x"
}

---

Redirect

GET /{shortCode}

Example

GET /a8F3x

Response

HTTP 302 Redirect

Location: https://original-url.com

---

# Deployment

Blink is deployed on Render.

The Spring Boot application serves both:

Frontend interface
REST API

Live application

https://blink-backend-mjj3.onrender.com

---

# Future Improvements

Possible enhancements for Blink include:

Custom aliases for URLs
Link expiration
Click analytics
QR code generation
Redis caching for popular URLs
Rate limiting for abuse protection

---

# Learning Outcomes

Building Blink demonstrates several important backend engineering concepts:

REST API development
URL encoding strategies
Database indexing
HTTP redirects
Full stack deployment
Clean service architecture

---

# License

MIT License

---

# Author

Built by Shivansh Bagga
