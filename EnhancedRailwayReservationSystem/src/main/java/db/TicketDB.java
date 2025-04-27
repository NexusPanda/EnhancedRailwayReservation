package db;

import model.Ticket;
import java.sql.*;
import java.util.*;

public class TicketDB {
    public static boolean bookTicket(int userId, String trainName, String status, int fare) {
        try (Connection conn = DBConnection.getConnection()) {
            String pnr = generatePNR();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO tickets (pnr, user_id, train_name, status, fare) VALUES (?, ?, ?, ?, ?)");
            stmt.setString(1, pnr);
            stmt.setInt(2, userId);
            stmt.setString(3, trainName);
            stmt.setString(4, status);
            stmt.setInt(5, fare);
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Ticket> viewTickets(int userId) {
        List<Ticket> tickets = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM tickets WHERE user_id = ?");
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                tickets.add(new Ticket(rs.getString("pnr"), rs.getInt("user_id"), rs.getString("train_name"), rs.getString("status"), rs.getInt("fare")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tickets;
    }

    public static List<Ticket> viewAllTickets() {
        List<Ticket> tickets = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM tickets");
            while (rs.next()) {
                tickets.add(new Ticket(rs.getString("pnr"), rs.getInt("user_id"), rs.getString("train_name"), rs.getString("status"), rs.getInt("fare")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tickets;
    }

    public static boolean cancelTicket(String pnr) {
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM tickets WHERE pnr = ?");
            stmt.setString(1, pnr);
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static String generatePNR() {
        return "PNR" + new Random().nextInt(100000);
    }
}
