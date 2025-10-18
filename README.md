# Hestia 🏠

A configurable dashboard for your home lab.

## Overview

Hestia provides a clean web interface to monitor and manage your home lab infrastructure. The dashboard displays
real-time information about your Docker containers, PostgreSQL databases, and other services through customizable
panels.

## Features

- **Docker Integration** - Monitor running containers, system resources, and images
- **PostgreSQL Panel** - Database connection and status monitoring
- **Configurable Panels** - Enable/disable panels based on your setup
- **Responsive Design** - Clean web interface accessible from any device
- **Real-time Updates** - Live monitoring of your infrastructure

## Prerequisites

- Java 21 or higher
- Docker and Docker Compose
- PostgreSQL (optional, can use included Docker setup)

## Quick Start

### 1. Clone the Repository

```bash
git clone https://github.com/aloussase/hestia.git
cd hestia
```

### 2. Run with Docker compose

```bash
docker-compose up -d
```

The application will start on `http://localhost:8080/dashboard`

## Development Setup

### Building from Source

```bash
# Clean build
./gradlew clean build

# Run tests
./gradlew test

# Run a specific test
./gradlew test --tests "HestiaApplicationTests.contextLoads"
```

### Configuration

The application can be configured through `src/main/resources/application.properties`:

## Contributing

1. Fork the repository
2. Create a feature branch
3. Submit a pull request

## Roadmap

- [ ] Dynamic configuration

## License

MIT
