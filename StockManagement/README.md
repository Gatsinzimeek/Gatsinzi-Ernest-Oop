# Advanced Stock Management System - qn1

This project is a Java-based stock management system. You can build and run it easily using Docker, ensuring a consistent environment for all users.

## Prerequisites
- [Docker](https://www.docker.com/products/docker-desktop) installed on your system.

## How to Build and Run

### 1. Clone the Repository
If you haven't already, clone this repository:
```sh
git clone <your-repo-url>
cd <repo-root>/StockManagement
```

### 2. Build the Docker Image
From the `qn1` directory, build the Docker image:
```sh
docker build -t stock-management .
```

### 3. Run the Application Interactively
Run the Docker container in interactive mode to use the menu system:
```sh
docker run --rm -it stock-management
```

- The `-it` flag allows you to interact with the application via the terminal.
- The menu will appear, and you can enter your choices as prompted.

## Notes
- The Dockerfile automatically compiles all `.java` files before running the application.
- No `.class` files are stored in the repository; they are generated during the build.
- If you make changes to the source code, rebuild the Docker image to apply them.

## Troubleshooting
- If you see `NoSuchElementException: No line found`, make sure you are running the container with the `-it` flags.

---

Feel free to open issues or contribute improvements!
