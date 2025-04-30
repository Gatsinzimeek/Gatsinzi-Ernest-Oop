# InsuranceSystem

A Java application for managing motor vehicle insurance policies and claims. Supports various policy types and comprehensive reporting features.

## Requirements
- Java 17 or higher (for local runs)
- Docker Desktop (for containerized runs)

## How to Build & Run

### Using Docker
```sh
cd InsuranceSystem
# Build the Docker image
docker build -t insurance-system .
# Run the application interactively
docker run --rm -it insurance-system
```

### Running Locally
```sh
javac *.java
java InsuranceSystem
```

## Features
- Add and manage multiple policy types (Comprehensive, Third Party, Collision, Liability, Roadside Assistance)
- File and manage insurance claims
- Generate detailed reports

## Notes
- For interactive use, always use the `-it` flag with Docker.
- No data persistence between runs (in-memory only).

---

For more details, see the source code and comments.
