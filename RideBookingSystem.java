import java.sql.*;
import java.util.Scanner;

public class RideBookingSystem {
    // Database connection details
    static final String DB_URL = "jdbc:mysql://localhost:3306/ride_db";
    static final String USER = "root";
    static final String PASS = "";
    
    static Connection conn = null;
    static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("=== Ride Booking System ===");
        System.out.println("Connecting to database...");
        
        // Connect to database
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected to database successfully!");
        } catch (Exception e) {
            System.out.println("Error connecting to database: " + e.getMessage());
            return;
        }
        
        // Main menu loop
        int choice;
        do {
            showMenu();
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); 
            
            switch (choice) {
                case 1:
                    insertBooking();
                    break;
                case 2:
                    viewAllBookings();
                    break;
                case 3:
                    updateBooking();
                    break;
                case 4:
                    deleteBooking();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 5);
        
        // Close connection
        try {
            if (conn != null) {
                conn.close();
            }
            scanner.close();
        } catch (SQLException e) {
            System.out.println("Error closing connection: " + e.getMessage());
        }
    }
    
    // Display menu options
    static void showMenu() {
        System.out.println("\n--- Menu ---");
        System.out.println("1. Insert a new ride booking");
        System.out.println("2. View all ride bookings");
        System.out.println("3. Update a ride booking by ID");
        System.out.println("4. Delete a ride booking by ID");
        System.out.println("5. Exit");
    }
    
    // Insert new booking
    static void insertBooking() {
        try {
            System.out.println("\n--- Insert New Booking ---");
            System.out.print("Enter customer name: ");
            String customerName = scanner.nextLine();
            
            System.out.print("Enter pickup location: ");
            String pickup = scanner.nextLine();
            
            System.out.print("Enter drop location: ");
            String dropLocation = scanner.nextLine();
            
            System.out.print("Enter ride date (e.g., 2024-01-15): ");
            String rideDate = scanner.nextLine();
            
            String sql = "INSERT INTO ride_booking (customer_name, pickup, drop_location, ride_date) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, customerName);
            pstmt.setString(2, pickup);
            pstmt.setString(3, dropLocation);
            pstmt.setString(4, rideDate);
            
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Booking inserted successfully!");
            } else {
                System.out.println("Failed to insert booking.");
            }
            pstmt.close();
        } catch (SQLException e) {
            System.out.println("Error inserting booking: " + e.getMessage());
        }
    }
    
    // View all bookings
    static void viewAllBookings() {
        try {
            System.out.println("\n--- All Bookings ---");
            String sql = "SELECT * FROM ride_booking";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            System.out.println("ID\tCustomer Name\t\tPickup\t\t\tDrop Location\t\tRide Date");
            System.out.println("--------------------------------------------------------------------------------");
            
            boolean hasData = false;
            while (rs.next()) {
                hasData = true;
                int id = rs.getInt("id");
                String customerName = rs.getString("customer_name");
                String pickup = rs.getString("pickup");
                String dropLocation = rs.getString("drop_location");
                String rideDate = rs.getString("ride_date");
                
                System.out.println(id + "\t" + customerName + "\t\t" + pickup + "\t\t" + dropLocation + "\t\t" + rideDate);
            }
            
            if (!hasData) {
                System.out.println("No bookings found.");
            }
            
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error viewing bookings: " + e.getMessage());
        }
    }
    
    // Update booking by ID
    static void updateBooking() {
        try {
            System.out.println("\n--- Update Booking ---");
            System.out.print("Enter booking ID to update: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // consume newline
            
            System.out.print("Enter new customer name: ");
            String customerName = scanner.nextLine();
            
            System.out.print("Enter new pickup location: ");
            String pickup = scanner.nextLine();
            
            System.out.print("Enter new drop location: ");
            String dropLocation = scanner.nextLine();
            
            System.out.print("Enter new ride date (e.g., 2024-01-15): ");
            String rideDate = scanner.nextLine();
            
            String sql = "UPDATE ride_booking SET customer_name=?, pickup=?, drop_location=?, ride_date=? WHERE id=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, customerName);
            pstmt.setString(2, pickup);
            pstmt.setString(3, dropLocation);
            pstmt.setString(4, rideDate);
            pstmt.setInt(5, id);
            
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Booking updated successfully!");
            } else {
                System.out.println("No booking found with ID: " + id);
            }
            pstmt.close();
        } catch (SQLException e) {
            System.out.println("Error updating booking: " + e.getMessage());
        }
    }
    
    // Delete booking by ID
    static void deleteBooking() {
        try {
            System.out.println("\n--- Delete Booking ---");
            System.out.print("Enter booking ID to delete: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // consume newline
            
            String sql = "DELETE FROM ride_booking WHERE id=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Booking deleted successfully!");
            } else {
                System.out.println("No booking found with ID: " + id);
            }
            pstmt.close();
        } catch (SQLException e) {
            System.out.println("Error deleting booking: " + e.getMessage());
        }
    }
}
