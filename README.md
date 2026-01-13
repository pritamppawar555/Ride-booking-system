# Ride Booking System - Java Console Application

A simple Java console-based application for managing ride bookings using JDBC and MySQL.

## Setup Instructions

### 1. Database Setup (XAMPP + MySQL)

1. Start XAMPP and make sure MySQL is running
2. Open phpMyAdmin (http://localhost/phpmyadmin)
3. Import and run the `create_table.sql` file to create the database and table
   - Or manually execute the SQL commands in the file

### 2. Java Setup

1. Make sure you have Java JDK installed
2. Download MySQL JDBC Driver (mysql-connector-java.jar)
   - Download from: https://dev.mysql.com/downloads/connector/j/
   - Or use: mysql-connector-java-8.0.33.jar (or latest version)

### 3. Compile and Run

**Compile:**
```bash
javac -cp "mysql-connector-java-8.0.33.jar" RideBookingSystem.java
```

**Run:**
```bash
java -cp ".;mysql-connector-java-8.0.33.jar" RideBookingSystem
```

(On Linux/Mac, use `:` instead of `;` in the classpath)

### 4. Database Configuration

If your MySQL password is not empty, edit the `PASS` variable in `RideBookingSystem.java`:
```java
static final String PASS = "your_password";
```

If your MySQL port is different from 3306, edit the `DB_URL`:
```java
static final String DB_URL = "jdbc:mysql://localhost:YOUR_PORT/ride_db";
```

## Features

1. **Insert a new ride booking** - Add customer name, pickup, drop location, and date
2. **View all ride bookings** - Display all bookings in a table format
3. **Update a ride booking by ID** - Modify existing booking details
4. **Delete a ride booking by ID** - Remove a booking from the database
5. **Exit** - Close the application

## Usage

Run the application and follow the menu prompts. Enter the corresponding number for each operation.
