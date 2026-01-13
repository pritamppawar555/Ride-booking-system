-- Create database
CREATE DATABASE IF NOT EXISTS ride_db;

-- Use the database
USE ride_db;

-- Create ride_booking table
CREATE TABLE IF NOT EXISTS ride_booking (
  id INT PRIMARY KEY AUTO_INCREMENT,
  customer_name VARCHAR(50),
  pickup VARCHAR(50),
  drop_location VARCHAR(50),
  ride_date VARCHAR(20)
);
