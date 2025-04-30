# Project Overview

This repository contains three systems implemented in Java with help of OOP:

1. **Motor Insurance System**: Manages insurance policies, claims, and vehicles.
2. **Online Shopping System**: Handles shopping items, carts, and payments.
3. **Stock Management System**: Tracks inventory, suppliers, and warehouses.

## Prerequisites

- Java Development Kit (JDK) 11 or later
- Docker installed on your system

## Setup Instructions


### Option 1: Build and Run Locally

1. **Build the Docker Image**:

   ```bash
   docker build -t 26622-gatsinzi-ernest-java .
   ```

2. **Run the Docker Container**:

   ```bash
   docker run -it 26622-gatsinzi-ernest-java
   ```

3. **Navigate and Execute**:
   Inside the container, navigate to the desired system folder and compile/run the Java files. For example:

   ```bash
   cd motor-insurance-system
   javac Main.java
   java Main
   ```

#### Option 2: Use Docker Registry

1. **Pull the Prebuilt Docker Image**:

   ```bash
   docker pull 26622-gatsinzi-ernest-java
   ```

2. **Run the Docker Container**:

   ```bash
   docker run -it 26622-gatsinzi-ernest-java
   ```

3. **Navigate and Execute**:
   Inside the container, navigate to the desired system folder and compile/run the Java files. For example:

   ```bash
   cd motor-insurance-system
   javac Main.java
   java Main
   ```

### Running Locally

1. Clone the repository:

   ```bash
   git clone https://github.com/Gatsinzimeek/Gatsinzi-Ernest-Oop.git
   ```

2. Navigate to the desired system folder and compile/run the Java files. For example:

   ```bash
   cd motor-insurance-system
   javac Main.java
   java Main
   ```
#### Projects 
# InsuranceSystem

A Java application for managing motor vehicle insurance policies and claims. Supports various policy types and comprehensive reporting features.


## Features
- Add and manage multiple policy types (Comprehensive, Third Party, Collision, Liability, Roadside Assistance)
- File and manage insurance claims
- Generate detailed reports

## Notes
- For interactive use, always use the `-it` flag with Docker.
- No data persistence between runs (in-memory only).

---
