Blink

A Production Style URL Shortener

---

Overview

Blink is a production inspired URL shortening service designed to transform long, complex URLs into short, clean, shareable links.

Instead of sharing something like:

https://example.com/products/electronics/mobile-phones/apple/iphone-15-pro-max?ref=homepage&campaign=spring_sale

Blink converts it into something simple:

blink.ly/a7F3x

When a user opens the shortened link, Blink quickly redirects them to the original destination.

While URL shorteners appear simple on the surface, building one properly introduces several real world backend engineering concepts including hash generation, database indexing, analytics tracking, scalability, and API design.

Blink is designed as a backend focused system that mirrors how services like Bitly or TinyURL operate internally.

---

Problem Statement

Modern URLs are often extremely long and difficult to share across platforms such as:

- Social media
- SMS
- Email
- QR codes
- Marketing campaigns

Long URLs also look messy and reduce click through rates.

A URL shortening system solves this by:

1. Generating a short unique code
2. Mapping that code to the original URL
3. Redirecting users instantly when the short link is visited

Blink aims to implement this entire system with production style architecture.

---

Core Features

1 Short URL Generation

Users can submit any long URL and Blink will generate a short code.

Example:

Input

https://github.com/shivanshbagga/java-backend-roadmap

Output

blink.ly/X8gT2

Each short code uniquely maps to one original URL.

---

2 Fast Redirect Service

When someone visits:

blink.ly/X8gT2

Blink will:

1. Look up the short code in the database
2. Retrieve the original URL
3. Redirect the user using HTTP 302 or 301 response

This process must happen within milliseconds.

---

3 Custom Alias Support

Users can choose their own short code.

Example:

blink.ly/portfolio
blink.ly/resume
blink.ly/github

If the alias is already taken, Blink rejects the request.

---

4 Link Expiration

Users can create links that expire automatically.

Example use cases:

- Temporary documents
- Interview links
- Limited promotions

Example

blink.ly/job-link
Expires in: 7 days

After expiration the link returns 404 Not Found.

---

5 Click Analytics

Blink records every time a short link is visited.

Analytics may include:

- Total clicks
- Timestamp
- IP address
- User agent
- Geographic location (optional)

Example analytics

Short URL: blink.ly/X8gT2
Total Clicks: 1,245
Top Country: India
Last Click: 2026-03-06 22:14

---

System Architecture

Blink follows a backend service architecture.

Client
   |
   v
REST API
   |
   v
Application Service
   |
   v
Database

Flow: Creating a Short Link

User → POST /shorten
     → Validate URL
     → Generate short code
     → Store mapping
     → Return short URL

---

Flow: Redirecting a Link

User → GET /{shortCode}
     → Lookup database
     → Fetch original URL
     → Redirect

---

Technology Stack

Backend

Java
Spring Boot
Spring Web
Spring Data JPA

Database

MySQL

Optional Additions

Redis (caching popular URLs)
Docker (containerization)
Nginx (reverse proxy)

---

Database Design

URL Table

Field| Type| Description
id| BIGINT| Primary key
original_url| TEXT| The full URL
short_code| VARCHAR| Unique generated code
created_at| TIMESTAMP| Creation time
expires_at| TIMESTAMP| Expiration time
custom_alias| BOOLEAN| Whether alias is custom

Indexing is critical for fast lookups.

INDEX(short_code)

---

Click Analytics Table

Field| Type| Description
id| BIGINT| Primary key
url_id| BIGINT| Reference to URL
clicked_at| TIMESTAMP| Click time
ip_address| VARCHAR| Visitor IP
user_agent| TEXT| Browser info

---

Short Code Generation Strategies

Generating short codes is the most interesting engineering problem here.

Option 1 Random Base62

Characters:

a-z
A-Z
0-9

Example code

a8F3xZ

Advantages

- Short
- Hard to guess
- High capacity

---

Option 2 Hash Based

Hash the URL and convert to Base62.

Example

MD5(URL) → Base62

---

Option 3 Incremental ID Encoding

ID: 1024
Base62(ID): g8

This approach is commonly used in production systems.

---

API Endpoints

Create Short URL

POST /api/urls

Request

{
  "url": "https://example.com/article",
  "customAlias": "my-article",
  "expiresAt": "2026-04-01"
}

Response

{
  "shortUrl": "blink.ly/my-article"
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

Get Analytics

GET /api/urls/{shortCode}/analytics

Response

{
  "totalClicks": 324,
  "lastClicked": "2026-03-06T21:40:00"
}

---

Example Project Structure

blink
 ├── controller
 │     └── UrlController.java
 │
 ├── service
 │     └── UrlService.java
 │
 ├── repository
 │     └── UrlRepository.java
 │
 ├── model
 │     └── Url.java
 │
 ├── analytics
 │     └── ClickEvent.java
 │
 └── util
       └── Base62Encoder.java

This structure keeps the codebase clean and maintainable.

---

Performance Considerations

Database Indexing

Without an index on "short_code", redirects become extremely slow.

Indexes allow constant time lookup.

---

Caching

Popular URLs can be cached using Redis.

Flow

Check Cache
   ↓
Cache Hit → Redirect
   ↓
Cache Miss → Database → Store in Cache

This drastically improves performance.

---

Rate Limiting

Protect the API from abuse.

Example

100 requests per minute per IP

---

Future Enhancements

Blink can evolve into a much more powerful platform.

Possible upgrades:

- QR code generation
- Password protected links
- Link preview pages
- Link editing
- Geo based analytics
- Distributed ID generation
- Multi region deployment

---

Learning Outcomes

Building Blink teaches several important backend engineering concepts:

- REST API design
- URL encoding strategies
- Database indexing
- System scalability
- Analytics tracking
- Caching strategies
- Clean backend architecture

It is an excellent intermediate backend project that demonstrates real world engineering thinking.

---

License

MIT License

---

Author

Built by Shivansh Bagga
