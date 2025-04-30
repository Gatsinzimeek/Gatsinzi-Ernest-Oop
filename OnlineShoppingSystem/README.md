# OnlineShoppingSystem

A Java-based simulation of an online shopping experience. Users can browse, add items to a cart, and checkout, with support for various product categories.

## Requirements
- Java 17 or higher (for local runs)
- Docker Desktop (for containerized runs)

## How to Build & Run

### Using Docker
```sh
cd OnlineShoppingSystem
# Build the Docker image
docker build -t online-shopping-system .
# Run the application interactively
docker run --rm -it online-shopping-system
```

### Running Locally
```sh
javac *.java
java OnlineShoppingSystem
```

## Features
- Customer registration and validation
- Shopping cart management
- Multiple item categories (Accessories, Books, Clothing, Electronics, Groceries, etc.)
- Checkout and payment simulation
- Sales reporting

## Notes
- For interactive use, always use the `-it` flag with Docker.
- No data persistence between runs (in-memory only).

---

For more details, see the source code and comments.
